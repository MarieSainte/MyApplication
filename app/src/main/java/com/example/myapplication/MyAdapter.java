package com.example.myapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Controler.Repository;
import com.example.myapplication.Model.Reunion;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private final List<Reunion> mReunion;

    public MyAdapter(List<Reunion> mReunion) {

        this.mReunion = mReunion;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Reunion reunion = mReunion.get(position);
        holder.sujet.setText(reunion.getSujet() + " - " + reunion.getStartMeeting().get(Calendar.HOUR_OF_DAY)+":"+ reunion.getStartMeeting().get(Calendar.MINUTE) + " - "+ reunion.getLieu());
        holder.participant.setText(reunion.getGuest());

        holder.mDelete.setOnClickListener(v -> {
            Repository.deleteReunion(reunion);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return mReunion.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.Sujet)
        public TextView sujet;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.Participant)
        public TextView participant;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.Bin)
        public ImageView mDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
