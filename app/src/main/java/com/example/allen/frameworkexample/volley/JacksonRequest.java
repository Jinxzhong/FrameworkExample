package com.example.allen.frameworkexample.volley;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * @param <T>
 * @author Mr.Zheng
 * @date 2014年10月18日 下午9:25:19
 */
public class JacksonRequest<T> extends Request<T> {

    private final Listener<T> mListener;

    private static ObjectMapper objectMapper = new ObjectMapper();

    private Class<T> mClass;

    private TypeReference<T> mTypeReference;//提供解析复杂JSON数据支持

    public JacksonRequest(int method, String url, Class<T> clazz, Listener<T> listener,
                          ErrorListener errorListener) {
        super(method, url, errorListener);
        mClass = clazz;
        mListener = listener;
    }

    public JacksonRequest(int method, String url, TypeReference<T> typeReference, Listener<T> listener,
                          ErrorListener errorListener) {
        super(method, url, errorListener);
        mTypeReference = typeReference;
        mListener = listener;
    }

    public JacksonRequest(String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }

    public JacksonRequest(String url, TypeReference<T> typeReference, Listener<T> listener,
                          ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        mTypeReference = typeReference;
        mListener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.v("mTAG", "json");
            if (mTypeReference == null)//使用Jackson默认的方式解析到mClass类对象

                return (Response<T>) Response.success(
                        objectMapper.readValue(jsonString, TypeFactory.rawClass(mClass)),
                        HttpHeaderParser.parseCacheHeaders(response));
            else//通过构造TypeReference让Jackson解析成自定义的对象类型
                return (Response<T>) Response.success(objectMapper.readValue(jsonString, mTypeReference),
                        HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

}