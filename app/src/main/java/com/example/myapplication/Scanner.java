package com.example.myapplication;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Scanner extends AppCompatActivity {
    public String result;
    private String Gender;
    private String category;
    private MaterialButton selectGender;
    private MaterialButton selectCategory;
    private MaterialButton generate;

    private MaterialButton inputImageBtn;
    private MaterialButton scanBtn;
    private ShapeableImageView imageIv;
    private EditText scannedIngredients;
    private static final String TAG = "MAIN_TAG";
    private Uri imageUri = null;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;
    private String[] cameraPermissions;
    private String[] storagePermissions;
    private ProgressDialog progressDialog;
    private TextRecognizer textRecognizer;
    private String url,data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        inputImageBtn = findViewById(R.id.inputImageBtn);
        scanBtn = findViewById(R.id.scanBtn);
        imageIv = findViewById(R.id.imageIv);
        scannedIngredients = findViewById(R.id.scannedIngredients);
        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait ...");
        progressDialog.setCanceledOnTouchOutside(false);
        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        selectGender = findViewById(R.id.Gender);
        selectCategory = findViewById(R.id.Category);
        generate = findViewById(R.id.generateRec);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Objects.equals(Gender, null) || Objects.equals(category, null)){
                    Toast.makeText(Scanner.this, "Select Gender and Category", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    result = jsonObject.toString();
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){

                    protected Map<String,String> getParam(){
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("ingredient_list", data);
                        params.put("gender", Gender);
                        params.put("category", category);
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(Scanner.this);
                queue.add(stringRequest);
            }
        });

        selectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryDialog();
            }
        });


        selectGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGenderDialog();
            }
        });


        inputImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputImageDialog();
            }
        });

        //recognizeText
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUri == null){
                    Toast.makeText(Scanner.this, "Select Image first..", Toast.LENGTH_SHORT).show();
                }else{
                    recognizeTextFromImg();
                }
            }
        });



    }

    private void recognizeTextFromImg() {
        Log.d(TAG,"ScanIngredients");
        progressDialog.setMessage("Scanning Ingredients!");
        progressDialog.show();
        try{
            InputImage inputImage = InputImage.fromFilePath(this, imageUri);
            progressDialog.setMessage("Scanning Ingredients!");
            Task<Text> textTaskResult = textRecognizer.process(inputImage)
                    .addOnSuccessListener(new OnSuccessListener<Text>(){
                        @Override
                        public void onSuccess(Text text){
                            progressDialog.dismiss();
                            String recognizedText = text.getText();

                            Log.d(TAG,"onSuccess: Scanned"+recognizedText);
                            scannedIngredients.setText(recognizedText);
                            data = scannedIngredients.getText().toString();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Log.e(TAG,"onFailure",e);
                            Toast.makeText(Scanner.this, "Try again due to"+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        }catch(Exception e){
            progressDialog.dismiss();
            Log.e(TAG,"Scanning",e);
            Toast.makeText(this, "Try again due to"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private void showInputImageDialog() {
        PopupMenu popupMenu = new PopupMenu(this, inputImageBtn);

        popupMenu.getMenu().add(Menu.NONE,1,1,"CAMERA");
        popupMenu.getMenu().add(Menu.NONE,2,2,"GALLERY");
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id== 1){
                    Log.d(TAG,"Camera Clicked");
                    if(checkCameraPermissions()){//changed
                        pickImageCamera();

                    }else{
                        requestCameraPermission();
                    }
                }else if(id == 2){
                    Log.d(TAG,"Gallery Clicked");
                    if(checkStoragePermissions()){//changed
                        pickImageGallery();

                    }else{
                        requestStoragePermission();
                    }
                }
                return true;
            }
        });
    }

    //Gender Selection nad Category

    private void showGenderDialog() {
        PopupMenu popupMenu = new PopupMenu(this, selectGender);

        popupMenu.getMenu().add(Menu.NONE,1,1,"Male");
        popupMenu.getMenu().add(Menu.NONE,2,2,"Female");
       // popupMenu.getMenu().add(Menu.NONE,3,3,"");

        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == 1){

                    Gender = String.valueOf(popupMenu.getMenu().getItem(id-1).getTitle());
                    selectGender.setText(Gender);
                    popupMenu.dismiss();

                }else if(id == 2){

                    Gender = String.valueOf(popupMenu.getMenu().getItem(id-1).getTitle());
                    selectGender.setText(Gender);
                    popupMenu.dismiss();
                }else{
                    popupMenu.dismiss();
                }
                return false;
            }
        });
    }


    private void showCategoryDialog() {
        PopupMenu popupMenu = new PopupMenu(this, selectCategory);

        popupMenu.getMenu().add(Menu.NONE,1,1,"Protien");
        popupMenu.getMenu().add(Menu.NONE,2,2,"Vitamin");
        popupMenu.getMenu().add(Menu.NONE,3,3,"Omega 3");
        //popupMenu.getMenu().add(Menu.NONE,4,4,"");

        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                category = (String) popupMenu.getMenu().getItem(id-1).getTitle();
                selectCategory.setText(category);
                popupMenu.dismiss();
                return false;
            }
        });
    }





    private void pickImageGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryActivityResultLauncher.launch(intent);

    }

    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        //got image
                        Intent data = result.getData();
                        imageUri = data.getData();
                        imageIv.setImageURI(imageUri);
                    }else{
                        //cancelled
                        Toast.makeText(Scanner.this, "Cancelled....", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private void pickImageCamera(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Sample Title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Sample Desc");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        cameraActivityResultLauncher.launch(intent);


    }

    private ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        imageIv.setImageURI(imageUri);
                    }else{
                        Toast.makeText(Scanner.this, "Cancelled.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private boolean checkStoragePermissions(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermissions(){
        boolean storageResult = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        boolean cameraResult = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return storageResult && cameraResult;
    }

    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length > 0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if(cameraAccepted && storageAccepted){
                        pickImageCamera();
                    }else{
                        Toast.makeText(this, "Camera & Storage Permission are Required", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Cancelled.", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length > 0){
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if(storageAccepted){
                        pickImageGallery();
                    }else{
                        Toast.makeText(this, "Storage Permission is Required.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            break;
        }
    }
}