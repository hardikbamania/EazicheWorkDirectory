package com.app.eaziche.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.RatingCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.eaziche.R;
import com.app.eaziche.activities.Blank;
import com.app.eaziche.adapters.AlsoListedAdapter;
import com.app.eaziche.adapters.OurPeopleAdapter;
import com.app.eaziche.models.OurPeople;
import com.app.eaziche.models.ShopDetails;
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

import static android.widget.LinearLayout.HORIZONTAL;

public class ProfileDescFragment extends Fragment {


    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    ShopDetails shopDetails;
    TextView sDesc,sAddress,sCon1,sCon2,sWebsite, sOurService, sNoOfRates, sRates,sReadMore;
    AppCompatRatingBar ratingBar;
    private ArrayList<SubCategories> arrayList;
    private ArrayList<OurPeople> peoples;
    AlsoListedAdapter adapter;
    OurPeopleAdapter peopleAdapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.tab_desc, container, false);

            // initializing variables
            sDesc = (TextView) view.findViewById(R.id.tvShortDesc);
            sAddress = (TextView) view.findViewById(R.id.tvAddress);
            sCon1 = (TextView) view.findViewById(R.id.tvContactNo1);
            sCon2 = (TextView) view.findViewById(R.id.tvContactNo2);
            sWebsite = (TextView) view.findViewById(R.id.tvWebsite);
            sRates = (TextView) view.findViewById(R.id.tvRates);
            sNoOfRates = (TextView) view.findViewById(R.id.tvNoOfRates);
            sOurService = (TextView) view.findViewById(R.id.tvOurServiceDetails);
            sReadMore = (TextView) view.findViewById(R.id.tvReadMore);
            ratingBar = (AppCompatRatingBar) view.findViewById(R.id.ratingBar);
            recyclerView = (RecyclerView) view.findViewById(R.id.rvAlsoListedIn);
            recyclerView2 = (RecyclerView) view.findViewById(R.id.rvPeoples);

            //adapter setting
            adapter = new AlsoListedAdapter(getContext());
            peopleAdapter = new OurPeopleAdapter(getContext());
            recyclerView.setAdapter(adapter);
            recyclerView2.setAdapter(peopleAdapter);

            //giving layout to recycler view
            LayoutManager layoutManager = new LinearLayoutManager(getContext(), HORIZONTAL, false);
            recyclerView2.setLayoutManager(layoutManager);
            recyclerView.setVisibility(View.VISIBLE);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            //return view
            return view;
        }
        else
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getCatId(final ShopDetails details) {
        Log.e("ShopDetails", "are here");
        shopDetails = details;
        if (details.getBio().length() > 120)
            sDesc.setText(String.format("%s...", details.getBio().substring(0, 100)));
        else
            sDesc.setText(details.getBio());
        sAddress.setText(details.getAddress());
        sCon1.setText(details.getContact_1());
        sCon2.setText(details.getContact_2());
        sWebsite.setText(details.getWebsite());
        sRates.setText(String.format("%s",details.getRates()));
        ratingBar.setRating(details.getRates());
        sNoOfRates.setText(details.getNo_of_rates());
        sOurService.setText(details.getOur_service());
        getAlsoListedIn(details.getId());
        getOurPeople(details.getId());


        sReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(getActivity(),Blank.class);
                view.putExtra("TITLE","Description");
                view.putExtra("VALUE",details.getBio());
                startActivity(view);
            }
        });
    }

    public void getAlsoListedIn(final String id) {
        StringRequest request = new StringRequest(Request.Method.POST, getString(R.string.also_listed_in_url), new com.android.volley.Response.Listener<String>() {
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
                params.put("ID", id);
                return params;
            }
        };
        Mysingleton.getInstance(getContext()).addToRequestQue(request);
    }

    public void getOurPeople(final String id) {
        StringRequest request = new StringRequest(Request.Method.POST, getString(R.string.our_people_url), new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("OurPeople", response);
                peoples = parseOurPeopleResponse(response);
                peopleAdapter.setListOurPeople(peoples);
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

    private ArrayList<OurPeople> parseOurPeopleResponse(String response) {

        ArrayList<OurPeople> arrayList = new ArrayList<>();


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
                OurPeople people = new OurPeople(Integer.parseInt(jsonObject.getString("id")), jsonObject.getString("profile"), jsonObject.getString("name"));
                arrayList.add(people);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            c++;
        }
        return arrayList;
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
        int c = 0, size = 0;

        if (responseArray != null)
            size = responseArray.length();

        while (c < size) {
            try {
                JSONObject jsonObject = responseArray.getJSONObject(c);
                SubCategories subCategories = new SubCategories(Integer.parseInt(jsonObject.getString("id")), jsonObject.getString("icon"), jsonObject.getString("title"));
                arrayList.add(subCategories);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            c++;
        }
        return arrayList;
    }

}
