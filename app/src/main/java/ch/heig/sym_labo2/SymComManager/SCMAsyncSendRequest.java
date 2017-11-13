package ch.heig.sym_labo2.SymComManager;

import android.app.Activity;

import java.io.IOException;

import ch.heig.sym_labo2.activities.AsyncSendRequestActivity;
import ch.heig.sym_labo2.activities.SCMActivities;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SCMAsyncSendRequest extends SymComManager {

    public SCMAsyncSendRequest(SCMActivities activity) {
        super(activity);
    }

    @Override
    public Callback responseCallback() {
        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String tmp = "";
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        tmp += responseHeaders.name(i) + ": " + responseHeaders.value(i) + "\n";
                    }
                    tmp += responseBody.string();
                    final String res = tmp;

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Ugly cast
                            getActivity().setResponseText(res);
                        }
                    });
                }
            }
        };
    }

    @Override
    public Request buildPostRequest(String payload, String url) {
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("text/plain; charset=utf-8"),
                payload
        );
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("content-type", "plain/text")
                .build();
    }
}
