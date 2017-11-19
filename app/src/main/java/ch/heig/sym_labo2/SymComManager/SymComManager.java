package ch.heig.sym_labo2.SymComManager;

import java.util.concurrent.TimeUnit;

import ch.heig.sym_labo2.activities.SCMActivities;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Classe implémentant l'envoi des données au serveur et imposant la création du payload et
 * le traitement de la réponse à ces enfants.
 */
public abstract class SymComManager {

    private SCMActivities activity;
    private static OkHttpClient client = new OkHttpClient.Builder()
                                            .connectTimeout(30, TimeUnit.SECONDS)
                                            .writeTimeout(30, TimeUnit.SECONDS)
                                            .readTimeout(30, TimeUnit.SECONDS)
                                            .build();

    /**
     * Constructeur
     * @param activity l'activité en lien avec les information à envoyer et les informations reçues
     */
    public SymComManager(SCMActivities activity){
        this.activity = activity;
    }

    /**
     * Envoi de payload à url (le serveur)
     * @param payload les données à envoyer à url
     * @param url le serveur auquel on désire transmettre payload
     * @throws Exception S'il y a une erreur lors de la transmission avec le serveur
     */
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

    /**
     * Traite la réponse du serveur (typiquement en affichant la réponse dans activity)
     * @return  une méthode qui sera utilisée pour gérer le comportement à avoir lorsque le serveur répond
     */
    public abstract Callback responseCallback();

    /**
     * Crée la requête à envoyer à url à partir de payload
     * @param payload les données à envoyer à url
     * @param url le serveur auquel on veut transmettre payload
     * @return la requête crée
     */
    public abstract Request buildPostRequest(String payload, String url);
}
