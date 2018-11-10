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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.eaziche.R;
import com.app.eaziche.adapters.ServiceListAdapterClass;
import com.app.eaziche.models.ShopItems;
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

public class ProfileListFragment extends Fragment {

    RecyclerView recyclerView;
    ServiceListAdapterClass adapter;
    ArrayList<ShopItems> shopItems;
    RecyclerView.LayoutManager layoutManager;
    private int subcatId;
    private String subcatName;
    TextView textView;


    public ProfileListFragment(){

    }


    public static ProfileListFragment newInstance() {
        ProfileListFragment fragment = new ProfileListFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_profile_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)view.findViewById(R.id.rvNamesOfService);
        textView = (TextView)view.findViewById(R.id.tvSubCatName);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        shopItems = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new ServiceListAdapterClass(getContext());
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        textView.setText(subcatName);
        getShopArrayList();

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }
    public void getShopArrayList() {
        StringRequest request = new StringRequest(Request.Method.POST,
                getString(R.string.company_names_url),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
                        shopItems = parseJsonArrayResponse(res);
                        adapter.setShopItems(shopItems);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("ID", String.valueOf(subcatId));
                return params;
            }
        };

        Mysingleton.getInstance(getContext()).addToRequestQue(request);
    }
    private ArrayList<ShopItems> parseJsonArrayResponse(String response) {

        ArrayList<ShopItems> arrayList = new ArrayList<>();

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
                JSONObject object = responseArray.getJSONObject(c);
                ShopItems shopItems = new ShopItems(object.getInt("id"),object.getString("name"),object.getString("bio"),object.getString("address"),object.getString("rates"),object.getString("no_of_view"),object.getString("followers"),object.getString("logo") );
                arrayList.add(shopItems);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            c++;
        }
        return arrayList;
    }
    
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getCatId(SubCategories category) {
        subcatId = category.getId();
        Log.e("IN PROFILE", "HEre it is Get In PROFILE"+subcatId);
        subcatName = category.getName();
    }
}
