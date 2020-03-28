package com.example.azakary.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azakary.Adapter.AzkaryAdapter;
import com.example.azakary.utils.Constance;
import com.example.azakary.Model.AzkarModel;
import com.example.azakary.R;

import java.util.ArrayList;

public class AzkarScrean extends AppCompatActivity implements AzkaryAdapter.OnItemListener {

    ArrayList<AzkarModel> azkarModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AzkaryAdapter adapter;
    int status = 0 ;

    int tatSizeConstance = 27 ;
    TextView zekrTxt_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azkar_screan);
        zekrTxt_field = findViewById(R.id.zekr);
        Intent intent = getIntent();
        if (intent.hasExtra("status")){
            status = 1;
            azkarModelArrayList = (ArrayList<AzkarModel>) Constance.instanceEviningList();
        }else {
            azkarModelArrayList = (ArrayList<AzkarModel>) Constance.instanceMorningList();
        }
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext(),
                RecyclerView.VERTICAL,false);
        adapter = new AzkaryAdapter(azkarModelArrayList, this,getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnItemClicked(int position, View view){
        delete(view, position);
    }

    @Override
    public void OnButtonClicked(int position) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        AzkarModel aa =  azkarModelArrayList.get(position);
        shareIntent.putExtra(Intent.EXTRA_TEXT,aa.getElzekr() + "Eng.Muh khaled");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Azkary App");
        startActivity(Intent.createChooser(shareIntent, "Share..."));
    }


    private void delete(View rowView, final int position) {
        // delete with animation
        Animation anim = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);
        rowView.startAnimation(anim);

        azkarModelArrayList.remove(position);
        adapter.notifyItemRemoved(position);
        if (azkarModelArrayList.isEmpty()){
            Intent i = new Intent(AzkarScrean.this, AzkarCounterScrean.class);
            i.putExtra("DAYTOTZEKR",adapter.totDayCount);
            i.putExtra("status",status);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AzkarScrean.this, AzkarCounterScrean.class);
        intent.putExtra("DAYTOTZEKR",adapter.totDayCount);
        intent.putExtra("status",status);
        startActivity(intent);
        finish();
    }

    public void decrement_increment(View view) {
        switch (view.getId()){
            case R.id.txtSizeBtn:
                if (tatSizeConstance == 35 ){
                    Toast.makeText(this, "عذرا لا يمكن التكبير اكثر", Toast.LENGTH_SHORT).show();
                }else {
                    tatSizeConstance++;
                    adapter.setTextSizes(tatSizeConstance);
                }

                break;
            case R.id.txtSizeincrementBtn:
                if (tatSizeConstance == 18 ){
                    Toast.makeText(this, "عذرا لا يمكن التصغير اكثر", Toast.LENGTH_SHORT).show();
                }else {
                    tatSizeConstance-- ;
                    adapter.setTextSizes(tatSizeConstance);
                }
        }

    }
}
