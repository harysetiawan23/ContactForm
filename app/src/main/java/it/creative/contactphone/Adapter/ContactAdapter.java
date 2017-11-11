package it.creative.contactphone.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import it.creative.contactphone.Model.Contact;
import it.creative.contactphone.R;

/**
 * Created by agush on 10/4/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<Contact> mList;
    private Context mContext;

    public ContactAdapter(ArrayList<Contact> mContact,Context context){
        this.mList = mContact;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_frame,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mContactImage.setImageURI(Uri.parse(mList.get(position).getContact_img_uri()));
        holder.mContactName.setText(mList.get(position).getContact_name());
        holder.mContactEmail.setText(mList.get(position).getContact_email());
        holder.mContactPhone.setText(mList.get(position).getContact_phone());
        holder.mContactBeauty.setText(mList.get(position).getContact_beauty()+"%");
        holder.mContactHobbies.setText(mList.get(position).getContact_hobbies());
        holder.mContactSex.setText("( "+mList.get(position).getContact_sex()+" )");


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
