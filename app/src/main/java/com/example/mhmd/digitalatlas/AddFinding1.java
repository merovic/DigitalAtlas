package com.example.mhmd.digitalatlas;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import static android.media.MediaRecorder.VideoSource.CAMERA;
import static android.os.Environment.*;

public class AddFinding1 extends AppCompatActivity {
    private ImageView imageview;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgPath = null;

    private static final Object IMAGE_DIRECTORY = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_finding1);
        ImageView pikeimage = findViewById(R.id.addpic);
        pikeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicturDialog();
            }
        });
    }
    public  void  showPicturDialog()
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
        startActivityForResult(galleryIntent,1);
    }
    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == 0) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(AddFinding1.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageview.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddFinding1.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == 1) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ImageView imageviw;
            imageview.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(AddFinding1.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(String.valueOf(getExternalStorageDirectory())+IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


}
