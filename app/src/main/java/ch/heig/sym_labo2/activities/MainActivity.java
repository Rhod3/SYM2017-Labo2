package ch.heig.sym_labo2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ch.heig.sym_labo2.R;

/**
 * Activité principale de l'application. Nous pouvons accéder les différentes activités permettant
 * de tester chaque méthode de transmissions de requêtes depuis cette activité.
 */
public class MainActivity extends AppCompatActivity {

    private Button buttonAsync = null;
    private Button buttonDelayed = null;
    private Button buttonSerialize = null;
    private Button buttonCompressed = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Association des boutons de l'interface logique aux boutons déclarés dans la classe

        this.buttonAsync = (Button) findViewById(R.id.button_ex1);
        this.buttonDelayed = (Button) findViewById(R.id.button_ex2);
        this.buttonSerialize = (Button) findViewById(R.id.button_ex3);
        this.buttonCompressed = (Button) findViewById(R.id.button_ex4);

        // Association d'un comportement à l'événement de click sur chaque bouton (Envoi sur l'activité associée)

        buttonAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AsyncSendRequestActivity.class);
                startActivity(intent);
            }
        });
        buttonDelayed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DelayedSendRequestActivity.class);
                startActivity(intent);
            }
        });
        buttonSerialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ObjectSendRequestActivity.class);
                startActivity(intent);
            }
        });
        buttonCompressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CompressedSendRequestActivity.class);
                startActivity(intent);
            }
        });
    }
}
