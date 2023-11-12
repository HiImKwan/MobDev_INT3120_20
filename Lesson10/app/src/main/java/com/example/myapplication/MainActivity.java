package com.example.myapplication;

import static android.provider.ContactsContract.RawContacts.*;
import static android.provider.ContactsContract.RawContacts.ACCOUNT_TYPE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.Data;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.get_contact);
        Button genContact = findViewById(R.id.gen_contact);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContact();
            }
        });
        genContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genContact();
            }
        });
    }

    private void genContact() {
        // To display a short toast message
        Toast toast = Toast.makeText(getApplicationContext(), "App cc", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void getContact() {
        getPhoneContact();
    }

    private void getPhoneContact() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.READ_CONTACTS}, 0);
        }

        ContentResolver contentResolver = getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        Log.i("CONTENT_PROVIDER_DEMO", "Total number of contacts: " + Integer.toString(cursor.getCount()));
        if(cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Log.i("CONTENT_PROVIDER_DEMO", "Contact name: " + contactName + ", phone number: " + contactNumber);
            }
        }
    }
}