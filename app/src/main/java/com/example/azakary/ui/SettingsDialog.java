package com.example.azakary.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;
import com.example.azakary.utils.Constance;
import com.example.azakary.utils.PrefManager;
import com.example.azakary.R;

import static android.app.AlarmManager.INTERVAL_FIFTEEN_MINUTES;
import static android.app.AlarmManager.INTERVAL_HALF_DAY;
import static android.app.AlarmManager.INTERVAL_HALF_HOUR;
import static android.app.AlarmManager.INTERVAL_HOUR;

public class SettingsDialog extends DialogFragment implements
        View.OnClickListener {

    //widgets
    private TextView mCreate, mCancel;
    private View view;
    private SegmentedButtonGroup segmentedButtonGroup;
    private Switch mSwitch;

    //var
    long repeatInterval;
    private static final String TAG = "MainActivity";
    private static final int APP_PERMISSION_REQUEST = 102;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int style = DialogFragment.STYLE_NORMAL;
        int theme = android.R.style.Theme_Holo_Light_Dialog;
        setStyle(style, theme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_settings, container, false);
        mCreate = view.findViewById(R.id.ok);
        mCancel = view.findViewById(R.id.cancel);
        mSwitch = view.findViewById(R.id.switch_btn);

        PrefManager.initPrefs(view.getContext());
        segmentedButtonGroup = view.findViewById(R.id.buttonGroup_lordOfTheRings);
        segmentedButtonGroup.setPosition(PrefManager.getInt("POSITION", 2), false);
        segmentedButtonGroup.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(final int position) {
                switch (position) {
                    case 0:
                        repeatInterval = INTERVAL_HALF_DAY;
                        Log.d(TAG, "onPositionChanged: " + "HALF_DAY");
                        break;
                    case 1:
                        repeatInterval = INTERVAL_HOUR;
                        Log.d(TAG, "onPositionChanged: " + "HOUR");
                        break;
                    case 2:
                        repeatInterval = INTERVAL_HALF_HOUR;
                        Log.d(TAG, "onPositionChanged: " + "HALF_HOUR");
                        break;
                    case 3:
                        repeatInterval = INTERVAL_FIFTEEN_MINUTES;
                        Log.d(TAG, "onPositionChanged: " + "FIFTEEN_MINUTES");
                        break;
                }
                PrefManager.putInt("POSITION", position);
            }
        });

        mSwitch.setChecked(PrefManager.getBoolean("notify", true));

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Constance.notify(view.getContext(), true);
                } else {
                    Constance.notify(view.getContext(), false);
                }
            }
        });

        getDialog().setTitle("Sittings");

        mCancel.setOnClickListener(this);
        mCreate.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                PrefManager.putLong(Constance.ALARM_STATE, repeatInterval);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        !Settings.canDrawOverlays(view.getContext())) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getActivity().getPackageName()));
                    startActivityForResult(intent, APP_PERMISSION_REQUEST);
                } else {
                    Constance.startAlarm(view.getContext());
                }
                getDialog().dismiss();
                break;
            case R.id.cancel:
                getDialog().dismiss();
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
