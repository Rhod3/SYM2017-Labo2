package ch.heig.sym_labo2.SymComManager;

import java.util.concurrent.TimeUnit;

import ch.heig.sym_labo2.activities.SCMActivities;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public abstract class SymComManager {

    private SCMActivities activity;
    private static OkHttpClient client = new OkHttpClient.Builder()
                                            .connectTimeout(5, TimeUnit.SECONDS)
                                            .writeTimeout(5, TimeUnit.SECONDS)
                                            .readTimeout(5, TimeUnit.SECONDS)
                                            .build();

    public SymComManager(SCMActivities activity){
        this.activity = activity;
    }

    public void sendRequest(String payload, String url) throws Exception {
        Request request = buildPostRequest(payload, url);
        client.newCall(request).enqueue(responseCallback());
    }

    public SCMActivities getActivity(){
        return activity;
    }

    public OkHttpClient getClient(){
        return client;
    }

    public abstract Callback responseCallback();

    public abstract Request buildPostRequest(String payload, String url);
}
