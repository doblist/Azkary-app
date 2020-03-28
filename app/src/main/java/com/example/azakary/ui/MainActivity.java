package com.example.azakary.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.azakary.utils.Constance;
import com.example.azakary.R;

public class MainActivity extends AppCompatActivity {
    // variable to track event time
    private long mLastClickTime = 0;
    private static final int APP_PERMISSION_REQUEST = 102;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                !Settings.canDrawOverlays(MainActivity.this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, APP_PERMISSION_REQUEST);
        } else {
            Constance.startAlarm(MainActivity.this);
        }

        Constance.notify(this, true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                SettingsDialog mSettingsDialog = new SettingsDialog();
                mSettingsDialog.show(getSupportFragmentManager(), "settings");
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void onClicked(View view) {
        //to do one click not more
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (view.getId()) {
            case R.id.morning_btn:
                Intent intentMor = new Intent(getApplicationContext(), AzkarScrean.class);
                startActivity(intentMor);
                break;
            case R.id.evinig_btn:
                Intent intentEvi = new Intent(getApplicationContext(), AzkarScrean.class);
                intentEvi.putExtra("status", 1);
                startActivity(intentEvi);
                break;

            case R.id.sebhaBtn:
                Intent iSebha = new Intent(MainActivity.this, TasbehActivity.class);
                startActivity(iSebha);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == APP_PERMISSION_REQUEST && resultCode == RESULT_OK) {
            Constance.startAlarm(MainActivity.this);
        } else {
            Toast.makeText(this, "Draw over other app permission not enable.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
