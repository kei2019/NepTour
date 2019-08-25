package com.example.neptour;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {
    private EditText username, password, email, editDate;
    private RadioGroup type;
    private Button register;
    Context context = this;
    private ProgressDialog progressDialog;
    Calendar myCalendar = Calendar.getInstance();
    String dateFormat = "dd.MM.yyyy";
    DatePickerDialog.OnDateSetListener date;
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
//    final public static String URL = "http://192.168.100.53:8000/register";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        type = findViewById(R.id.type);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);

        progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Processing");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFieldEmpty(email) && isFieldEmpty(password) && isFieldEmpty(username)) {
                    if (isEmailValid(email)) {

                        if (type.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(context, "Select type", Toast.LENGTH_SHORT).show();
                        } else {
                            registerUser();

                        }
                    }
                 }
            }
        });

    }

    private void registerUser() {
        final String usernamevalue = username.getText().toString().trim();
        final String passValue = password.getText().toString().trim();
        final String emailValue = email.getText().toString().trim();
        final RadioButton checkedBtn = findViewById(type.getCheckedRadioButtonId());
        final String typeValue = checkedBtn.getText().toString();
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Links.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    if(!jsonObject.getBoolean("error")){
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", usernamevalue);
                params.put("email", emailValue);
                params.put("password", passValue);
                params.put("checkedbtn", String.valueOf(checkedBtn));
                params.put("type", typeValue);
                return params;
            }

        };
        SingletonPattern.getInstance(context).addToRequestQueue(stringRequest);


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

