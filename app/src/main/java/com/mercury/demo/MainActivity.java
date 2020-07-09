package com.mercury.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.mercury.MainAdapter;
import com.mercury.demo.aty.PathActivity;
import com.mercury.demo.aty.ProgressActivity;
import com.mercury.demo.aty.RadarViewActivity;
import com.mercury.demo.aty.TestLayoutActivity;
import com.mercury.demo.behavior.CoordinateActivity;
import com.mercury.demo.camera.AvatarActivity;
import com.mercury.demo.scrollcut.ScrollCutAcitivty;
import com.mercury.demo.viewpager.TabHostActivity;


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
                break;
            case 3:
                startActivity(new Intent(this, TabHostActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, ProgressActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, CoordinateActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, PathActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, ScrollCutAcitivty.class));
                break;
            default:
                break;
        }

    }
}
