package ch.heig.sym_labo2.SymComManager;

import ch.heig.sym_labo2.activities.SCMActivities;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;


public class SCMJsonObject extends SCMAsyncSendRequest {

    public SCMJsonObject(SCMActivities activity){
        super(activity);
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
}
