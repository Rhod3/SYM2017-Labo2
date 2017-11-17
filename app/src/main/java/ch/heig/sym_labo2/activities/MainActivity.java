package ch.heig.sym_labo2.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ch.heig.sym_labo2.R;
import ch.heig.sym_labo2.SymComManager.SCMDelayed;

public class MainActivity extends AppCompatActivity {

    private Button button1 = null;
    private Button button2 = null;
    private Button button3 = null;
    private Button button4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.button1 = (Button) findViewById(R.id.button_ex1);
        this.button2 = (Button) findViewById(R.id.button_ex2);
        this.button3 = (Button) findViewById(R.id.button_ex3);
        this.button4 = (Button) findViewById(R.id.button_ex4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AsyncSendRequestActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonAction(DelayedRequestActivity.class);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ObjectSendRequestActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CompressedSendRequestActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setButtonAction(Class c) {
        Intent intent = new Intent(MainActivity.this, c);
        startActivity(intent);
    }
}
