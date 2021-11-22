package com.example.garbage_managament;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.garbage_managament.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Marker marker;
    public static String qrvalue;
    private int count = 0;
//    String location[] = {"Dr. V.N. Bedekar","VPM's Polytechnic","K.C. College of Engineering","ASM Institute of Management"};
//    double a[] = {19.1891,19.1927,19.1800,19.1876};
//    double b[] = {72.9804,72.9812,72.9804,72.9548};
//    double c[] = {80.80,40.50,60.70,90.20};
    public HashMap hashMap = new HashMap();
    FirebaseDatabase database;
    DatabaseReference myRef3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        myRef3 = database.getInstance().getReference().child("Percentage");


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
//        Bundle bundle = getIntent().getExtras();
//        ArrayList<String> li = bundle.getStringArrayList("String");
//        Log.e("Pass String", "onCreate: "+li+" "+li.size());
//        double a1[] = new double[li.size()/4];
//        double b1[] = new double[li.size()/4];
//        double c1[] = new double[li.size()/4];
//        String d1[] = new String[li.size()/4];
//        for (int i=0;i<a1.length;i++){
//            a1[i] = Double.parseDouble(li.get(i));
//            b1[i] = Double.parseDouble(li.get(li.size()/4+i));
//            c1[i] = Double.parseDouble(li.get((li.size()/4)*2+i));
//            d1[i] = (li.get((li.size()/4)*3+i));
//        }
//        Log.e("Ready arrays are => ", "latitude=>"+Arrays.toString(a1)+" Longitude => "+Arrays.toString(b1)+"Percentage are=>"+Arrays.toString(c1)+" Landmark are "+Arrays.toString(d1));
        Bundle bundle = getIntent().getExtras();
        ArrayList<String> li = bundle.getStringArrayList("String");
        String user = bundle.getString("user");
        double a1[] = new double[li.size()/4];
        double b1[] = new double[li.size()/4];
        double c1[] = new double[li.size()/4];
        String d1[] = new String[li.size()/4];
        for (int i=0;i<a1.length;i++){
            a1[i] = Double.parseDouble(li.get(i));
            b1[i] = Double.parseDouble(li.get(li.size()/4+i));
            c1[i] = Double.parseDouble(li.get((li.size()/4)*2+i));
            d1[i] = (li.get((li.size()/4)*3+i));
        }
//        Log.i("List is ","=>"+li.toString()+"and suer is "+user);
//        Log.e("Ready arrays are => ", "latitude=>"+Arrays.toString(a1)+" Longitude => "+Arrays.toString(b1)+"Percentage are=>"+Arrays.toString(c1)+" Landmark are "+Arrays.toString(d1)+"User is "+user);

        mMap = googleMap;


        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        DataPrepare obj = new DataPrepare();
//        int length = obj.latitude.size();

        for (int i=0;i<a1.length;i++){
//            LatLng mark = new LatLng(a[i],b[i]);
//            double pos1 = obj.latitude.get(i);
//            double pos2 = obj.longitude.get(i);
            LatLng mark = new LatLng(a1[i],b1[i]);
//            LatLng mark = new LatLng(a1.get(i),b.get(i));
//            mMap.addMarker(new MarkerOptions().position(mark).title(City[i]));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(mark));
            marker = mMap.addMarker(new MarkerOptions()
                    .position(mark)
                    .title(" "+d1[i])
                    .snippet(" "+d1[i]));
            if (c1[i]>=70){
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.red_trash));
            }
            else if (c1[i]>=60){
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.yellow_trash));
            }
            else{
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.green_trash));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mark,10f));

        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker){
//                return false;
//                Toast.makeText(MapsActivity.this, ""+marker.getTitle()+" is Now Empty", Toast.LENGTH_SHORT).show();
//                System.out.println("Marker id is "+marker.getId());
//                System.out.println(li.get(marker.getId().charAt(1)));
//                Integer a = Integer.parseInt(li.get(0));
//                System.out.print(a.getClass().getSimpleName());
//               System.out.println("value of"+a);
//                AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
////                builder.setMessage("Are you want to collect Garbage from "+marker.getTitle()+ " ??").setPositiveButton("YES", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        marker.setTitle(marker.getTitle());
////                        marker.showInfoWindow();
////                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.green_trash_24));
////                    }
////                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        marker.setTitle(marker.getTitle());
////                        marker.showInfoWindow();
//////                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.green_trash_24));
////                    }
////                });
//                marker.setTitle(marker.getTitle());
//                        marker.showInfoWindow();
//                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.green_trash_24));
                if (user.equals("employee")){
                    char index = marker.getId().charAt(1);
                    int i = Character.getNumericValue(index);
                    String qrcode = a1[i]+""+b1[i];
//                    Toast.makeText(MapsActivity.this, ""+qrcode, Toast.LENGTH_SHORT).show();
                    hashMap.put(""+i,"0");
                    new AlertDialog.Builder(MapsActivity.this)
                            .setTitle(marker.getTitle()+" is "+c1[i]+" % Full Collect Garbage?")
//                        .setMessage(marker.getTitle()+" ")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent i = new Intent(MapsActivity.this,MainActivity2.class);
                                    startActivity(i);

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            if (qrcode.equals(qrvalue)){
                                                marker.setTitle(marker.getTitle()+" ");
                                                marker.showInfoWindow();
                                                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.green_trash));
                                                myRef3.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                                    @Override
                                                    public void onSuccess(Object o) {
                                                        Toast.makeText(MapsActivity.this, "Value Updated in dataBase", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                            }
                                            else{
                                                Toast.makeText(MapsActivity.this, "Wrong QRCODE value are "+qrcode+" "+qrvalue, Toast.LENGTH_SHORT).show();
                                                marker.setTitle(marker.getTitle()+" ");
                                                marker.showInfoWindow();
                                            }
                                        }
                                    }, 5000);   //5 seconds

//                                    Toast.makeText(MapsActivity.this, ""+qrvalue, Toast.LENGTH_SHORT).show();
///
//                                    Toast.makeText(MapsActivity.this, ""+qrcode+"=="+qrvalue, Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                    marker.setTitle(marker.getTitle());
                                    marker.showInfoWindow();

                                }
                            })
                            .show();
                }
                else if (user.equals("user")){
                    char index = marker.getId().charAt(1);
                    int i = Character.getNumericValue(index);
                    marker.setTitle(marker.getTitle() +" is "+c1[i]+"% full");
//                    marker.setTitle(a1[i]+" "+b1[i]+" ");
                    marker.showInfoWindow();
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        count++;
        Toast.makeText(this, "Press again to exit the app", Toast.LENGTH_SHORT).show();
        if (count == 2) {
            finish();
            System.exit(0);
        }
    }
}