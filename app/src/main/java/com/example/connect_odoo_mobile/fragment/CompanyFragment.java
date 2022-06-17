package com.example.connect_odoo_mobile.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.connect_odoo_mobile.R;
import com.example.connect_odoo_mobile.adapter.CompanyAdapter;
import com.example.connect_odoo_mobile.adapter.ContactAdapter;
import com.example.connect_odoo_mobile.data.GetDataFromOdoo;
import com.example.connect_odoo_mobile.data_models.Company;
import com.example.connect_odoo_mobile.data_models.Contact;
import com.example.connect_odoo_mobile.read_json.ReadJSON;
import com.google.gson.Gson;

import org.apache.xmlrpc.XmlRpcException;

import java.util.ArrayList;
import java.util.List;

public class CompanyFragment extends Fragment {
    private View view;
    private CompanyAdapter companyAdapter;
    private List<Company> companyArrayList;
    private RecyclerView layoutView;
    private GetDataFromOdoo getDataFromOdoo = new GetDataFromOdoo();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_company, container, false);
        //mapping view
        mapping();
        //set contact recycler view
       setContactRecyclerView();
        return view;
    }

    private void mapping() {
        layoutView = view.findViewById(R.id.layoutView);
    }

    private void setContactRecyclerView() {
        try {
            companyArrayList = getDataFromOdoo.getCompany();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        companyAdapter = new CompanyAdapter(getContext(), companyArrayList);
        LinearLayoutManager companyManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        layoutView.setLayoutManager(companyManager);
        layoutView.setAdapter(companyAdapter);
    }
}