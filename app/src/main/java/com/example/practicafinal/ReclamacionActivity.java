package com.example.practicafinal;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ReclamacionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private EditText nombre;
    private EditText email;
    private EditText motivo;
    private EditText idFactura;
    private Uri imagenReclamacion;
    ImageView m_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacion);

        //Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                0,
                0
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_view);
        navigationView.setNavigationItemSelectedListener(this);

        m_imageView = findViewById(R.id.imagen_reclamacion);
        nombre = findViewById(R.id.edit_text_nombre2);
        email = findViewById(R.id.edit_text_email2);
        idFactura = findViewById(R.id.edit_text_idFactura2);
        motivo = findViewById(R.id.edit_text_motivo2);
        //imagenReclamacion = findViewById(R.id.imagen_reclamacion);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_reclamacion, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(20);
        }
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    static final  int REQUEST_IMAGE_CAPTURE = 1;
    static final  int REQUEST_GALLERY = 2;

    public void dispatchTakePictureIntent(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } catch (ActivityNotFoundException e) {
                // display error state to the user
            }
        }
    }

    public void dispatchOpenGalleryIntent(View v) {
        Intent takePictureIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            try {
                startActivityForResult(takePictureIntent, REQUEST_GALLERY);
            } catch (ActivityNotFoundException e) {
                // display error state to the user
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            m_imageView.setImageBitmap(imageBitmap);
            m_imageView.setVisibility(View.VISIBLE);
        }
        else if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK)
        {
            Uri videoUri = data.getData();
            m_imageView.setImageURI(videoUri);
            m_imageView.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }

    //Icono de la derecha send reclamacion
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reclamacion, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Darle funcionalidad al icono del send reclamacion
    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.sendReclamacion:
                sendReclamacion();
                return true;
        }
        return false;
    }

    private void sendReclamacion() {

        if (nombre.getText().toString().isEmpty() || email.getText().toString().isEmpty() || idFactura.getText().toString().isEmpty() || motivo.getText().toString().isEmpty()){
            Toast.makeText(ReclamacionActivity.this, "Rellene los campos en blanco", Toast.LENGTH_SHORT).show();
        }
        else {
            String inputEmail = email.getText().toString();
            String[] emails = inputEmail.split(",");
            String inputNombre = nombre.getText().toString();
            String inputIdFactura = idFactura.getText().toString();
            String inputMotivo = motivo.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, emails);
            intent.putExtra(Intent.EXTRA_SUBJECT, inputIdFactura);
            intent.putExtra(Intent.EXTRA_TEXT, inputNombre + "\n \n" + inputMotivo);
            //intent.putExtra(Intent.EXTRA_STREAM, imagenReclamacion);

            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Elige cómo enviarlo: "));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent = new Intent(this, SectionActivity.class);
        switch (id)
        {
            case R.id.navigation_home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_novedades:
                intent.putExtra("SECTION_ID", 0);
                startActivity(intent);
                break;
            case R.id.navigation_ofertas:
                intent.putExtra("SECTION_ID", 1);
                startActivity(intent);
                break;
            case R.id.navigation_ps4:
                intent.putExtra("SECTION_ID", 2);
                startActivity(intent);
                break;
            case R.id.navigation_xbox:
                intent.putExtra("SECTION_ID", 3);
                startActivity(intent);
                break;
            case R.id.navigation_carrito:
                Intent intentShopCart = new Intent(this, ShoppingCartActivity.class);
                startActivity(intentShopCart);
                break;
            case R.id.navigation_location:
                Intent intentLocation = new Intent(this, LocationActivity.class);
                startActivity(intentLocation);
                break;
            case R.id.navigation_contact:
                Intent intent_contact = new Intent(this, ContactActivity.class);
                startActivity(intent_contact);
                break;
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}