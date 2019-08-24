package com.example.neptour;

import android.app.DatePickerDialog;
import android.content.Context;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    private EditText username, password, email, editDate;
    private RadioGroup type;
    private Button register;
    Context context = this;
    Calendar myCalendar = Calendar.getInstance();
    String dateFormat = "dd.MM.yyyy";
    DatePickerDialog.OnDateSetListener date;
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
    public static final String URL_REGISTER = "http://10.0.2.2/app/main/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        type = findViewById(R.id.type);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);


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
        String usernamevalue = username.getText().toString().trim();
        String passValue = password.getText().toString().trim();
        String emailValue = email.getText().toString().trim();
        RadioButton checkedBtn = findViewById(type.getCheckedRadioButtonId());
        final String typeValue = checkedBtn.getText().toString();

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

