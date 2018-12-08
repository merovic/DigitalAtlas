package com.compubase.mhmd.digitalatlas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    ImageView pic;
    TextView name,email,phone,system,finding,abnormality,mention,date,scenario,comment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        pic = findViewById(R.id.pic);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        system = findViewById(R.id.system);
        finding = findViewById(R.id.finding);
        abnormality = findViewById(R.id.abnormality);
        mention = findViewById(R.id.mention);
        date = findViewById(R.id.date);
        scenario = findViewById(R.id.scenario);
        comment = findViewById(R.id.comment);

        Bundle extras = getIntent().getExtras();

        assert extras != null;
        String picc = extras.getString("userIMG");
        String namee = extras.getString("userName");
        String emaill = extras.getString("userEmail");
        String phonee = extras.getString("userPhone");
        String systemm = extras.getString("userSystem");
        String findingg = extras.getString("userFinding");
        String abnormalityy = extras.getString("userAbnormality");
        String mentionn = extras.getString("userMention");
        String datee = extras.getString("userDate");
        String scenarioo = extras.getString("userScenario");
        String commentt = extras.getString("userMessage");


        Glide.with(getApplicationContext())
                .load(picc)
                .into(pic);

        name.setText(namee);
        email.setText(emaill);
        phone.setText(phonee);
        system.setText(systemm);
        finding.setText(findingg);
        abnormality.setText(abnormalityy);
        mention.setText(mentionn);
        date.setText(datee);
        scenario.setText(scenarioo);
        comment.setText(commentt);
    }
}
