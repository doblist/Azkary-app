package com.example.azakary.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.azakary.R;

import java.util.Random;

import static android.content.Context.WINDOW_SERVICE;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private WindowManager mWindowManager;
    private View mFloatingWidget;
    private static boolean isVisible = true;
    final static String[] names = new String[]{
            "أستغفر الله",
            "رب اغفر لي",
            "سبحان الله وبحمده",
            "سبحان الله العظيم",
            "الحمد لله",
            "لا إله إلا الله",
            "اللهم جنه الفردوس العلا",
            "اللهم ارفع مقتك وغضبك عنا",
            "لا اله الا انت سبحانك إني كنت من الظالمين",
            "استغفر الله العظيم",
            "استغفر الله وأسأله التوبه",
            " يارب عفوك",
            "لا حول ولا قوة إلا بالله",
            "اللهم عفوك ورضاك",
            "اللهم تب على",
            "الله أكبر",
            "اللهم اكرمنى"

    };
    Random random = new Random();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        mFloatingWidget = LayoutInflater.from(context).inflate(R.layout.layout_floating_widget, null);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.RIGHT;
        params.x = 0;
        params.y = 100;

        mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        assert mWindowManager != null;
//            if (isVisible) {
//            mWindowManager.addView(mFloatingWidget, params);
//            isVisible = false;
//        }
//        if (mFloatingWidget.getWindowToken() != null) {
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mWindowManager.removeView(mFloatingWidget);
//                    isVisible = true;
//                }
//            }, 15000);
//        }

        if (isVisible) {
            mWindowManager.addView(mFloatingWidget, params);
            isVisible = false;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mFloatingWidget.getWindowToken() != null) {
                        mWindowManager.removeView(mFloatingWidget);
                        isVisible = true;
                    }
                }
            }, 15000);
        }

        TextView closeButton = mFloatingWidget.findViewById(R.id.close_btn);
        closeButton.setText(names[random.nextInt(names.length)]);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFloatingWidget.getWindowToken() != null) {
                    mWindowManager.removeView(mFloatingWidget);
                    isVisible = true;
                }
            }
        });
    }
}
