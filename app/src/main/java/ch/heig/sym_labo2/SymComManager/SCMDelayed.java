package ch.heig.sym_labo2.SymComManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.util.Pair;

import java.util.ArrayList;

import ch.heig.sym_labo2.activities.SCMActivities;


public class SCMDelayed extends SCMAsyncSendRequest {

    private ArrayList<Pair<String, String>> waitingRequests = new ArrayList<>();

    public SCMDelayed (SCMActivities activity){
        super(activity);
    }

    public void sendRequest(String payload, String url) throws Exception {
        if (isNetworkAvailable()) {
            super.sendRequest(payload, url);
        } else {

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
