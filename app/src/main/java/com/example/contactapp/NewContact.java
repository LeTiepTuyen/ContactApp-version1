package com.example.contactapp;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

//import com.example.contactapp.databinding.ActivityMainBinding;
import com.example.contactapp.databinding.ActivityNewContactBinding;

public class NewContact extends AppCompatActivity {
    private ActivityNewContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewContactBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Quay lại màn hình trước đó
            }
        });


        ArrayAdapter<String> phoneTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Mobile", "Landline", "Other"});
        phoneTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Đặt giao diện hiển thị khi chọn một mục từ Spinner
        binding.phoneTypeSpinner.setAdapter(phoneTypeAdapter);


        ArrayAdapter<String> emailTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Home", "Company", "Other"});
        emailTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Đặt giao diện hiển thị khi chọn một mục từ Spinner
        binding.emailTypeSpinner.setAdapter(emailTypeAdapter);

    }


}
