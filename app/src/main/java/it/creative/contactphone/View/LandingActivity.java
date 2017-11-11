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

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
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
