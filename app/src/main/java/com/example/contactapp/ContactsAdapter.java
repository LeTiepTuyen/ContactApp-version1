package com.example.contactapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactapp.databinding.ContactRowItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> implements Filterable {
    private ArrayList<Contact> contactList;
    private ArrayList<Contact> contactListOld;
    private ContactRowItemBinding binding;
    public ContactsAdapter(ArrayList<Contact> contactList) {
        this.contactList = contactList;
        this.contactListOld = contactList;

    }

    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(contactList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView tvName;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvName = (TextView) view.findViewById(R.id.tv_name);
        }


    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    contactList = contactListOld;
                } else {
                    ArrayList<Contact>  list = new ArrayList<Contact>();
                    for (Contact contact: contactListOld) {
                        if(contact.getName().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(contact);
                        }
                    }
                    contactList =  list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = contactList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                contactList = (ArrayList<Contact>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}

