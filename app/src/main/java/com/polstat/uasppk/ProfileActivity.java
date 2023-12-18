package com.polstat.uasppk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private Database db;
    private EditText ed1, ed2, ed3, ed4, ed5;
    private TextView tv;
    private Button btnSave, btnBack;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new Database(getApplicationContext(), "uasppk", null, 2);

        tv = findViewById(R.id.textViewProfileTitle);
        ed1 = findViewById(R.id.editTextProfileUsername);
        ed2 = findViewById(R.id.editTextProfileFullName);
        ed3 = findViewById(R.id.editTextProfileNip);
        ed4 = findViewById(R.id.editTextProfileEmail);
        ed5 = findViewById(R.id.editTextProfilePassword);
        btnSave = findViewById(R.id.buttonProfileSave);
        btnBack = findViewById(R.id.buttonProfileBack);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        String[] userInfo = db.getUserInfo(username);

        if (userInfo != null) {
            ed1.setText(userInfo[0]);
            ed2.setText(userInfo[2]);
            ed3.setText(userInfo[3]);
            ed4.setText(userInfo[1]);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validasi password
                String passwordFromDb = db.getPassword(username);

                if (isPasswordValid(passwordFromDb)) {
                   saveChanges();
                } else {
                    Toast.makeText(ProfileActivity.this, "Password is not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isPasswordValid(String enteredPassword) {
        return enteredPassword.equals(ed5.getText().toString());
    }

    private void setLoggedInStatus(boolean isLoggedIn) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
    }

    private void saveChanges() {
        String newFullName = ed2.getText().toString();
        String newNip = ed3.getText().toString();
        String newEmail = ed4.getText().toString(); // Mendapatkan email baru
        String newUsername = ed1.getText().toString(); // Mendapatkan username baru

        String oldUsername = db.getUserInfo(username)[0]; // Mendapatkan username lama

        db.updateUserProfile(username, newFullName, newNip);
        db.updateUsernameAndEmail(username, newUsername, newEmail);

        if (!oldUsername.equals(newUsername)) {
            // Jika username berubah, set status login false dan kembali ke halaman login
            setLoggedInStatus(false);
            Toast.makeText(ProfileActivity.this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
            Toast.makeText(ProfileActivity.this, "Please log back in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        } else {
            // Jika username tidak berubah, tampilkan pesan perubahan berhasil
            Toast.makeText(ProfileActivity.this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
        }
    }

}
