package com.compubase.mhmd.digitalatlas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Document extends AppCompatActivity {
    ImageView important;
    Button downloadimg;

    String TAG = "ass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        important = findViewById(R.id.importan);
        downloadimg = findViewById(R.id.saveimg);

        downloadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //https://pasteboard.co/HKC3z06.jpg

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pasteboard.co/HKC3z06.jpg"));
                startActivity(browserIntent);



            }
        });
    }


}
