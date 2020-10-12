package com.example.calificaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class wordListAdapter extends RecyclerView.Adapter<wordListAdapter.WordViewHolder> {

    private final LinkedList<String> mWordList;
    private LayoutInflater inflater;
    private Context context;

    public wordListAdapter(Context context, LinkedList<String> mWordList){
        inflater = LayoutInflater.from(context);
        this.mWordList= mWordList;
        this.context = context;
    }

    @Override
    public wordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.wordlist_item, parent,false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(wordListAdapter.WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }


    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView wordItemView;
        final wordListAdapter mAdapter;

        public WordViewHolder(View itemView, wordListAdapter mAdapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = mAdapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int mPosition = getLayoutPosition();
            String contentText = mWordList.get(mPosition);
            mAdapter.notifyDataSetChanged();
            ((MainActivity) context).abrir(mPosition,contentText);

        }
    }
}
