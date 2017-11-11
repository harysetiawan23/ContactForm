package it.creative.contactphone.View;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import it.creative.contactphone.Controller.DatabaseHelper;
import it.creative.contactphone.Model.Config;
import it.creative.contactphone.Model.Contact;
import it.creative.contactphone.R;

public class EditContact extends AppCompatActivity {

    private ImageView mContactImage;
    private EditText mName, mEmail, mPhone,mHobbies;
    private Button mSubmit,mDelete;
    private Context mContext;
    private Uri resultUri;
    private DatabaseHelper mContactDB;
    private Toolbar mToolbar;
    private RadioButton mMale, mFemale;
    private SeekBar mBeautyMeter;
    private Intent currIntent;
    private String sex;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        initComponent();
        initValue();
    }
    private void initComponent() {
        //Intent
        currIntent = getIntent();
        //Initiation
        mContext = this;
        mContactImage = (ImageView) findViewById(R.id.add_contact_image);
        mName = (EditText) findViewById(R.id.add_contact_name);
        mEmail = (EditText) findViewById(R.id.add_contact_email);
        mPhone = (EditText) findViewById(R.id.add_contact_phone);
        mSubmit = (Button) findViewById(R.id.add_contact_submit);
        mContactDB = new DatabaseHelper(mContext.getApplicationContext());
        mToolbar = (Toolbar) findViewById(R.id.add_contact_toolbar);
        mDelete = (Button)findViewById(R.id.add_contact_hapus);
        //Radio Button
        mFemale = (RadioButton)findViewById(R.id.add_contact_female);
        mMale = (RadioButton)findViewById(R.id.add_contact_male);

        //CheckButton
        mHobbies = (EditText)findViewById(R.id.add_contact_hobbies);


        //Seekbar
        mBeautyMeter = (SeekBar)findViewById(R.id.add_contact_beauty);
    }


    private void initValue() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContactImage.setImageURI(Uri.parse(currIntent.getStringExtra(Config.CONTACT_IMAGE)));
        mContactImage.setPadding(0,0,0,0);
        mName.setText(currIntent.getStringExtra(Config.CONTACT_NAME));
        mEmail.setText(currIntent.getStringExtra(Config.CONTACT_EMAIL));
        mPhone.setText(currIntent.getStringExtra(Config.CONTACT_PHONE));

        resultUri = Uri.parse(currIntent.getStringExtra(Config.CONTACT_IMAGE));
        sex = currIntent.getStringExtra(Config.CONTACT_SEX);
        id = currIntent.getIntExtra(Config.CONTACT_ID,0);
        if(sex.equals("Male")){
            mMale.performClick();
        }else if(sex.equals("Female")){
            mFemale.performClick();
        }

        mHobbies.setText(currIntent.getStringExtra(Config.CONTACT_HOBBIES));

        mBeautyMeter.setProgress(Integer.parseInt(currIntent.getStringExtra(Config.CONTACT_BEAUTY)));

        //OnClick
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
                    contact.setContact_id(id);

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

                    contact.setContact_hobbies(mHobbies.getText().toString());


                    //Seekbar
                    mBeautyMeter.setMax(100);
                    contact.setContact_beauty(String.valueOf(mBeautyMeter.getProgress()));



                    showPopUp(contact);


                } catch (Exception e) {
                    Log.d("STATUS", " UPDATE ERROR"+e);
                }


            }
        });

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Delete this item?");
                builder.setTitle("Attention");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext,"Data Deleted", Toast.LENGTH_SHORT).show();

                        String hobbies ="";
                        Contact contact = new Contact();
                        contact.setContact_id(id);

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

                        contact.setContact_hobbies(mHobbies.getText().toString());


                        //Seekbar
                        mBeautyMeter.setMax(100);
                        contact.setContact_beauty(String.valueOf(mBeautyMeter.getProgress()));



                        mContactDB.deleteContact(contact);
                        dialog.dismiss();
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog mDialog = builder.create();
                mDialog.show();
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

        builder.setTitle("Save ?");
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
                mContactDB.updateContact(contact);
                Toast.makeText(mContext,"Update Success",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                mContactImage.setImageURI(resultUri);
                mContactImage.setPadding(0,0,0,0);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
