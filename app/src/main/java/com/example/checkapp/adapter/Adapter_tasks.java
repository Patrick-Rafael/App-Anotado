package com.example.checkapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkapp.R;
import com.example.checkapp.model.Tasks;

import java.util.ArrayList;

public class Adapter_tasks extends RecyclerView.Adapter<Adapter_tasks.MyViewHolder> {

    private Context context;
    private ArrayList<Tasks> tasksArrayList;

    public Adapter_tasks(Context context, ArrayList<Tasks> tasksArrayList) {
        this.context = context;
        this.tasksArrayList = tasksArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_tasks, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Tasks tasks = tasksArrayList.get(position);

        holder.textTitleTasks.setText(tasks.getTextTitleTasks());

    }

    @Override
    public int getItemCount() {
        return tasksArrayList.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitleTasks;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitleTasks = itemView.findViewById(R.id.textTitleTask);
        }
    }
}
