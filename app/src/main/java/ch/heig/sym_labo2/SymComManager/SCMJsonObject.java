package ch.heig.sym_labo2.SymComManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import ch.heig.sym_labo2.activities.SCMActivities;
import ch.heig.sym_labo2.utils.Person;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SCMJsonObject extends SCMAsyncSendRequest {

    public SCMJsonObject(SCMActivities activity){
        super(activity);
    }

    public void sendPerson(String name, String firstname, String url){
        Person person = new Person(name, firstname);
        Gson gson = new Gson();
        String json = gson.toJson(person);

        Request request = buildPostRequest(json, url);
        getClient().newCall(request).enqueue(responseCallback());
    }

    public void sendRequest(String filename, String url) throws Exception {
        String json = loadJSONFromAsset(filename);
        int tmp = json.getBytes().length;
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

    protected String loadJSONFromAsset(String filename) {
        String json;
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
}
