package com.compubase.mhmd.digitalatlas;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaientList extends Fragment {

    RecyclerView paientList ;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Patient> patients = new ArrayList<>();
    String URL,type;
    RequestQueue requestQueue;
    PatientAdapter myAdapter;
    View view;


    TinyDB tinyDB;

    public PaientList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_paient_list, container, false);
       return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        paientList = view.findViewById(R.id.paientlists);
        paientList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        paientList.setLayoutManager(layoutManager);

        tinyDB = new TinyDB(getContext());

        type = tinyDB.getString("type");

        myAdapter = new PatientAdapter(getContext(),patients);


        JSON_DATA_WEB_CALL();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void JSON_DATA_WEB_CALL(){

        if(type.equals("admin"))
        {
            URL = "http://atlas.alosboiya.com.sa/atlas.asmx/select_all_pat_for_admin";
        }else
            {
                URL = "http://atlas.alosboiya.com.sa/atlasnew.asmx/select_pat_by_iduser?iduser="+tinyDB.getString("userID");
            }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                showMessage();

            }

        }) {


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("iduser", tinyDB.getString("userID"));
                return params;
            }

        };

        requestQueue = Volley.newRequestQueue(getContext());

        requestQueue.add(stringRequest);



    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(String Jobj){

        patients.clear();
        myAdapter.notifyDataSetChanged();

        try {
            JSONArray js = new JSONArray(Jobj);

            for(int i = 0; i<js.length(); i++) {

                JSONObject childJSONObject = js.getJSONObject(i);

                Patient GetDataAdapter2 = new Patient();

                GetDataAdapter2.setName(childJSONObject.getString("name"));
                GetDataAdapter2.setId(childJSONObject.getString("id"));
                GetDataAdapter2.setImgUrl(childJSONObject.getString("image"));
                GetDataAdapter2.setUseremail(childJSONObject.getString("email"));
                GetDataAdapter2.setIsApproved(childJSONObject.getString("approval"));
                GetDataAdapter2.setPhone(childJSONObject.getString("phone"));
                GetDataAdapter2.setSystem(childJSONObject.getString("system"));
                GetDataAdapter2.setFinding(childJSONObject.getString("finding"));
                GetDataAdapter2.setAbnormality(childJSONObject.getString("abnormality"));
                GetDataAdapter2.setDate(childJSONObject.getString("datee"));
                GetDataAdapter2.setMention(childJSONObject.getString("mention"));
                GetDataAdapter2.setScenario(childJSONObject.getString("scenario"));
                GetDataAdapter2.setMessage(childJSONObject.getString("message"));
                patients.add(GetDataAdapter2);
            }


            paientList.setAdapter(myAdapter);
            //myAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showMessage() {
        Toast.makeText(getContext(), "No Connection", Toast.LENGTH_LONG).show();
    }

}
