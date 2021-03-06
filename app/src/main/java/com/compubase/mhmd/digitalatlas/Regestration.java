package com.compubase.mhmd.digitalatlas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public class Regestration extends AppCompatActivity {

    String GET_JSON_DATA_HTTP_URL;
    EditText studentId,fn,ln,email,pass,phone,position,username;
    Button register;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);
        studentId = findViewById(R.id.studentid);
        fn = findViewById(R.id.fn);
        ln = findViewById(R.id.ln);
        email = findViewById(R.id.email);
        position = findViewById(R.id.position);
        register = findViewById(R.id.register);
        pass = findViewById(R.id.password);
        phone = findViewById(R.id.mobnum);
        username =findViewById(R.id.username);
        checkBox = findViewById(R.id.checkbox);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked())
                {
                    volleyConnection();
                }else
                    {
                        showMessage("Check the box first");
                    }
            }
        });

    }


    private void volleyConnection()
    {
        GET_JSON_DATA_HTTP_URL = "http://atlas.alosboiya.com.sa/atlasnew.asmx/insert_user?";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_JSON_DATA_HTTP_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(Objects.equals(response, "True")){
                            showMessage("Registration  Done Successful Congratulations");

                            onBackPressed();

                        }else {
                            showMessage("Failed Please Try Again ");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                showMessage(error.toString());

            }

        }) {

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("name", fn.getText().toString());
                params.put("phone", phone.getText().toString());
                params.put("email", email.getText().toString());
                params.put("password", pass.getText().toString());
                params.put("companyname", studentId.getText().toString());
                params.put("postion", position.getText().toString());
                params.put("lastname", ln.getText().toString());
                params.put("username", username.getText().toString());

                return params;
            }



        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }



    private void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_LONG).show();
    }
}
