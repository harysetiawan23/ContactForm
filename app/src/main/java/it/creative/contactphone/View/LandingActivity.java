package it.creative.contactphone.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import it.creative.contactphone.Adapter.ContactAdapter;
import it.creative.contactphone.Controller.DatabaseHelper;
import it.creative.contactphone.Model.Contact;
import it.creative.contactphone.R;

public class LandingActivity extends AppCompatActivity {
    private RecyclerView mContactRV;
    private RecyclerView.Adapter mAdapter;
    private Context mContext;
    private DatabaseHelper mContactDatabase;
    private Toolbar mToolbar;

    private ArrayList<Contact> mContact = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        initComponent();
        initValue();
        Toast.makeText(mContext,"Application Ready To Use : onCreate",Toast.LENGTH_SHORT).show();
    }

    private void initValue() {

        setSupportActionBar(mToolbar);
        mAdapter.notifyDataSetChanged();
        mContactRV.setLayoutManager(new GridLayoutManager(mContext,1));
        mContactRV.setAdapter(mAdapter);
    }

    private void initComponent() {
        mContext = this;
        mContactRV = (RecyclerView)findViewById(R.id.contact_list_rv);
        mContactDatabase = new DatabaseHelper(mContext.getApplicationContext());
        mAdapter = new ContactAdapter(mContactDatabase.getAllContacts(),mContext);
        mToolbar = (Toolbar)findViewById(R.id.contact_toolbar);

    }

    private void loadRV() {
        mAdapter = new ContactAdapter(mContactDatabase.getAllContacts(),mContext);
        mContactRV.setAdapter(mAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(mContext,"Applicatoon is Underneath : onPause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(mContext,"Welcome : onStart",Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged();
        loadRV();

    }


    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
        loadRV();
        Toast.makeText(mContext,"Applicatoon is Resume : onResume",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(mContext,"Goodbye : onDestroy",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.add_contact:{
                startActivity(new Intent(mContext,AddContact.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
