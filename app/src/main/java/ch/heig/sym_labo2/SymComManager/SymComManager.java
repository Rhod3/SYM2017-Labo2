package ch.heig.sym_labo2.SymComManager;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public abstract class SymComManager extends Thread {

    private Activity activity;
    private final String mainURL = "http://sym.iict.ch/";
    private static OkHttpClient client = new OkHttpClient.Builder()
                                            .connectTimeout(5, TimeUnit.SECONDS)
                                            .writeTimeout(5, TimeUnit.SECONDS)
                                            .readTimeout(5, TimeUnit.SECONDS)
                                            .build();

    public SymComManager(Activity activity){
        this.activity = activity;
    }

    public void run() {
        // compute primes larger than minPrime
    }

    public void sendRequest(String payload, String url) throws Exception {
        Request request = buildRequest(payload, url);
        client.newCall(request).enqueue(responseCallback());
    }

    public Activity getActivity(){
        return activity;
    }

    public String getMainURL(){
        return mainURL;
    }

    public abstract Callback responseCallback();

    public abstract Request buildRequest(String request, String url);
}
