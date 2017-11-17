package ch.heig.sym_labo2.SymComManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.util.Pair;

import java.util.ArrayList;

import ch.heig.sym_labo2.activities.SCMActivities;


public class SCMDelayed extends SCMAsyncSendRequest {

    private final ArrayList<Pair<String, String>> waitingRequests;
    private boolean firstTimeNoConnection;

    public SCMDelayed (SCMActivities activity){
        super(activity);
        waitingRequests = new ArrayList<>();
        firstTimeNoConnection = false;
    }

    public void sendRequest(String payload, String url) throws Exception {
        if (isNetworkAvailable()) {
            super.sendRequest(payload, url);
        } else {
            waitingRequests.add(new Pair<String, String>(payload, url));

            if (!firstTimeNoConnection) {
                firstTimeNoConnection = true;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isNetworkAvailable() && (waitingRequests.size() > 0)) {
                            Pair<String, String> request = waitingRequests.remove(0);
                            try {
                                SCMDelayed.super.sendRequest(request.first, request.second);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
