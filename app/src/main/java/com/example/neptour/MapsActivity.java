package com.example.neptour;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";

    private GoogleMap mMap;
    private EditText searchPlace;
    private Button searchBtn;

    private static final float DEFAULT_ZOOM = 8f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        searchPlace = findViewById(R.id.search_place);
        searchBtn = findViewById(R.id.search_button);



        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String placeValue = searchPlace.getText().toString();
//                new HttpAsyncTask().execute("http://192.168.100.66/location");
            }
        });

    }

    public void getJsonOutput() {
        Toast.makeText(this, "Accessing : " + Links.map_url, Toast.LENGTH_SHORT).show();
        JsonObjectRequest locationRequest = new JsonObjectRequest(Request.Method.GET, Links.map_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
//                hidePDialog();

                try {
//                    JSONObject jsonObject = new JSONObject(response);
                    if (response.optJSONArray("locations") != null) {
                        JSONArray locations = response.getJSONArray("locations");

                        Log.d(TAG,"Locations: " + locations.length());

                        for (int i = 0; i < locations.length(); i++) {
                            JSONObject location = locations.getJSONObject(i);

                            String zone = location.getString("zone");
                            String placeType = location.getString("place_type");
                            String placeName = location.getString("place_name");

                            String lat = location.getString("lat");
                            String lng = location.getString("lng");

                            if (!lat.equals("") && !lng.equals("")) {
                                double latitude = Double.parseDouble(location.getString("lat"));
                                double longitude = Double.parseDouble(location.getString("lng"));
                                Log.d(TAG, "zone: " + zone);

                                Log.d(TAG, "location: " + location.toString());


                                MarkerOptions markerOptions = new MarkerOptions();
                                LatLng latLng;
                                latLng = new LatLng(latitude, longitude);
                                markerOptions.title(placeName);
                                markerOptions.position(latLng);
                                markerOptions.snippet(zone + "Place Type: " + placeType);
                                mMap.addMarker(markerOptions);
                            }


                            // next K,V pair consist of JSONObject photo_url
//                            JSONObject photoUrl = book.getJSONObject("photo_url");


//                            String photoSrc = photoUrl.getString("src");
//                            String photoAlt = photoUrl.getString("alt");
                            // use extracted values in your Book object
                        }
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                    Log.d(TAG, e.toString());
                }

                // notifying list adapter about data changes
                // so that it renders the list view with updated data
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "Error: " + error.getMessage());
//                hidePDialog();
                Toast.makeText(MapsActivity.this, "Error fetching API", Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(locationRequest);
    }
//    public static String GET(String url){
//        InputStream inputStream = null;
//        String result = "";
//        try {
//
//            // create HttpClient
//            HttpClient httpclient = new DefaultHttpClient();
//
//            // make GET request to the given URL
//            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
//
//            // receive response as inputStream
//            inputStream = httpResponse.getEntity().getContent();
//
//            // convert inputstream to string
//            if(inputStream != null)
//                result = convertInputStreamToString(inputStream);
//            else
//                result = "Did not work!";
//
//        } catch (Exception e) {
//            Log.d("InputStream", e.getLocalizedMessage());
//        }
//
//        return result;
//    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }


//    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... urls) {
//
//            return GET(urls[0]);
//        }
//        // onPostExecute displays the results of the AsyncTask.
//        @Override
//        protected void onPostExecute(String result) {
//            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
//            searchPlace.setText(result);
//        }
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng nepal = new LatLng(27,85);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nepal, DEFAULT_ZOOM));
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(27.7172, 85.3240);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Kathmandu"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        getJsonOutput();
    }


}
