package com.example.movieapp.data.remote;

import com.example.movieapp.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
 public class MovieApiClient {
     private static final String BASE_URL = BuildConfig.BASE_URL;

    //private static final String BASE_URL = "https://imdb236.p.rapidapi.com/";
     private static final String API_KEY = BuildConfig.API_KEY;

    private static Retrofit retrofit;


   public static Retrofit getInstance() {
       if (retrofit == null) {
           // Create an OkHttpClient with an Interceptor to add headers
           OkHttpClient client = new OkHttpClient.Builder()
                   .addInterceptor(new Interceptor() {
                       @Override
                       public Response intercept(Chain chain) throws IOException {
                           Request original = chain.request();
                           Request request = original.newBuilder()
                                   .header("x-rapidapi-key", API_KEY) // Add API Key Header
                                   .header("x-rapidapi-host", "imdb236.p.rapidapi.com") // Required by RapidAPI
                                   .method(original.method(), original.body())
                                   .build();
                           return chain.proceed(request);
                       }
                   })
                   .build();

           // Build Retrofit instance with the OkHttpClient
           retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .client(client) // Set custom client
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       }
       return retrofit;
   }
}
