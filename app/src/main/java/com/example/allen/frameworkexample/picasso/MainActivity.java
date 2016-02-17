package com.example.allen.frameworkexample.picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.allen.frameworkexample.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen Lin on 2016/02/10.
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textview)
    TextView mTextview;
    @Bind(R.id.imageview)
    ImageView mImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("picasso example");
        Picasso.with(this).setIndicatorsEnabled(true);
        Picasso.with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .resize(800, 800)
                .centerCrop()
                .placeholder(R.mipmap.ic_default)
                .error(R.mipmap.ic_error)
                .into(mImageview);
    }
}
