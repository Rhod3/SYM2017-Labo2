package ch.heig.sym_labo2.SymComManager;

import java.io.IOException;
import java.io.InputStream;

import ch.heig.sym_labo2.activities.SCMActivities;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;


public class SCMJsonObject extends SCMAsyncSendRequest {

    public SCMJsonObject(SCMActivities activity){
        super(activity);
    }

    public void sendRequest(String filename, String url) throws Exception {
        String json = loadJSONFromAsset(filename);
        Request request = buildPostRequest(json, url);
        getClient().newCall(request).enqueue(responseCallback());
    }

    @Override
    public Request buildPostRequest(String payload, String url) {
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                payload
        );
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .build();
    }

    private String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private String loadJsonFromFile(String path){
        return null;
    }
}
