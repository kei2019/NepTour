package com.example.neptour;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonPattern {

    static SingletonPattern singletonPattern;
    RequestQueue requestQueue;
    static Context ctx;

    SingletonPattern(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }
    public static synchronized SingletonPattern getInstance(Context context) {
        if (singletonPattern == null) {
            singletonPattern = new SingletonPattern(context);
        }
        return singletonPattern;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {

            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
