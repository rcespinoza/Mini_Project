package com.example.mini_rio1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;


    //declare variables
    Button loadImageButton;
    ImageView imageView;
    TextView textView;
    TextView classificationText;
    Uri imageUri;
    ArrayList<String> cat = new ArrayList<>(Arrays.asList("Dog", "Cat", "You"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cat.add("Cat");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing variables
        loadImageButton = findViewById(R.id.load_image_button);
        imageView = findViewById(R.id.image_view);
        textView = findViewById(R.id.textView);
        classificationText = findViewById(R.id.classification_text);

        //creating method for the button
//        @Override
        loadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


    }

    //method for selecting the photo
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE_REQUEST);
    }

    //Method for displaying photo and text
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            textView.setText(printCat());

        }
    }

    //helper method that returns a random category from the array
    private String printCat() {
        Random r = new Random();

        int index = r.nextInt(cat.size());
        return cat.get(index);
    }

}

