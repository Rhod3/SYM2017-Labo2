package ch.heig.sym_labo2.SymComManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ch.heig.sym_labo2.activities.SCMActivities;

/**
 * Classe de traitement permettant de stocker les requêtes dans le cas où le serveur n'est pas accessible
 * et de les envoyer lorsque le serveur devient accessible.
 */
public class SCMDelayed extends SCMAsyncSendRequest {

    private final ArrayList<Pair<String, String>> waitingRequests;
    private boolean firstTimeNoConnection;

    public SCMDelayed (SCMActivities activity){
        super(activity);
        waitingRequests = new ArrayList<>();
        firstTimeNoConnection = false;
    }

    public void sendRequest(String payload, String url) throws Exception {
        final int timeBetweenTasks = 3;

        if (isNetworkAvailable()) {
            super.sendRequest(payload, url);
        } else {
            waitingRequests.add(new Pair<String, String>(payload, url));

            if (!firstTimeNoConnection) {
                firstTimeNoConnection = true;

                ScheduledExecutorService scheduler =
                        Executors.newSingleThreadScheduledExecutor();

                scheduler.scheduleAtFixedRate
                    (new Runnable() {
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
                    }, 0, timeBetweenTasks, TimeUnit.SECONDS);
            }
        }
    }

    /**
     * Indique si l'appareil faisant tourner l'application est connecté au réseau
     * @return vrai si l'appareil est connecté au réseau. Faux sinon.
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
