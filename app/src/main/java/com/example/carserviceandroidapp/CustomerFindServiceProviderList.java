package com.example.carserviceandroidapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerFindServiceProviderList extends AppCompatActivity  implements CustomAdapter.ItemClickListener{
    DBHelper DB;
    CustomAdapter adapter;
    String location="";String spID, fullLoc;

    //arraylist to store the image and the text for the recycle views
    ArrayList<ImageAndText> aList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_find_service_provider_list);
        DB = new DBHelper(this);
        Intent intent = getIntent();
        TextView txtServiceLoc = findViewById(R.id.txtServiceLocation);
        if(intent!=null)
        {
            location = intent.getStringExtra("LOC");
            txtServiceLoc.setText("Available Service Providers at \n" +location);
        }
        //displaying the image and the text name and address of the service providers
        displayData();
        //showing the recycle view by 2 columns
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int numOfCols = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this,numOfCols));
        adapter = new CustomAdapter(this,aList,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        spID = aList.get(position).getServiceProviderID();
        Intent intent = new Intent(CustomerFindServiceProviderList.this,CustomerScheduleDropOff.class);
        intent.putExtra("SPID",spID);
        startActivity(intent);
    }

    //displaying the image and the text name and address of the service providers
    private void displayData()
    {
        Cursor cursor = DB.getServiceProviderList();

        if(cursor.getCount()==0)
        {
            Toast.makeText(CustomerFindServiceProviderList.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                if(cursor.getString(4).equals(location))
                {
                    String imageName = cursor.getString(9); // replace with the name of the desired image
                    int imageResourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    fullLoc = cursor.getString(2) + "\n" + cursor.getString(3) + "," +
                            cursor.getString(4) + ", " + cursor.getString(5) +", "
                            +  cursor.getString(6);
                    aList.add(new ImageAndText(fullLoc, imageResourceId,cursor.getString(0)));

                }
            }
        }

    }
}