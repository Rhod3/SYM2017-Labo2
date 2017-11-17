package ch.heig.sym_labo2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ch.heig.sym_labo2.R;
import ch.heig.sym_labo2.SymComManager.SCMDelayed;
import ch.heig.sym_labo2.SymComManager.SCMJsonObject;
import ch.heig.sym_labo2.SymComManager.SymComManager;

public class DelayedRequestActivity extends SCMActivities {

    private Button sendButton = null;
    private EditText inputText = null;
    private TextView responseText = null;
    private SymComManager symComManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delayed_request);

        sendButton = (Button) findViewById(R.id.delayedSendButton);
        inputText = (EditText) findViewById(R.id.delayedInputText);
        responseText = (TextView) findViewById(R.id.delayedResponseTextView);
        responseText.setMovementMethod(new ScrollingMovementMethod());

        symComManager = new SCMDelayed(this);

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

    @Override
    public void setResponseText(String text) {
        responseText.setText(text);
    }
}
