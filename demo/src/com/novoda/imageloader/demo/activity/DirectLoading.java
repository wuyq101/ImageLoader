package com.novoda.imageloader.demo.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.novoda.imageloader.core.util.DirectLoader;
import com.novoda.imageloader.demo.R;

public class DirectLoading extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direct_loading);
        imageView = (ImageView) findViewById(R.id.direct_image);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
         * TODO Don't use the direct loader like this.
         * It's useful for downloading a Bitmap to display in a notification.
         * You need to handle the threading yourself.
         */
        new Thread() {
            public void run() {
                DirectLoader dl = new DirectLoader();
                Bitmap b = dl.download("http://www.asianweek.com/wp-content/uploads/2012/03/microsoft_logo11.jpg");
                setImageView(b);
            };
        }.start();
    }

    public void setImageView(final Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }

}
