package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.SearchView;

import com.example.contactapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private ActivityMainBinding binding;
    private ArrayList<Contact> listContacts = new ArrayList<Contact>();

    private ContactsAdapter contactsAdapter;

    private AppDatabase appDatabase;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // View binding:
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        //Set layout cho Recyleview rvContacts:
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvContacts.setLayoutManager(linearLayoutManager);



        //Tạo Adapter và bind dữ liệu Adapter với Recyleview:
        contactsAdapter = new ContactsAdapter(listContacts);
        binding.rvContacts.setAdapter(contactsAdapter);
        contactsAdapter.notifyDataSetChanged();


        //Tạo các gạch chia giữa từng item trong Recycle View:
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        binding.rvContacts.addItemDecoration(itemDecoration);

        //Set toolbar làm ActionBar hiện tại:
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);



        //Tạo intent cho NewContact:
        binding.btnNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewContact.class);
                startActivity(intent);
            }
        });


        // Xử lý Details khi ấn vào từng Item Contacts trên RecycleView
        // Dùng RecyclerView.OnItemTouchListener để lắng nghe sự kiện chọn mục. Intent sẽ được tạo để chuyển đến DetailContactActivity với dữ liệu tương ứng.
        // Sau khi tạo OnItemTouchListener phải add vào rvContact thông qua phương thức addOnItemTouchListener()
        binding.rvContacts.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && e.getAction() == MotionEvent.ACTION_UP) {
                    int position = rv.getChildAdapterPosition(child);
                    if (position != RecyclerView.NO_POSITION) {
                        Contact selectedContact = listContacts.get(position);

                        Intent intent = new Intent(MainActivity.this, DetailContactActivity.class);
                        intent.putExtra("name", selectedContact.getName());
                        intent.putExtra("phone", selectedContact.getMobile());
                        intent.putExtra("email", selectedContact.getGmail());
                        startActivity(intent);
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        // Đọc và ghi dữ liệu vào Database:
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Xóa toàn bộ dữ liệu trong bảng Contact


                appDatabase = AppDatabase.getInstance(getApplicationContext());
                contactDao = appDatabase.contactDao();


                // Nếu table Contact trong database với dữ liệu trống, thì thực hiện insertAll để khỏi tạo table Contact với giá trị ban đầu
                if (contactDao.countContacts() == 0) {
                    contactDao.insertAll(getListContacts());
                }

//

                // Đọc dữ liệu từ database và lưu vào listContacts
                listContacts.clear(); // Xóa dữ liệu cũ trước khi lấy dữ liệu mới
                listContacts.addAll(contactDao.getAll());

                // Cập nhật dữ liệu cho contactsAdapter
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contactsAdapter.notifyDataSetChanged();
                    }
                });


            }
        });


    }


    // function Khởi tạo dữ liệu ban đầu cho Contacts List:
    public ArrayList<Contact> getListContacts() {
        ArrayList initialContactsList = new ArrayList<Contact>();
        initialContactsList.add(new Contact("Tuyen","0822565661","lutk@gmail.com"));
        initialContactsList.add(new Contact("My","0556655661","mybedo@gmail.com"));
        initialContactsList.add(new Contact("Win","0822565651","wintk@gmail.com"));
        initialContactsList.add(new Contact("Trang","0235665661","trang33@gmail.com"));
        initialContactsList.add(new Contact("Hao","0956655661","hao31@gmail.com"));
        initialContactsList.add(new Contact("Tan","0722565661","tanvn@gmail.com"));
        initialContactsList.add(new Contact("Huyen","0235665661","trang33@gmail.com"));
        initialContactsList.add(new Contact("Huy","0235663331","huy45@gmail.com"));

        return initialContactsList;
    }



    // Khởi tạo menu activity bằng cách inflate (nạp) một menu từ một tập tin XML vào đối tượng Menu được cung cấp.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menusearch, menu);

        searchView  = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                contactsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactsAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;



    }


}