package ch.heig.sym_labo2.activities;

import android.support.v7.app.AppCompatActivity;

/**
 * Classe parente de toutes les activités implémentant une méthode de transmission de requêtes différente.
 */
public abstract class SCMActivities extends AppCompatActivity {

    /**
     * Traitement de la réponse du serveur (typiquement affichage à l'utilisateur).
     * @param text la réponse du serveur à traiter
     */
    public abstract void setResponseText(String text);
}
