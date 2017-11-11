package it.creative.contactphone.Font;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by agush on 12/08/2017.
 */

public class RobotoBold extends TextView {

    public RobotoBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RobotoBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoBold(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Bold.ttf");
        setTypeface(tf ,1);

    }
}