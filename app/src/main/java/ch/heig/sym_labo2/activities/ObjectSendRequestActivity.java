package ch.heig.sym_labo2.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ch.heig.sym_labo2.R;
import ch.heig.sym_labo2.SymComManager.SCMJsonObject;
import ch.heig.sym_labo2.SymComManager.SymComManager;

public class ObjectSendRequestActivity extends SCMActivities {

    // Attributs
    private Button sendButton = null;
    private EditText inputText = null;
    private TextView responseText = null;
    private SymComManager symComManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_send_request);

        sendButton = (Button) findViewById(R.id.objectSendButton);
        inputText = (EditText) findViewById(R.id.inputJson);
        responseText = (TextView) findViewById(R.id.jsonResponseFromServerTextView);

        symComManager = new SCMJsonObject(this);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    symComManager.sendRequest(inputText.getText().toString(), "http://sym.iict.ch/rest/json");
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
