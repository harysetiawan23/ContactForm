package it.creative.contactphone.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import it.creative.contactphone.Controller.DatabaseHelper;
import it.creative.contactphone.Model.Config;
import it.creative.contactphone.Model.Contact;
import it.creative.contactphone.R;
import it.creative.contactphone.View.EditContact;

/**
 * Created by agush on 10/4/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<Contact> mList;
    private Context mContext;
    private DatabaseHelper mDatabase;

    public ContactAdapter(ArrayList<Contact> mContact,Context context){
        this.mList = mContact;
        this.mContext = context;
        this.mDatabase = new DatabaseHelper(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_frame,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mContactName.setText(mList.get(position).getContact_name());
        holder.mContactEmail.setText(mList.get(position).getContact_email());
        holder.mContactPhone.setText(mList.get(position).getContact_phone());
        holder.mContactBeauty.setText(mList.get(position).getContact_beauty()+"%");
        holder.mContactHobbies.setText(mList.get(position).getContact_hobbies());
        holder.mContactSex.setText("( "+mList.get(position).getContact_sex()+" )");
        Picasso.with(mContext)
                .load(Uri.parse(mList.get(position).getContact_img_uri()))
                .networkPolicy(NetworkPolicy.OFFLINE)
//                .resize(735, 460)
//                .centerCrop()
                .into(holder.mContactImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(mContext)
                                .load(Uri.parse(mList.get(position).getContact_img_uri()))
//                                .resize(735, 460)
//                                .centerCrop()
                                .into(holder.mContactImage);
                    }
                });
        holder.mFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editContact= new Intent(mContext, EditContact.class);
                editContact.putExtra(Config.CONTACT_NAME,mList.get(position).getContact_name());
                editContact.putExtra(Config.CONTACT_ID,mList.get(position).getContact_id());
                editContact.putExtra(Config.CONTACT_BEAUTY,mList.get(position).getContact_beauty());
                editContact.putExtra(Config.CONTACT_EMAIL,mList.get(position).getContact_email());
                editContact.putExtra(Config.CONTACT_HOBBIES,mList.get(position).getContact_hobbies());
                editContact.putExtra(Config.CONTACT_SEX,mList.get(position).getContact_sex());
                editContact.putExtra(Config.CONTACT_PHONE,mList.get(position).getContact_phone());
                editContact.putExtra(Config.CONTACT_IMAGE,mList.get(position).getContact_img_uri());
                ((Activity)mContext).startActivity(editContact);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mContactImage;
        private TextView mContactName,mContactEmail,mContactPhone,mContactBeauty,mContactHobbies,mContactSex;
        private FrameLayout mFrame;
        public ViewHolder(View itemView) {
            super(itemView);
            mContactImage = (ImageView)itemView.findViewById(R.id.contact_image);
            mContactName = (TextView)itemView.findViewById(R.id.contact_name);
            mContactEmail = (TextView)itemView.findViewById(R.id.contact_email);
            mContactPhone = (TextView)itemView.findViewById(R.id.contact_phone);
            mContactBeauty = (TextView)itemView.findViewById(R.id.contact_beauty);
            mContactHobbies = (TextView)itemView.findViewById(R.id.contact_hobbies);
            mContactSex = (TextView)itemView.findViewById(R.id.contact_sex);
            mFrame = (FrameLayout)itemView.findViewById(R.id.contact_layout);

        }
    }

}
