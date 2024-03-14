package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;

import com.example.contactapp.databinding.ActivityMainBinding;
import com.example.contactapp.databinding.ContactRowItemBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private ActivityMainBinding binding;
    private ArrayList<Contact> listContacts;

    private ContactsAdapter contactsAdapter;

    private AppDatabase appDatabase;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvContacts.setLayoutManager(linearLayoutManager);

        listContacts = new ArrayList<Contact>();
        listContacts.add(new Contact("Lu","0822565661","lutk@gmail.com"));
        listContacts.add(new Contact("My","0556655661","mybedo@gmail.com"));
        listContacts.add(new Contact("Win","0822565651","wintk@gmail.com"));
        listContacts.add(new Contact("Hao","0956655661","hao31@gmail.com"));
        listContacts.add(new Contact("Tan","0722565661","tanvn@gmail.com"));
        listContacts.add(new Contact("Trang","0235665661","trang33@gmail.com"));


        contactsAdapter = new ContactsAdapter(listContacts);
        binding.rvContacts.setAdapter(contactsAdapter);
        contactsAdapter.notifyDataSetChanged();


        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        binding.rvContacts.addItemDecoration(itemDecoration);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                contactDao = appDatabase.contactDao();
                contactDao.insertAll(new Contact("Way","0822601191","way@gmail.com"));

            }
        });


        binding.btnNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewContact.class);
                startActivity(intent);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menusearch, menu);
        return true;

//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);


    }
}