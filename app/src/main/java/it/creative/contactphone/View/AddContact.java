package it.creative.contactphone.View;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import it.creative.contactphone.Controller.DatabaseHelper;
import it.creative.contactphone.Model.Config;
import it.creative.contactphone.Model.Contact;
import it.creative.contactphone.R;

public class AddContact extends AppCompatActivity {
    private ImageView mContactImage;
    private EditText mName, mEmail, mPhone;
    private Button mSubmit;
    private Context mContext;
    private Uri resultUri;
    private DatabaseHelper mContactDB;
    private Toolbar mToolbar;
    private RadioButton mMale, mFemale;
    private CheckBox mShopping, mSports, mDance, mFashion, mOrganization;
    private SeekBar mBeautyMeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);


        initComponent();
        initValue();
    }

    private void initValue() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mContactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start((Activity) mContext);
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String hobbies ="";
                    Contact contact = new Contact();
                    contact.setContact_id(Config.randInt());

                    //Editext Data
                    contact.setContact_name(mName.getText().toString());
                    contact.setContact_email(mEmail.getText().toString());
                    contact.setContact_phone(mPhone.getText().toString());
                    contact.setContact_img_uri(resultUri.toString());

                    //Radio Button Data
                    if (mMale.isChecked()) {
                        contact.setContact_sex("Male");
                    } else if (mFemale.isChecked()) {
                        contact.setContact_sex("Female");
                    }

                    //CheckBox Button
                    if(mShopping.isChecked()){
                        hobbies += "Shopping ";
                    }
                    if(mSports.isChecked()){
                        hobbies +="Sports ";
                    }
                    if(mDance.isChecked()){
                        hobbies+="Dance ";
                    }
                    if(mFashion.isChecked()){
                        hobbies+="Fashion ";
                    }
                    if (mOrganization.isChecked()){
                        hobbies+="Organization ";
                    }
                    contact.setContact_hobbies(hobbies);


                    //Seekbar
                    mBeautyMeter.setMax(100);
                    contact.setContact_beauty(String.valueOf(mBeautyMeter.getProgress()));



                    showPopUp(contact);


                } catch (Exception e) {
                    Log.d("STATUS", " INSTERT ERROR "+e);
                }


            }
        });
    }

    private void showPopUp(final Contact contact){
        //AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = getLayoutInflater().inflate(R.layout.contact_frame,null);

        TextView mContactName,mContactEmail,mContactPhone,mContactBeauty,mContactHobbies,mContactSex;
        ImageView mContactImage;

        //Inititation
        mContactImage = (ImageView)view.findViewById(R.id.contact_image);
        mContactName = (TextView)view.findViewById(R.id.contact_name);
        mContactEmail = (TextView)view.findViewById(R.id.contact_email);
        mContactPhone = (TextView)view.findViewById(R.id.contact_phone);
        mContactBeauty = (TextView)view.findViewById(R.id.contact_beauty);
        mContactHobbies = (TextView)view.findViewById(R.id.contact_hobbies);
        mContactSex = (TextView)view.findViewById(R.id.contact_sex);


        mContactImage.setImageURI(Uri.parse(contact.getContact_img_uri()));
        mContactName.setText(contact.getContact_name());
        mContactEmail.setText(contact.getContact_email());
        mContactPhone.setText(contact.getContact_phone());
        mContactBeauty.setText(contact.getContact_beauty()+"%");
        mContactHobbies.setText(contact.getContact_hobbies());
        mContactSex.setText(contact.getContact_sex());

        builder.setView(view);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Contact","ID"+contact.getContact_id()+
                        "Beauty"+contact.getContact_beauty()+
                        "Name"+contact.getContact_name()+
                        "EMAIL"+contact.getContact_email()+
                        "HOBBies"+contact.getContact_hobbies()+
                        "SEX"+contact.getContact_sex()+
                        "Phone"+contact.getContact_phone()
                );
                mContactDB.addContact(contact);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void initComponent() {
        mContext = this;
        mContactImage = (ImageView) findViewById(R.id.add_contact_image);
        mName = (EditText) findViewById(R.id.add_contact_name);
        mEmail = (EditText) findViewById(R.id.add_contact_email);
        mPhone = (EditText) findViewById(R.id.add_contact_phone);
        mSubmit = (Button) findViewById(R.id.add_contact_submit);
        mContactDB = new DatabaseHelper(mContext.getApplicationContext());
        mToolbar = (Toolbar) findViewById(R.id.add_contact_toolbar);

        //Radio Button
        mFemale = (RadioButton)findViewById(R.id.add_contact_female);
        mMale = (RadioButton)findViewById(R.id.add_contact_male);

        //CheckButton
        mShopping = (CheckBox)findViewById(R.id.add_contact_shopping);
        mSports = (CheckBox)findViewById(R.id.add_contact_sports);
        mDance = (CheckBox)findViewById(R.id.add_contact_dance);
        mFashion = (CheckBox)findViewById(R.id.add_contact_fashion);
        mOrganization = (CheckBox)findViewById(R.id.add_contact_organization);


        //Seekbar
        mBeautyMeter = (SeekBar)findViewById(R.id.add_contact_beauty);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                mContactImage.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
