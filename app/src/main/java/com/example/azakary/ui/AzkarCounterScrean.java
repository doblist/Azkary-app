package com.example.azakary.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.azakary.R;
import com.example.azakary.utils.PrefManager;

public class AzkarCounterScrean extends AppCompatActivity {
    private TextView totalAzkar_Field, calcuText_field, tsbihaCount_field,
            dayAddedCnt_field, totalAzkarTxt_field;
    private int dayCount, newSave, tasbihaCount, newTasbih ;

    private static final String TOTAL_AZKAR_KEY = "azkar", TOTAL_TASBIH_KEY = "tasbih" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azkar_counter_screan);

        PrefManager.initPrefs(this);

        calcuText_field = findViewById(R.id.calucText);
        dayAddedCnt_field = findViewById(R.id.dayAddedCnt);
        totalAzkar_Field = findViewById(R.id.azkarTotal);
        tsbihaCount_field = findViewById(R.id.tasbihaCnt);
        totalAzkarTxt_field = findViewById(R.id.totalAzkarTxt);

        Intent intent = getIntent();

        if (intent.hasExtra("TOTAL_TASBIH")){
            totalAzkarTxt_field.setText("مجموع التسابيح");
            calcuText_field.setBackgroundColor(getResources().getColor(R.color.colorTasbih));
            calcuText_field.setText("حاسبه التسابيح");
            tasbihaCount = intent.getIntExtra("TOTAL_TASBIH",0);

            if (tasbihaCount!=0){
                tsbihaCount_field.setText(tasbihaCount+"");
                newTasbih = tasbihaCount;
            }
            int storedTasbeh = PrefManager.getInt(TOTAL_TASBIH_KEY,0);
            newTasbih = storedTasbeh+newTasbih;
            totalAzkar_Field.setText(newTasbih+"");

        }else{
            int s = intent.getIntExtra("status",0);
            if (s == 1){
                calcuText_field.setBackgroundColor(getResources().getColor(R.color.colorEvining));
            }
            dayAddedCnt_field.setText("الاذكار المضافه");
            dayCount = intent.getIntExtra("DAYTOTZEKR",0);
            {
                if (dayCount!=0){
                    tsbihaCount_field.setText(dayCount+"");
                }
                newSave = dayCount;
//                int storedInt = mPref.getInt(TOTAL_AZKAR_KEY,0);
                int storedInt = PrefManager.getInt(TOTAL_AZKAR_KEY,0);
                newSave = storedInt + newSave ;
                totalAzkar_Field.setText(newSave+"");
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (newSave!=0){
            PrefManager.putInt(TOTAL_AZKAR_KEY, newSave);
        }
        if (newTasbih!=0){
            PrefManager.putInt(TOTAL_TASBIH_KEY, newTasbih);
        }
    }
}
