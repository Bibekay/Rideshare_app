package com.example.rideshare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rideshare.R;
import com.example.rideshare.model.ridepost;

import java.util.List;

public class NotificationRideAdapter extends RecyclerView.Adapter<NotificationRideAdapter.HistoryViewHolder>{


    private Context mcontext;
    private List<ridepost> ridepostList;

    public NotificationRideAdapter(Context mcontext, List<ridepost>ridepostList){
        this.mcontext=mcontext;
        this.ridepostList=ridepostList;
    }
    @NonNull
    @Override
    public NotificationRideAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.adapter_notificationride,parent,false);


        return  new NotificationRideAdapter.HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationRideAdapter.HistoryViewHolder holder, int i) {

        final ridepost rp = ridepostList.get(i);
        holder.txt1.setText(rp.getPickuppoint());
        holder.txt2.setText(rp.getDestination());
        holder.txt3.setText(rp.getJourneydate());
        holder.txt4.setText(rp.getTime());
        holder.txt5.setText(rp.getPaidornot());
        holder.txt6.setText(rp.getCost());
        holder.txt7.setText(rp.acepted_by.getUsername());
        holder.txt8.setText(rp.acepted_by.getContact());
    }


    @Override
    public int getItemCount() {
        return ridepostList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{

        TextView txt1,txt2,txt3,txt4,txt5,txt6, txt7, txt8;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.tvPickuppoint);
            txt2 = itemView.findViewById(R.id.tvDestination);
            txt3 = itemView.findViewById(R.id.tvJourneydate);
            txt4 = itemView.findViewById(R.id.tvTime);
            txt5 = itemView.findViewById(R.id.tvPaidornot);
            txt6 = itemView.findViewById(R.id.tvCost);
            txt7= itemView.findViewById(R.id.tvUsername);
            txt8 = itemView.findViewById(R.id.tvContact);

        }
    }

}
