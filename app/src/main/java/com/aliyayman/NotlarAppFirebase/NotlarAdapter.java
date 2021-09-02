package com.aliyayman.NotlarAppFirebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class  NotlarAdapter extends RecyclerView.Adapter<NotlarAdapter.CardNesneleriTutucu>  {
    private Context mContext;
    private List<Notlar> notlarList;

    public NotlarAdapter(Context mContext, List<Notlar> notlarList) {
        this.mContext = mContext;
        this.notlarList = notlarList;
    }

    public class CardNesneleriTutucu extends RecyclerView.ViewHolder{
        private TextView textViewDers;
        private TextView textViewNot1;
        private TextView textViewNot2;
        private CardView not_card;

        public CardNesneleriTutucu(@NonNull View itemView) {
            super(itemView);
            textViewDers=itemView.findViewById(R.id.textViewDers);
            textViewNot1=itemView.findViewById(R.id.textViewNot1);
            textViewNot2=itemView.findViewById(R.id.textViewNot2);
            not_card=itemView.findViewById(R.id.not_card);


        }
    }

    @NonNull
    @Override
    public CardNesneleriTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);

        return new CardNesneleriTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardNesneleriTutucu holder, int position) {
        Notlar not=notlarList.get(position);

        holder.textViewDers.setText(not.getDers_adi());
        holder.textViewNot1.setText(String.valueOf(not.getNot1()));
        holder.textViewNot2.setText(String.valueOf(not.getNot2()));

        holder.not_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext,DetayActivity.class);
                intent.putExtra("nesne",not);
                mContext.startActivity(intent);


            }
        });    }




    @Override
    public int getItemCount() {
        return notlarList.size();
    }
}
