package com.mercury.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.mercury.MainAdapter;
import com.mercury.demo.aty.RadarViewActivity;
import com.mercury.demo.aty.TestLayoutActivity;
import com.mercury.demo.camera.AvatarActivity;


public class MainActivity extends AppCompatActivity implements MainAdapter.onItemClickListenr {


   RecyclerView recyclerView;
   MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this);
        adapter.setListenr(this);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onItemClick(int pos) {
        switch (pos) {
            case 0:
                startActivity(new Intent(this, AvatarActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, TestLayoutActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, RadarViewActivity.class));
            default:
                break;
        }

    }
}
