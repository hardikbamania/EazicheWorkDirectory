package com.app.eaziche.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.eaziche.R;
import com.app.eaziche.adapters.RateAdapter;
import com.app.eaziche.models.Rates;
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

public class ProfileRatesFragment extends Fragment {


    private int Id;
    private ArrayList<Rates> arrayList;
    private RateAdapter adapter;
    RecyclerView.LayoutManager manager;
    View view;
    RecyclerView recyclerView;
    private ShopDetails shopDetails;

    public ProfileRatesFragment() {
        // Required empty public constructor`
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       if (view == null){
           view = inflater.inflate(R.layout.tab_rates,container,false);
           recyclerView = (RecyclerView) view.findViewById(R.id.rvRates);
           adapter = new RateAdapter(getContext());
           manager = new LinearLayoutManager(getContext());
           recyclerView.setAdapter(adapter);
           recyclerView.setLayoutManager(manager);
       }
        return view;
    }

    public void getRates(final String id) {
        StringRequest request = new StringRequest(Request.Method.POST, getString(R.string.rates_url), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                arrayList = parseJsonArrayResponse(response);
                adapter.setListRates(arrayList);
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
                params.put("ID", id);
                return params;
            }
        };
        Mysingleton.getInstance(getContext()).addToRequestQue(request);
    }


    private ArrayList<Rates> parseJsonArrayResponse(String response) {

        ArrayList<Rates> arrayList = new ArrayList<>();

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
     /*   Rates Rates = new Rates(1, "","Hardik Bamaniya", "tari sasu ne dhokra bhave",4);

        arrayList.add(Rates);
     */   while (c < size) {
            try {
                JSONObject jsonObject = responseArray.getJSONObject(c);
                Rates Rates = new Rates(Integer.parseInt(jsonObject.getString("id")), jsonObject.getString("profile"), jsonObject.getString("name"), jsonObject.getString("comment"),Float.parseFloat(jsonObject.getString("rates")));

                arrayList.add(Rates);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            c++;
        }
        return arrayList;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getCatId(final ShopDetails details) {
        Log.e("ShopDetails", "are here");
        shopDetails = details;
        getRates(details.getId());
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

}
