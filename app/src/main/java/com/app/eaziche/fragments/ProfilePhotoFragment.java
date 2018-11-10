package com.app.eaziche.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.eaziche.R;
import com.app.eaziche.adapters.ImageAdapter;
import com.app.eaziche.models.Images;
import com.app.eaziche.models.ShopDetails;

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

import static android.support.v7.widget.StaggeredGridLayoutManager.HORIZONTAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePhotoFragment extends Fragment {


    private ArrayList<Images> images;
    ShopDetails shopDetails;
    RecyclerView recyclerView;
    LayoutManager manager;
    ImageAdapter adapter;
    View view;

    public ProfilePhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null) {
            view = inflater.inflate(R.layout.tab_photos, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.rvImages);
            adapter = new ImageAdapter(getContext());
            manager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getCatId(final ShopDetails details) {
        Log.e("ShopDetails", "are here");
        shopDetails = details;
        getImages(details.getId());
    }

    public void getImages(final String id) {
        StringRequest request = new StringRequest(Request.Method.POST, getString(R.string.profile_images_url), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Images", response);
                images = parseJsonArrayResponse(response);
                adapter.setImages(images);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("ID", id);
                return params;
            }
        };
        Mysingleton.getInstance(getContext()).addToRequestQue(request);
    }


    private ArrayList<Images> parseJsonArrayResponse(String response) {

        ArrayList<Images> arrayList = new ArrayList<>();

        Log.e("RESPONSE", response);
        JSONArray responseArray = null;
        try {
            responseArray = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int c = 0, size = 0;

        if (responseArray != null)
            size = responseArray.length();

        while (c < size) {
            try {
                JSONObject jsonObject = responseArray.getJSONObject(c);
                Images images = new Images(jsonObject.getInt("id"),jsonObject.getString("thumbnail"),jsonObject.getString("title"));
                arrayList.add(images);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            c++;
        }
        return arrayList;
    }
}
