package ch.heig.sym_labo2.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import ch.heig.sym_labo2.R;
import ch.heig.sym_labo2.SymComManager.SCMCompressed;
import ch.heig.sym_labo2.SymComManager.SymComManager;

/**
 * Activité permettant de tester la méthode de transmission de requête compressée.
 */
public class CompressedSendRequestActivity extends SCMActivities {

    // Attributs
    private Button sendButton = null;
    private RadioGroup fileChoice = null;
    private RadioButton fileChose = null;
    private TextView responseText = null;
    private SymComManager symComManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compressed_send_request);

        sendButton = (Button) findViewById(R.id.compressedSendButton);
        fileChoice = (RadioGroup) findViewById(R.id.compressedFilesRadioGroup);
        responseText = (TextView) findViewById(R.id.compressedResponseFromServerTextView);
        responseText.setMovementMethod(new ScrollingMovementMethod());

        symComManager = new SCMCompressed(this);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedRadioButtonID = fileChoice.getCheckedRadioButtonId();
                fileChose = (RadioButton) findViewById(checkedRadioButtonID);

                try {
                    //Envoi de la requête contenant le JSON contenue dans le fichier choisi par l'utilisateur au serveur
                    symComManager.sendRequest(fileChose.getText().toString(), "http://sym.iict.ch/rest/json");
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
