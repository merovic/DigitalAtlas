package com.compubase.mhmd.digitalatlas;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddReviewerActivity extends Fragment {

    String GET_JSON_DATA_HTTP_URL;
    EditText studentId,fn,ln,email,pass,phone,position,username;
    Button register;
    View view;


    public AddReviewerActivity() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_reviewer, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        studentId = view.findViewById(R.id.studentid);
        fn = view.findViewById(R.id.fn);
        ln = view.findViewById(R.id.ln);
        email = view.findViewById(R.id.email);
        position = view.findViewById(R.id.position);
        register = view.findViewById(R.id.register);
        pass = view.findViewById(R.id.password);
        phone = view.findViewById(R.id.mobnum);
        username = view.findViewById(R.id.username);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyConnection();
            }
        });
    }



    private void volleyConnection()
    {
        GET_JSON_DATA_HTTP_URL = "http://atlas.alosboiya.com.sa/atlasnew.asmx/insert_reviewer?";
        // http://atlas.alosboiya.com.sa/atlas.asmx?op=login_campany

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_JSON_DATA_HTTP_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(Objects.equals(response, "True")){
                            showMessage("Adding  Done Successful Congratulations");

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

        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }


    private void showMessage(String _s) {
        Toast.makeText(getActivity(), _s, Toast.LENGTH_LONG).show();
    }

}
