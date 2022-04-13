package com.example.moneynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button addmoney , rememberlogin;
    CheckBox rememberme;
    EditText phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addmoney = findViewById(R.id.addmoney);
        phonenumber = findViewById(R.id.phonenumber);
        rememberlogin = findViewById(R.id.rememberlogin);
        rememberme = findViewById(R.id.rememberme);
          SharedPreferences getshared = getSharedPreferences("demo", MODE_PRIVATE);
          String value = getshared.getString("str","false");
          if(value.equals("7739312489")){
              Intent intent  = new Intent(MainActivity.this,postlistActivity.class);
              startActivity(intent);
              finish();
          }
          else if (value.equals("9266215962")){
              Intent intent  = new Intent(MainActivity.this,Addmoneypage.class);
              startActivity(intent);
              finish();
          }

          rememberlogin.setOnClickListener(view -> {
              if(rememberme.isChecked()) {
                  String massage = phonenumber.getText().toString();
                  SharedPreferences shrd = getSharedPreferences("demo", MODE_PRIVATE);
                  SharedPreferences.Editor editor = shrd.edit();
                  editor.putString("str", massage);
                  editor.apply();
                  if(phonenumber.getText().toString().equals("7739312489")){
                      Intent intent  = new Intent(MainActivity.this,postlistActivity.class);
                      startActivity(intent);
                      finish();
                  }
                  else if (phonenumber.getText().toString().equals("9266215962")){
                      Intent intent  = new Intent(MainActivity.this,Addmoneypage.class);
                      startActivity(intent);
                      finish();
                  }
              }
              else{

                  if(phonenumber.getText().toString().equals("7739312489")){
                      Intent intent  = new Intent(MainActivity.this,postlistActivity.class);
                      startActivity(intent);
                      finish();
                  }
                  else if (phonenumber.getText().toString().equals("9266215962")){
                      Intent intent  = new Intent(MainActivity.this,Addmoneypage.class);
                      startActivity(intent);
                      finish();
                  }
                  else {
                      Toast.makeText(MainActivity.this, "You have enter invalid id", Toast.LENGTH_SHORT).show();
                  }
              }
          });


    }


}