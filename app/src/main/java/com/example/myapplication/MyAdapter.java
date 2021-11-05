package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Controler.Repository;
import com.example.myapplication.Model.Reunion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<Reunion> mReunion;

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

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Reunion reunion = mReunion.get(position);
        holder.sujet.setText(reunion.getSujet() + " - " + reunion.getHour()+":"+ reunion.getMinute() + " - "+ reunion.getLieu());
        holder.participant.setText(reunion.getGuest());

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repository.deleteReunion(reunion);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mReunion.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.Sujet)
        public TextView sujet;

        @BindView(R.id.Participant)
        public TextView participant;

        @BindView(R.id.Bin)
        public ImageView mDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
