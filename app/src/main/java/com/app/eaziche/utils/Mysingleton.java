package com.app.eaziche.utils;

import android.content.Context;
import com.android.volley.Request; 
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Mysingleton {

	private static Mysingleton mInstance;
	private RequestQueue queue;
	private static Context context;

	public Mysingleton(Context c) {
		context = c;
		queue = getRequestQue();
		// TODO Auto-generated constructor stub
	}

	public RequestQueue getRequestQue() {
		if (queue == null) {
			queue = Volley.newRequestQueue(context.getApplicationContext());
		}
		return queue;
	}

	public static synchronized Mysingleton getInstance(Context c) {
		if (mInstance == null) {
			mInstance = new Mysingleton(c);
		}
		return mInstance;
	}
	
	public <T>void addToRequestQue(Request<T> request) {
		queue.add(request);
	}
}