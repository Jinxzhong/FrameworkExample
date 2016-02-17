package com.example.allen.frameworkexample.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.allen.frameworkexample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen Lin on 2016/02/17.
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    @Bind(R.id.textview)
    TextView mTextview;
    @Bind(R.id.imageview)
    ImageView mImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("volley example");
        getJson();
        getImage();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (App.getInstance() != null) {
            App.getInstance().cancelRequests(TAG);
        }

    }

    private void getJson() {
        //新建一个JsonObject请求
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://www.mocky.io/v2/56c33f991200002d3773f261", null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, response.toString());
//                        mTextview.setText(response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, error.getMessage(), error);
//            }
//        });
        //新建一个JsonObject请求
        GsonRequest<Person> gsonRequest = new GsonRequest<Person>(
                "http://www.mocky.io/v2/56c33f991200002d3773f261", Person.class,
                new Response.Listener<Person>() {
                    @Override
                    public void onResponse(Person person) {
                        Log.d(TAG, "first_name: " + person.getFirst_name());
                        Log.d(TAG, "last_name: " + person.getLast_name());
                        Log.d(TAG, "gender: " + person.getGender());
                        mTextview.setText("first_name: " + person.getFirst_name() + "\n"
                                + "last_name: " + person.getLast_name() + "\n" +
                                "gender: " + person.getGender());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        });

        //添加请求到队列
        App.getInstance().addRequest(gsonRequest, TAG);
    }

    private void getImage() {
        ImageLoader imageLoader = App.getInstance().getImageLoader();
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(mImageview,
                R.mipmap.ic_default, R.mipmap.ic_error);
        imageLoader.get("https://d262ilb51hltx0.cloudfront.net/max/800/1*dWGwx6UUjc0tocYzFNBLEw.jpeg",
                listener, 800, 800);
    }


}
