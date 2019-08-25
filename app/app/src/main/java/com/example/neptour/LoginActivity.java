package com.example.neptour;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login_btn;
    Context context = this;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);
        progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Processing");

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFieldEmpty(email) && isFieldEmpty(password)) {
                    if (isEmailValid(email)) {
                       loginUser();
                    }
                }
            }
        });


    }

    private void loginUser() {
        final String emailValue = email.getText().toString().trim();
        final String passValue = password.getText().toString().trim();
//        Toast.makeText(LoginActivity.this, "email " +emailValue, Toast.LENGTH_SHORT).show();
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, Links.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error")) {

                        Toast.makeText(LoginActivity.this, "User login successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message")
                                ,Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage()
                        ,Toast.LENGTH_LONG).show();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id_email", emailValue);
                params.put("id_password", passValue);
                return params;
            }
        };

        SingletonPattern.getInstance(this).addToRequestQueue(stringRequest);

    }

    public Boolean isFieldEmpty(EditText view) {
        if (view.getText().toString().length() > 0) {
            return true;
        } else {
            view.setError("Field Required");
            return false;
        }
    }

    public Boolean isEmailValid(EditText view) {
        String value = view.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            return true;
        } else {
            view.setError("Invalid email");
            return false;
        }

    }
}
