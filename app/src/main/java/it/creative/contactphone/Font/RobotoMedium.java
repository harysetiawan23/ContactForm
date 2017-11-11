package it.creative.contactphone.Font;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by agush on 12/08/2017.
 */

public class RobotoMedium extends TextView {

    public RobotoMedium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RobotoMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoMedium(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Medium.ttf");
        setTypeface(tf ,1);

    }
}