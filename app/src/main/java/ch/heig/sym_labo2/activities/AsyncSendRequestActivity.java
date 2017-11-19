package ch.heig.sym_labo2.activities;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ch.heig.sym_labo2.R;
import ch.heig.sym_labo2.SymComManager.SCMAsyncSendRequest;
import ch.heig.sym_labo2.SymComManager.SymComManager;

/**
 * Activité permettant de tester la méthode de transmission de requête asynchrone.
 */
public class AsyncSendRequestActivity extends SCMActivities {

    // Attributs
    private Button sendButton = null;
    private EditText inputText = null;
    private TextView responseText = null;
    private SymComManager symComManager = null;

    // Méthodes de gestion de l'activité
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_async_send_request);

        sendButton = (Button) findViewById(R.id.asyncSendButton);
        inputText = (EditText) findViewById(R.id.inputText);
        responseText = (TextView) findViewById(R.id.responseFromServerTextView);
        responseText.setMovementMethod(new ScrollingMovementMethod());

        symComManager = new SCMAsyncSendRequest(this);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Envoi de la requête contenant le texte saisi par l'utilisateur au serveur
                    symComManager.sendRequest(inputText.getText().toString(), "http://sym.iict.ch/rest/txt");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setResponseText(String text) {
        responseText.setText(text);
    }
}