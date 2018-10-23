package com.compubase.mhmd.digitalatlas;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class AdminMain extends AppCompatActivity {
    ImageButton add, patientlisst, approve, notific;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        final AddFinfing1 addFinfing1 = new AddFinfing1();
        final AdminApproveList adminApproveList = new AdminApproveList();
        final AdminNotification adminNotification = new AdminNotification();
        final PaientList paientList = new PaientList();
        approve = findViewById(R.id.approve);
        title = findViewById(R.id.title);

        title.setText("Add Finding(S)");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.cotaineradmin,addFinfing1 );
        fragmentTransaction.commit();

        //final AddPatientFragment addPatientFragment = new AddPatientFragment();
        //final PatientListAdmin patientListAdmin = new  PatientListAdmin();
        // PatientNotification patientNotification = new PatientNotification();
        // final NotificationAdmin notificationAdmin = new NotificationAdmin();
        //FragmentManager fragmentManager = getFragmentManager();
        // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.add(R.id.cotainer , frag);
        //fragmentTransaction.commit();

        add = findViewById(R.id.addfinfingadmin);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.cotaineradmin,addFinfing1 );
                fragmentTransaction.commit();

                title.setText("Add Finding(S)");

            }
        });
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.cotaineradmin,adminApproveList );
                fragmentTransaction.commit();

                title.setText("Approve Users");


            }
        });
        notific = findViewById(R.id.notificAdmin);
        notific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.cotaineradmin,adminNotification );
                fragmentTransaction.commit();

                title.setText("Notifications");

            }
        });
        patientlisst = findViewById(R.id.paierntlistadmin);
        patientlisst .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.cotaineradmin,paientList );
                fragmentTransaction.commit();


                title.setText("Patient List");

            }
        });
    }
}






