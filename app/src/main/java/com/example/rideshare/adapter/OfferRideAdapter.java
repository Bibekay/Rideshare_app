package com.example.rideshare.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rideshare.R;
import com.example.rideshare.activities.PostDetailsActivity;
import com.example.rideshare.model.ridepost;

import java.util.List;


public class OfferRideAdapter extends  RecyclerView.Adapter<OfferRideAdapter.HistoryViewHolder> {

    private Context mcontext;
    private List<ridepost> ridepostList;

    public OfferRideAdapter(Context mcontext, List<ridepost>ridepostList){
        this.mcontext=mcontext;
        this.ridepostList=ridepostList;
    }
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.history_adpter,parent,false);


        return  new HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int i) {

       final ridepost rp = ridepostList.get(i);
        holder.txt1.setText(rp.getPickuppoint());
        holder.txt2.setText(rp.getDestination());
        holder.txt3.setText(rp.getJourneydate());
        holder.txt4.setText(rp.getTime());
        holder.txt5.setText(rp.getPaidornot());
        holder.txt6.setText(rp.getCost());

        holder.clickRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent notify = new Intent(mcontext, PostDetailsActivity.class);
                            notify.putExtra("id", rp.get_id());
                            notify.putExtra("pickuppoint",rp.getPickuppoint());
                            notify.putExtra("destination",rp.getDestination());
                            notify.putExtra("journeydate",rp.getJourneydate());
                            notify.putExtra("time",rp.getTime());
                            notify.putExtra("paidornot",rp.getPaidornot());
                            notify.putExtra("cost",rp.getCost());

                            mcontext.startActivity(notify);

                }
        });
    }


    @Override
    public int getItemCount() {
        return ridepostList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{

          TextView txt1,txt2,txt3,txt4,txt5,txt6;
          Button clickRequest;


        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.tvPickuppoint);
            txt2 = itemView.findViewById(R.id.tvDestination);
            txt3 = itemView.findViewById(R.id.tvJourneydate);
            txt4 = itemView.findViewById(R.id.tvTime);
            txt5 = itemView.findViewById(R.id.tvPaidornot);
            txt6 = itemView.findViewById(R.id.tvCost);

            clickRequest = itemView.findViewById(R.id.btnRequest);
        }
    }
}
