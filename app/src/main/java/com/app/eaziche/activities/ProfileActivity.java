package com.app.eaziche.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.eaziche.R;
import com.app.eaziche.fragments.ProfileDescFragment;
import com.app.eaziche.fragments.ProfileMapFragment;
import com.app.eaziche.fragments.ProfilePhotoFragment;
import com.app.eaziche.fragments.ProfileRatesFragment;
import com.app.eaziche.models.ShopDetails;
import com.app.eaziche.models.ShopItems;
import com.app.eaziche.utils.Mysingleton;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity{

    FragmentTabHost tabHost;
    ScrollView scrollView;
    ShopItems shopItems;
    ShopDetails shopDetails;
    TextView sName, sFollowers, sView,sUserName;
    ImageView logo,cover;
    public static int USER_ID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Eaziche");
        }
        tabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        scrollView = (ScrollView) findViewById(R.id.scroll);


        sName = (TextView) findViewById(R.id.tvName);
        sUserName = (TextView) findViewById(R.id.tvUserName);
        sFollowers = (TextView) findViewById(R.id.tvFollowers);
        sView = (TextView) findViewById(R.id.tvViews);
        logo = (ImageView)findViewById(R.id.ivProfilePicture);
        cover = (ImageView)findViewById(R.id.ivBackGround);

        shopItems = getIntent().getParcelableExtra("shopItems");
        initialTabs();
        scrollView.smoothScrollTo(0, 0);
        initialValue();
    }

    private void initialTabs() {
        tabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);

        TabSpec tabDesc = tabHost.newTabSpec("DESC");
        TabSpec tabRates = tabHost.newTabSpec("RATES");
        TabSpec tabMap = tabHost.newTabSpec("MAP");
        TabSpec tabPhoto = tabHost.newTabSpec("PHOTO");

        tabDesc.setIndicator("Desc");
        tabRates.setIndicator("Rates");
        tabMap.setIndicator("Map");
        tabPhoto.setIndicator("Images");

        tabHost.addTab(tabDesc, ProfileDescFragment.class,null);
        tabHost.addTab(tabRates, ProfileRatesFragment.class,null);
        tabHost.addTab(tabMap,ProfileMapFragment.class,null);
        tabHost.addTab(tabPhoto, ProfilePhotoFragment.class,null);

    }

    private void initialValue() {
        USER_ID = shopItems.getUid();
        sName.setText(shopItems.getName());
        sFollowers.setText(shopItems.getfollowers());
        sView.setText(shopItems.getViews());
        getShopDetails();
    }

    public void getShopDetails() {


        StringRequest request = new StringRequest(Request.Method.POST,
                getString(R.string.company_details_url),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String res) {
                        try {
                            JSONObject object =  new JSONObject(res);
                            shopDetails = new ShopDetails(object.getString("id"),object.getString("name"),object.getString("user_name"),object.getString("website"),object.getString("address"),object.getString("5"),object.getString("6"),object.getString("7"),object.getString("8"),object.getString("9"),object.getString("10"),object.getString("11"),object.getString("12"),object.getString("13"),object.getString("14"),object.getString("15"),object.getString("16"),object.getString("17"),object.getString("18"));
                            setNewValues(shopDetails);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("RESPONCE OF SHOPDETAILS",res);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ID", String.valueOf(shopItems.getUid()));
                return params;
            }
        };

        Mysingleton.getInstance(this).addToRequestQue(request);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void setNewValues(ShopDetails shopDetails) {
        Picasso.with(this).load(getString(R.string.url)+"user/cover/"+shopDetails.getCover()).into(cover);
        Picasso.with(this).load(getString(R.string.url)+"user/profile/"+shopDetails.getLogo()).resize(100,100).centerCrop().into(logo);
        EventBus.getDefault().postSticky(shopDetails);
        sUserName.setText(String.format("#%s",shopDetails.getUser_name()));

    }

}
