package com.example.garbage_managament;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
//import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText UserName;
    private EditText Password;
    private Button submit;
    private Button preparedata;
    private ImageView wait;
    public int counter = 4;
    private boolean isdataloaded = false;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference myRef2;
    DatabaseReference myRef3;
    DatabaseReference myRef4;
    ArrayList<String> li = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRef = database.getInstance().getReference().child("Latitude");
        myRef2 = database.getInstance().getReference().child("Longitude");
        myRef3 = database.getInstance().getReference().child("Percentage");
        myRef4 = database.getInstance().getReference().child("Landmark");
        database = FirebaseDatabase.getInstance();
        UserName = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        submit = findViewById(R.id.Submit);
        preparedata = findViewById(R.id.preparedata);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               validate(UserName.getText().toString(),Password.getText().toString());
            }
        });

        preparedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataLoad();
//                Toast.makeText(MainActivity.this, "Data Loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void validate(String Username,String password){
        if (Username.equals("yash") && password.equals("1109")) {
            if(isdataloaded == true) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                i.putExtra("user","employee");
                i.putExtra("String",li);
                startActivity(i);
            }
            else{
                Toast.makeText(MainActivity.this, "Data Not loaded please Load it", Toast.LENGTH_SHORT).show();
            }
        }
        else if(Username.equals("chacha") && password.equals("1234")){
            if (isdataloaded == true){
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                intent.putExtra("user","user");
                intent.putExtra("String",li);
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this, "Data Not loaded please Load it", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            if (counter==0){
                submit.setEnabled(false);
            }
            Toast.makeText(MainActivity.this,"Invalid UserName or Password "+counter +" attempts remaining",Toast.LENGTH_SHORT).show();
            counter--;
        }
    }
    public void DataLoad(){
        isdataloaded = true;
        preparedata.setEnabled(false);
        Toast.makeText(MainActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();
//        getting Latitude from Database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot datasnapshot) {
//                        String value = snapshot.getValue(String.class);
//                        Toast.makeText(MainActivity.this, ""+value, Toast.LENGTH_SHORT).show();
                for (DataSnapshot data:datasnapshot.getChildren()){
                    li.add(data.getValue().toString());
                }

//                Toast.makeText(MainActivity.this, ""+li, Toast.LENGTH_SHORT).show();
//                return;
//                for (int i=0;i<li.size();i++)
//                    a.add(Double.parseDouble(li.get(i)));
//                Toast.makeText(MapsActivity.this, "data captured from list"+a, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to get value", Toast.LENGTH_SHORT).show();
            }
        });
//        Getting Longitude from FireBase
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot datasnapshot) {
//                        String value = snapshot.getValue(String.class);
//                        Toast.makeText(MainActivity.this, ""+value, Toast.LENGTH_SHORT).show();
                for (DataSnapshot data:datasnapshot.getChildren()){
                    li.add(data.getValue().toString());
                }

//                Toast.makeText(MainActivity.this, ""+li, Toast.LENGTH_SHORT).show();
//                return;
//                for (int i=0;i<li.size();i++)
//                    a.add(Double.parseDouble(li.get(i)));
//                Toast.makeText(MapsActivity.this, "data captured from list"+a, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to get value", Toast.LENGTH_SHORT).show();
            }
        });

//        Getting locations from FireBase DataBase
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot datasnapshot) {
//                        String value = snapshot.getValue(String.class);
//                        Toast.makeText(MainActivity.this, ""+value, Toast.LENGTH_SHORT).show();
                for (DataSnapshot data:datasnapshot.getChildren()){
                    li.add(data.getValue().toString());
                }

//                Toast.makeText(MainActivity.this, ""+li, Toast.LENGTH_SHORT).show();
//                return;
//                for (int i=0;i<li.size();i++)
//                    a.add(Double.parseDouble(li.get(i)));
//                Toast.makeText(MapsActivity.this, "data captured from list"+a, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to get value", Toast.LENGTH_SHORT).show();
            }
        });
        myRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot datasnapshot) {
//                        String value = snapshot.getValue(String.class);
//                        Toast.makeText(MainActivity.this, ""+value, Toast.LENGTH_SHORT).show();
                for (DataSnapshot data:datasnapshot.getChildren()){
                    li.add(data.getValue().toString());
                }
//                Toast.makeText(MainActivity.this, ""+li, Toast.LENGTH_SHORT).show();

//                Toast.makeText(MainActivity.this, ""+li, Toast.LENGTH_SHORT).show();
//                return;
//                for (int i=0;i<li.size();i++)
//                    a.add(Double.parseDouble(li.get(i)));
//                Toast.makeText(MapsActivity.this, "data captured from list"+a, Toast.LENGTH_SHORT).show();

                Toast.makeText(MainActivity.this, "Data Loaded Please move ahead", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to get value", Toast.LENGTH_SHORT).show();
            }
        });
//       if (li.size()%li.size()==0){
//           Toast.makeText(MainActivity.this, "Data Loaded Please move ahead", Toast.LENGTH_SHORT).show();
//       }

    }
}