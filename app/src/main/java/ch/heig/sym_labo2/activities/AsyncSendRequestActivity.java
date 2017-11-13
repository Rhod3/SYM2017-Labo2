package ch.heig.sym_labo2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ch.heig.sym_labo2.R;
import ch.heig.sym_labo2.SymComManager.SCMAsyncSendRequest;
import ch.heig.sym_labo2.SymComManager.SymComManager;

public class AsyncSendRequestActivity extends AppCompatActivity {

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

        symComManager = new SCMAsyncSendRequest(this);
        symComManager.start();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
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

    public void toast(String text) {
        Toast.makeText(this.getApplicationContext(), text, Toast.LENGTH_SHORT);
    }
}