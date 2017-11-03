package ch.heig.sym_labo2.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ch.heig.sym_labo2.R;
import ch.heig.sym_labo2.utils.CommunicationEventListener;

public class AsyncSendRequestActivity extends AppCompatActivity {

    // Classe privée de gestion des appels asynchrones à un serveur
    private class SymComManager extends AsyncTask<String, String, String> {

        public String sendRequest(String request, String url) throws Exception {
            return null;
        }

        public void setCommunicationEventListener(CommunicationEventListener l) {

        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }

    // Attributs
    private Button button = null;
    private EditText inputText = null;
    private final SymComManager mcm = new SymComManager();

    // Méthodes de gestion de l'activité
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_async_send_request);

        this.button = (Button) findViewById(R.id.asyncSendButton);
        this.inputText = (EditText) findViewById(R.id.inputText);

        final SymComManager mcm = new SymComManager();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mcm.sendRequest(inputText.getText().toString(), "http://sym.iict.ch/rest/txt");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}