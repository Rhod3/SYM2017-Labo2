package ch.heig.sym_labo2.SymComManager;

import android.app.Activity;
import android.widget.Toast;

import java.io.IOException;

import ch.heig.sym_labo2.activities.AsyncSendRequestActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SCMAsyncSendRequest extends SymComManager {

    public SCMAsyncSendRequest(Activity activity) {
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
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Cast is ugly
                            ((AsyncSendRequestActivity) getActivity()).setResponseText(response.toString());
                        }
                    });
                }
            }
        };
    }

    @Override
    public Request buildRequest(String payload, String url) {
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
