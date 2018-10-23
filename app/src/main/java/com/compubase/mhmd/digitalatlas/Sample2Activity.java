package com.compubase.mhmd.digitalatlas;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class Sample2Activity extends Activity {

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    final int PICK_IMAGE_REQUEST = 71;
    Button finish;
    Uri imgPath;
    String pic1,pic2,pic3,pic4,pic5;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_addfinding2);

        pic1 = "";
        pic2 = "";
        pic3 = "";
        pic4 = "";
        pic5 = "";

        img1 = findViewById(R.id.pic1);
        img2 = findViewById(R.id.pic2);
        img3 = findViewById(R.id.pic3);
        img4 = findViewById(R.id.pic4);
        img5 = findViewById(R.id.pic5);


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();
                pic1 = "done";
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();
                pic2 = "done";
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();
                pic3 = "done";
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();
                pic4 = "done";
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();
                pic5 = "done";
            }
        });
        finish = findViewById(R.id.toFinding3);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showMessage("Upload Sucesse");

            }
        });


    }


    private   void  showPicturDialog()
    {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDlialogItem={"Select From Gallery" ,
                "Capture From Camera"};
        pictureDialog.setItems(pictureDlialogItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0 :
                        choosePhotoFromGallary();
                        break;
                    case 1:
                        takePhotoFromCamera();
                        break;
                }
            }
        });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,71);
    }
    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == PICK_IMAGE_REQUEST) && (resultCode == RESULT_OK)
                && (data != null) && (data.getData() != null))
        {
            imgPath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgPath);
                if(pic1.equals("done") && pic2.equals("") && pic3.equals("") && pic4.equals("") && pic5.equals(""))
                {
                    img1.setImageBitmap(bitmap);
                }else if(pic1.equals("done") && pic2.equals("done") && pic3.equals("") && pic4.equals("") && pic5.equals(""))
                {
                    img2.setImageBitmap(bitmap);
                }else if(pic1.equals("done") && pic2.equals("done") && pic3.equals("done") && pic4.equals("") && pic5.equals(""))
                {
                    img3.setImageBitmap(bitmap);
                }else if(pic1.equals("done") && pic2.equals("done") && pic3.equals("done") && pic4.equals("done") && pic5.equals(""))
                {
                    img4.setImageBitmap(bitmap);
                }else if(pic1.equals("done") && pic2.equals("done") && pic3.equals("done") && pic4.equals("done") && pic5.equals("done"))
                {
                    img5.setImageBitmap(bitmap);
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_LONG).show();
    }

}
