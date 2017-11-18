package ch.heig.sym_labo2.activities;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ch.heig.sym_labo2.R;
import ch.heig.sym_labo2.SymComManager.SCMJsonObject;
import ch.heig.sym_labo2.SymComManager.SCMXmlObject;

public class ObjectSendRequestActivity extends SCMActivities {

    // Attributs
    private Button bigJsonButton = null;
    private TextView responseText = null;
    private SCMJsonObject scmJsonObject = null;
    private SCMXmlObject scmXmlObject = null;


    private Button personJsonButton = null;
    private Button personXMLButton = null;
    private EditText name = null;
    private EditText firstname = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_send_request);

        bigJsonButton = (Button) findViewById(R.id.bigJsonSendButton);
        name = (EditText) findViewById(R.id.personNameEditText);
        firstname = (EditText) findViewById(R.id.personNameEditText);
        responseText = (TextView) findViewById(R.id.jsonResponseFromServerTextView);
        responseText.setMovementMethod(new ScrollingMovementMethod());
        personJsonButton = (Button) findViewById(R.id.personJsonButton);
        personXMLButton = (Button) findViewById(R.id.personXMLButton);

        scmJsonObject = new SCMJsonObject(this);
        scmXmlObject = new SCMXmlObject(this);

        bigJsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    scmJsonObject.sendRequest("big.json", "http://sym.iict.ch/rest/json");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        personXMLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    scmXmlObject.sendPerson(name.getText().toString(), firstname.getText().toString(), "http://sym.iict.ch/rest/xml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        personJsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    scmJsonObject.sendPerson(name.getText().toString(), firstname.getText().toString(), "http://sym.iict.ch/rest/json");
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
