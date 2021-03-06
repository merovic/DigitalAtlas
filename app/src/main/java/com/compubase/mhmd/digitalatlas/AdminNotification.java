package com.compubase.mhmd.digitalatlas;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class AdminNotification extends Fragment {

    EditText title, body;
    EditText person;
    Button send;
    String GET_JSON_DATA_HTTP_URL;
    View view;

    RequestQueue requestQueue;

    public AdminNotification() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.fragment_admin_notification, container, false);
         return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        person = view.findViewById(R.id.person);
        title = view.findViewById(R.id.title);
        body = view.findViewById(R.id.body);
        send = view.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                volleyconnect();
            }
        });
    }
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void volleyconnect()
        {
            GET_JSON_DATA_HTTP_URL = "http://atlas.alosboiya.com.sa/atlasnew.asmx/note?";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_JSON_DATA_HTTP_URL,

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            showMessage(response);
                            showMessage("Success Sending");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    showMessage(error.toString());

                }

            }) {

                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("sender", person.getText().toString());
                    params.put("head", title.getText().toString());
                    params.put("body", body.getText().toString());

                    return params;
                }



            };

            requestQueue = Volley.newRequestQueue(getContext());

            requestQueue.add(stringRequest);

        }
        @RequiresApi(api = Build.VERSION_CODES.M)
        private void showMessage(String _s) {
            Toast.makeText(getContext(), _s, Toast.LENGTH_LONG).show();
        }

    }

