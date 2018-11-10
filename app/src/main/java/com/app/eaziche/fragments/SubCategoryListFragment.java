package com.app.eaziche.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.eaziche.R;
import com.app.eaziche.adapters.SubCategoryAdapterClass;
import com.app.eaziche.models.Category;
import com.app.eaziche.models.SubCategories;
import com.app.eaziche.utils.Mysingleton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubCategoryListFragment extends Fragment {

    RecyclerView mRecyclerView;
    SubCategoryAdapterClass adapter;
    RecyclerView.LayoutManager layoutManager;
    TextView textView;
    static String catName;
    static int catId;
    private ArrayList<SubCategories> arrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_list, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.lvItems);
        layoutManager = new LinearLayoutManager(getContext());
        arrayList = new ArrayList<>();
        textView = (TextView) v.findViewById(R.id.tvCategory);

        adapter = new SubCategoryAdapterClass(getContext(),this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(layoutManager);
        return v;
    }

    public void getSubCategoryByString() {
        StringRequest request = new StringRequest(Request.Method.POST, getString(R.string.sub_category_url), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                arrayList = parseJsonArrayResponse(response);
                adapter.setListSubCategories(arrayList);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ID", String.valueOf(catId));
                return params;
            }
        };
        Mysingleton.getInstance(getContext()).addToRequestQue(request);
    }

    private ArrayList<SubCategories> parseJsonArrayResponse(String response) {

        ArrayList<SubCategories> arrayList = new ArrayList<>();

        Log.e("RESPONSE", response);
        JSONArray responseArray = null;
        try {
            responseArray = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int c = 0,size=0;

        if (responseArray != null)
            size = responseArray.length();

        while (c < size) {
            try {
                JSONObject jsonObject = responseArray.getJSONObject(c);
                SubCategories subCategories = new SubCategories(Integer.parseInt(jsonObject.getString("id")), jsonObject.getString("icon"), jsonObject.getString("title"), jsonObject.getString("describtion"));

                arrayList.add(subCategories);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            c++;
        }
        return arrayList;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        textView.setText(catName);
        getSubCategoryByString();

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getCatId(Category category) {
        catId = category.getId();
        Log.e("IN Sub", "HEre it is Get In Sub");
        catName = category.getName();
    }

}
