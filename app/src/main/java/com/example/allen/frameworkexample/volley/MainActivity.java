package com.example.allen.frameworkexample.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.allen.frameworkexample.R;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

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
        getJson();
        getImage();

    }

    private void getJson() {
        //新建一个请求
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://www.mocky.io/v2/56c30376120000be2e73f210", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("mTAG", response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("mTAG", error.getMessage(), error);
            }
        });
        //添加请求到队列
        App.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void getImage() {

    }


}
