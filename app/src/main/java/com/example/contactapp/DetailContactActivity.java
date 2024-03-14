package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.contactapp.databinding.ActivityDetailContactBinding;

public class DetailContactActivity extends AppCompatActivity {
    private ActivityDetailContactBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);


        binding = ActivityDetailContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent receivedIntent = getIntent();

        if (receivedIntent != null) {
            String name = receivedIntent.getStringExtra("name");
            String phone = receivedIntent.getStringExtra("phone");
            String email = receivedIntent.getStringExtra("email");

            binding.nameEdittext.setText(name);
            binding.phoneNumberEdittext.setText(phone);
            binding.emailEdittext.setText(email);
        }

        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Quay lại màn hình trước đó
            }
        });
    }
}