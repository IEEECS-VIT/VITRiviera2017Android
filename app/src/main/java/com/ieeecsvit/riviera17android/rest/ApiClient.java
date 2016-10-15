package com.ieeecsvit.riviera17android.rest;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://riviera-2017-api.herokuapp.com/api/v1/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(final Context context) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (retrofit == null) {
            OkHttpClient ok = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder().addHeader("Authorization", Auth.getToken(context)
                            ).build();
                            return chain.proceed(request);
                        }
                    })
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(ok.newBuilder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).build())
                    .build();
        }
        return retrofit;
    }
}
