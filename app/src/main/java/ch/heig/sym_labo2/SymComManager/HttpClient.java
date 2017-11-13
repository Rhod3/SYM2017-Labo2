package ch.heig.sym_labo2.SymComManager;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class HttpClient {
    private static OkHttpClient okHttpClient = null;

    public static OkHttpClient getClient() {
        if (okHttpClient != null) {
            return okHttpClient;
        } else {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .build();
            return okHttpClient;
        }
    }
}
