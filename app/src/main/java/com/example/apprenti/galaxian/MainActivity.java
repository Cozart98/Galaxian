package com.example.apprenti.galaxian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.acl.LastOwnerException;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference utilisateurRef = database.getReference("Utilisateur");





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final EditText editTextNom = (EditText) findViewById(R.id.nomLogin);
        final EditText editTextmdp = (EditText) findViewById(R.id.mdp);
        final EditText editTextscore = (EditText) findViewById(R.id.meilleurScore);
        final Button btAjout = (Button) findViewById(R.id.buttonEnvoyer);
        final Button btBest = (Button) findViewById(R.id.buttonScore);

        btAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = editTextNom.getText().toString();
                String mdp = editTextmdp.getText().toString();
                String score = editTextscore.getText().toString();
                if(nom.equals("") || mdp.equals("") || score.equals("")) {
                    Toast.makeText(getApplicationContext(),"Veillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                }
                else {
                    int nbScore = Integer.valueOf(score);
                    GalaxianModel utilisateur = new GalaxianModel(nom, mdp, nbScore);
                    String id = utilisateurRef.push().getKey();
                    utilisateurRef.child(id).setValue(utilisateur);
                    editTextNom.setText("");
                    editTextmdp.setText("");
                    editTextscore.setText("");


                }
            }
        });




        btBest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                utilisateurRef.orderByChild("score").limitToLast(1)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot utilisateurSnapshot : dataSnapshot.getChildren()) {
                                    GalaxianModel utilisateur =
                                            utilisateurSnapshot.getValue(GalaxianModel.class);

                                    Toast.makeText(MainActivity.this, utilisateur.getNom() +" "+ utilisateur.getScore(),
                                            Toast.LENGTH_LONG).show();

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {}
                        });

            }
        });


    }
}
