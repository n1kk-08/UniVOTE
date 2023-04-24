package com.example.univote.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.univote.R;
import com.example.univote.activities.VotingActivity;
import com.example.univote.model.Candidate;

import org.w3c.dom.Text;

import java.util.List;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {

    private Context context;
    private List<Candidate> list;

    public CandidateAdapter(Context context, List<Candidate> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.candidate_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(list.get(position).getName());
        holder.position.setText(list.get(position).getPosition());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VotingActivity.class);
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("post",list.get(position).getPosition());
                intent.putExtra("id",list.get(position).getId());

                context.startActivity(intent);
//                Activity activity = (Activity) context;
//                activity.finish();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name, position;
        private CardView cardView;
        private Button voteBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            position = itemView.findViewById(R.id.post);
//            voteBtn = itemView.findViewById(R.id.vote_btn);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
