package it.creative.contactphone.Font;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by agush on 12/08/2017.
 */

public class FuturaBold extends TextView {

    public FuturaBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FuturaBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FuturaBold(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/FuturaCondensedBold.ttf");
        setTypeface(tf ,1);

    }
}