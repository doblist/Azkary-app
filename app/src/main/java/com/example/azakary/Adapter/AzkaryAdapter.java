package com.example.azakary.Adapter;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.azakary.Model.AzkarModel;
import com.example.azakary.R;

import java.util.ArrayList;

import static android.content.Context.VIBRATOR_SERVICE;

public class AzkaryAdapter extends RecyclerView.Adapter<AzkaryAdapter.ViewHolder>{
    private ArrayList<AzkarModel> modelArrayList;
    private OnItemListener onItemListener;
    private Context context;
    private Vibrator vibrator;
    public int totDayCount = 0 ;
    private int textSize = 25;

    public AzkaryAdapter(ArrayList<AzkarModel> modelArrayList, OnItemListener onItemListener, Context context) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.onItemListener = onItemListener;
    }

    public void setTextSizes(int textSize) {
        this.textSize = textSize;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AzkaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item,parent,false);
        return new AzkaryAdapter.ViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final AzkaryAdapter.ViewHolder holder, final int position) {
        AzkarModel currentZekr = modelArrayList.get(position);

        holder.elzekrView.setText(currentZekr.getElzekr());
        holder.elzekrView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        holder.cntView.setText(currentZekr.getCounter());
    }


    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView elzekrView, cntView;
        Button share;
        int currentCounter;
        OnItemListener onItemListener;

        public ViewHolder(@NonNull View itemView, final OnItemListener onItemListener) {
            super(itemView);
            elzekrView = itemView.findViewById(R.id.zekr);
            cntView = itemView.findViewById(R.id.zeker_cnt);
            share = itemView.findViewById(R.id.share_btn);
            this.onItemListener = onItemListener;

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (onItemListener!= null && position != RecyclerView.NO_POSITION){
                        onItemListener.OnButtonClicked(position);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vibrator= (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= 26) {
                        vibrator.vibrate(VibrationEffect.createOneShot(25,
                                VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(25);
                    }

                    String cntInTxt = cntView.getText().toString();
                    currentCounter = Integer.parseInt(cntInTxt);

                    if(currentCounter!=0){
                        currentCounter--;
                        totDayCount++;
                    }
                    cntView.setText(String.valueOf(currentCounter));
                    if(currentCounter == 0){
                        int position = getAdapterPosition();
                        if (onItemListener!= null && position != RecyclerView.NO_POSITION){
                            onItemListener.OnItemClicked(position, view);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemListener{
        void OnItemClicked(int position, View view);
        void OnButtonClicked(int position);
    }
}