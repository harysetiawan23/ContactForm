package it.creative.contactphone.Model;

import android.app.Application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

/**
 * Created by agush on 11/11/2017.
 */

public class contactphone extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso build = builder.build();
        build.setIndicatorsEnabled(false);
        build.setLoggingEnabled(true);
        Picasso.setSingletonInstance(build);
    }
}
