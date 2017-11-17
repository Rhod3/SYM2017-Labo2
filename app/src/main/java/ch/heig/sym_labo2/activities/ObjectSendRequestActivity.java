package ch.heig.sym_labo2.activities;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ch.heig.sym_labo2.R;
import ch.heig.sym_labo2.SymComManager.SCMJsonObject;
import ch.heig.sym_labo2.SymComManager.SymComManager;

public class ObjectSendRequestActivity extends SCMActivities {

    // Attributs
    private Button bigJsonButton = null;
    private EditText inputText = null;
    private TextView responseText = null;
    private SCMJsonObject symComManager = null;

    private Button fruitJsonButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_send_request);

        bigJsonButton = (Button) findViewById(R.id.bigJsonSendButton);
        responseText = (TextView) findViewById(R.id.jsonResponseFromServerTextView);
        responseText.setMovementMethod(new ScrollingMovementMethod());
        fruitJsonButton = (Button) findViewById(R.id.fruitJsonButton);

        symComManager = new SCMJsonObject(this);

        bigJsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    symComManager.sendRequest("big.json", "http://sym.iict.ch/rest/json");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        fruitJsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    symComManager.sendFruit("http://sym.iict.ch/rest/json");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void setResponseText(String text) {
        responseText.setText(text);
    }
}
