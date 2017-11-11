package it.creative.contactphone.Font;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by agush on 12/08/2017.
 */

public class RobotoThin extends TextView {

    public RobotoThin(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RobotoThin(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoThin(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Thin.ttf");
        setTypeface(tf ,1);

    }
}