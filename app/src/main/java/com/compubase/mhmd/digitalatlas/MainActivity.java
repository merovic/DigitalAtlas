package com.compubase.mhmd.digitalatlas;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageButton add, patientlists, notific;
    TextView title;
    final AddFinfing1 addFinfing1 = new AddFinfing1();
    final PaientList paientList = new PaientList();
    final UserNotification userNotification = new UserNotification();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.usercontiner,addFinfing1 );
        fragmentTransaction.commit();

        title.setText("Add Finding(S)");

        add = findViewById(R.id.addfinfing);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.usercontiner,addFinfing1 );
                fragmentTransaction.commit();

                title.setText("Add Finding(S)");

            }
        });
        patientlists = findViewById(R.id.plistbutton);
        patientlists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.usercontiner,paientList );
                fragmentTransaction.commit();

                title.setText("Patient List");

            }
        });
        notific =findViewById(R.id.usernotific);
        notific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.usercontiner,userNotification );
                fragmentTransaction.commit();

                title.setText("Notifications");
            }
        });
    }

}
