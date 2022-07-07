package com.example.connect_odoo_mobile.contact.get_contact;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connect_odoo_mobile.R;
import com.example.connect_odoo_mobile.contact.ContactDetailActivity;
import com.example.connect_odoo_mobile.handle.ImageUtils;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private final Context context;
    private final List<Contact> contactArrayList;

    public ContactAdapter(Context context, List<Contact> contactArrayList) {
        this.context = context;
        this.contactArrayList = contactArrayList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactArrayList.get(position);
        if (!contact.getName().equals("")) {
            holder.txtName.setText(contact.getName());
        }
        if (!contact.getCompany_name().equals("")) {
            holder.txtCompany.setText(contact.getCompany_name());
        }
        if (!contact.getEmail().equals("")) {
            holder.txtEmail.setText(contact.getEmail());
        }
        if (!contact.getImage().equals("")) {
            holder.imgAvatar.setImageBitmap(ImageUtils.getBitmapImage(contact.getImage()));
        }
        holder.layoutContact.setOnClickListener(view -> {
            Intent intent = new Intent(context, ContactDetailActivity.class);
            //data transfer to screen contact detail
            intent.putExtra("id", contact.getId());
            intent.putExtra("name", contact.getName());
            intent.putExtra("email", contact.getEmail());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtName, txtEmail, txtCompany;
        private final ImageView imgAvatar;
        private final ConstraintLayout layoutContact;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtCompany = itemView.findViewById(R.id.txtCompany);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            layoutContact = itemView.findViewById(R.id.layoutContact);
        }
    }
}
