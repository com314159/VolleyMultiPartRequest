package com.example.volleytest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

public class OkHttpStack extends MultiPartStack {
   private final OkUrlFactory okUrlFactory;
   public OkHttpStack() {
       this(new OkUrlFactory(new OkHttpClient())); 
   }
   public OkHttpStack(OkUrlFactory okUrlFactory) {
       if (okUrlFactory == null) {
           throw new NullPointerException("Client must not be null.");
       }
       this.okUrlFactory = okUrlFactory;
   }
   @Override
   protected HttpURLConnection createConnection(URL url) throws IOException {
       return okUrlFactory.open(url);
   }
}