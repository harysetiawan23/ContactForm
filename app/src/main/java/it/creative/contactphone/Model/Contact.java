package it.creative.contactphone.Model;

/**
 * Created by agush on 10/4/2017.
 */

public class Contact {
    private String contact_img_uri,contact_name,contact_email,contact_phone,contact_sex,contact_hobbies,contact_beauty;
    private int contact_id;
    public Contact(){

    }

    public Contact(String contact_img_uri, String contact_name, String contact_email, String contact_phone, String contact_sex, String contact_hobbies, String contact_beauty, int contact_id) {
        this.contact_img_uri = contact_img_uri;
        this.contact_name = contact_name;
        this.contact_email = contact_email;
        this.contact_phone = contact_phone;
        this.contact_sex = contact_sex;
        this.contact_hobbies = contact_hobbies;
        this.contact_beauty = contact_beauty;
        this.contact_id = contact_id;
    }

    public String getContact_img_uri() {
        return contact_img_uri;
    }

    public void setContact_img_uri(String contact_img_uri) {
        this.contact_img_uri = contact_img_uri;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_sex() {
        return contact_sex;
    }

    public void setContact_sex(String contact_sex) {
        this.contact_sex = contact_sex;
    }

    public String getContact_hobbies() {
        return contact_hobbies;
    }

    public void setContact_hobbies(String contact_hobbies) {
        this.contact_hobbies = contact_hobbies;
    }

    public String getContact_beauty() {
        return contact_beauty;
    }

    public void setContact_beauty(String contact_beauty) {
        this.contact_beauty = contact_beauty;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }
}
