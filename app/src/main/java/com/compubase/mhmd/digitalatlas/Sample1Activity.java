package com.compubase.mhmd.digitalatlas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Sample1Activity extends Activity {



    private static final String[] ageOf = { "Newborn" , "Child" , "Adolescence" , "Old Agy"};
    private static final String[] genderType = {"Male" , "Female" };
    private static final String[] sysType = {"Cardiology" , "Respirology" , "Dermatolgy" , "endocrinology" , "General surgery", "Cardit"
            , "Gynecology", "Hematology", "Infectious Diseases", "Medical Genetics", "Medical Imaging", "Nephrology",
            "Neuorology" ,"Obtritrics" ,"Ophthalomology" ,"Orthopedics" ,"Otolaryngology" ,
            "Pediatrics" ,"Plastic surgery" ,"Rheumatology" ,"Urology" ,"Vascular surgery" ,"Oncology"};
    EditText supervision , finding, rq1 ,rq2,rq3,rq4 ,op1 , op2 ,senario , abnormal;
    Button next;
    Spinner age , gender , system;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_addfinding3);


        finding = findViewById(R.id.finding);
        rq1 = findViewById(R.id.required1);
        rq2 = findViewById(R.id.required2);
        rq3 = findViewById(R.id.required3);
        supervision = findViewById(R.id.supervision);
        rq4 = findViewById(R.id.required4);
        op1 = findViewById(R.id.optional1);
        op2 = findViewById(R.id.optional2);
        senario = findViewById(R.id.senario);
        next = findViewById(R.id.next);
        abnormal = findViewById(R.id.abnormal);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Sample1Activity.this,Sample2Activity.class);
                startActivity(intent);

            }
        });


        ageSpiner();
        systemSpinner();
        genderSpinner();


    }



    public void ageSpiner()
    {
        age = findViewById(R.id.age);
        ArrayAdapter<String> ageAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item, ageOf);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(ageAdapter);
    }
    public void genderSpinner()
    {
        gender = findViewById(R.id.gender);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item, genderType);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);
    }
    public void systemSpinner()
    {
        system = findViewById(R.id.system);
        ArrayAdapter<String> sysAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item, sysType);
        sysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        system.setAdapter(sysAdapter);

    }

}
