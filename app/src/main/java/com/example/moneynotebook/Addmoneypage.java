package com.example.moneynotebook;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;


public class Addmoneypage extends AppCompatActivity {
    Button addmoney;
    EditText name , rupees;
    Post post;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference , databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmoneypage);
        addmoney = findViewById(R.id.addmoney);
        name = findViewById(R.id.name);
        rupees = findViewById(R.id.rupees);
        post = new Post();
        Calendar calendar = Calendar.getInstance();
        String curruentdate = DateFormat.getInstance().format(calendar.getTime());
        databaseReference = firebaseDatabase.getInstance().getReference().child("users");
        databaseReference1 = firebaseDatabase.getInstance().getReference().child("backup");

// Write a message to the database
     addmoney.setOnClickListener(view -> {
                if(TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(rupees.getText().toString())){
                    Toast.makeText(Addmoneypage.this, "Please enter text", Toast.LENGTH_SHORT).show();

            }
                else {
                    if (checkConnection()) {
                        post.setName(name.getText().toString());
                        post.setRupees(rupees.getText().toString());
                        post.setStatus("notpaid");

                        String id = databaseReference.push() .getKey();
                        databaseReference.child(id).setValue(post);


                        post.setName(name.getText().toString());
                        post.setRupees(rupees.getText().toString());
                        post.setStatus(curruentdate);

                        String id1 = databaseReference.push() .getKey();
                        databaseReference1.child(id1).setValue(post);

                        name.setText(null);
                        rupees.setText(null);

                    }
                    else {
                        Toast.makeText(Addmoneypage.this, "No internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        boolean checkConnection() {
            ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activenetwork = manager.getActiveNetworkInfo();
            if (null != activenetwork) {
                return activenetwork.getType() == ConnectivityManager.TYPE_WIFI || activenetwork.getType() == ConnectivityManager.TYPE_MOBILE;
            }
            else {
                return false;
            }
        }
    }

