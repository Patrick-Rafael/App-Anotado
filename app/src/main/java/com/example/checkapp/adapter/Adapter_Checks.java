package com.example.checkapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkapp.R;
import com.example.checkapp.model.Checks;

import java.util.ArrayList;

public class Adapter_Checks extends RecyclerView.Adapter<Adapter_Checks.MyViewHolder> {

    private Context context;
    private ArrayList<Checks> checksArrayList;

    public Adapter_Checks(Context context, ArrayList<Checks> checksArrayList) {

        this.context = context;
        this.checksArrayList = checksArrayList;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_checks,parent,false);
        return new MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Checks checks = checksArrayList.get(position);
        holder.textTitleCheks.setText(checks.getTextTitleChecks());
        holder.textDescriptionChecks.setText(checks.getTextDescriptionChecks());

    }

    @Override
    public int getItemCount() {
        return checksArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitleCheks, textDescriptionChecks;
        private CheckBox checkBoxChecks;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitleCheks = itemView.findViewById(R.id.textTitleChecks);
            textDescriptionChecks = itemView.findViewById(R.id.textDescriptionChecks);

        }
    }

}
