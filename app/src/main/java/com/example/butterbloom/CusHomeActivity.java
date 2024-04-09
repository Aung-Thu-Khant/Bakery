package com.example.butterbloom;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.butterbloom.Adapter.AdapterOne;
import com.example.butterbloom.Adapter.AdapterTwo;
import com.example.butterbloom.Model.Cake;

import java.util.ArrayList;
import java.util.List;

public class CusHomeActivity extends AppCompatActivity {
    AdapterOne adapterOne;
    AdapterTwo adapterTwo;
    RecyclerView rv_hori, rv_ver;
    List<Cake> cakeList = new ArrayList<>();

    List<Cake> cakeListTwo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cus_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rv_hori = findViewById(R.id.recyclerView2);
        rv_ver = findViewById(R.id.rv_ver);

        Cake c1 = new Cake("Bakery","12000ks",R.drawable.one);
        Cake c2 = new Cake("Fruit","15000ks",R.drawable.two);
        Cake c3 = new Cake("Bubble","23000ks",R.drawable.three);
        Cake c4 = new Cake("Birthday","32000ks",R.drawable.four);
        Cake c5 = new Cake("Monkey","15000ks",R.drawable.five);
        Cake c6 = new Cake("Stawberry","17000ks",R.drawable.six);
        cakeList.add(c1);
        cakeList.add(c2);
        cakeList.add(c3);
        cakeList.add(c4);
        cakeList.add(c5);
        cakeList.add(c6);

        Cake c7 = new Cake("Bakery","12000ks",R.drawable.seven);
        Cake c8 = new Cake("Fruit","15000ks",R.drawable.eight);
        Cake c9 = new Cake("Bubble","23000ks",R.drawable.nine);
        Cake c10 = new Cake("Birthday","32000ks",R.drawable.ten);
        Cake c11 = new Cake("Monkey","15000ks",R.drawable.eleven);
        Cake c12 = new Cake("Stawberry","17000ks",R.drawable.twelve);
        cakeListTwo.add(c7);
        cakeListTwo.add(c8);
        cakeListTwo.add(c9);
        cakeListTwo.add(c10);
        cakeListTwo.add(c11);
        cakeListTwo.add(c12);

        LinearLayoutManager linearLayoutManagerone = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rv_hori.setLayoutManager(linearLayoutManagerone);
        adapterOne = new AdapterOne(this,cakeList);
        rv_hori.setAdapter(adapterOne);

        LinearLayoutManager linearLayoutManagertwo = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_ver.setLayoutManager(linearLayoutManagertwo);
        adapterTwo = new AdapterTwo(this,cakeListTwo);
        rv_ver.setAdapter(adapterTwo);

    }
}