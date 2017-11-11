package it.creative.contactphone.Font;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by agush on 17/07/2017.
 */

public class FontFace extends AppCompatActivity{
    private Context mContext;
    public FontFace(Context context){
        mContext = context;
    }

    public  Typeface  regular(){
        Typeface thin = Typeface.createFromAsset(mContext.getAssets(),  "fonts/FuturaCondensedRegular.ttf");
        return thin;
    }
    public  Typeface  bold(){
        Typeface thin = Typeface.createFromAsset(mContext.getAssets(),  "fonts/FuturaCondensedBold.ttf");
        return thin;
    }
    public  Typeface  italic(){
        Typeface thin = Typeface.createFromAsset(mContext.getAssets(),  "fonts/FuturaCondensedItalic.ttf");
        return thin;
    }

}
