package com.example.gregnightingale.fibonacciscroll;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigInteger;

/**
 * Created by gregnightingale on 5/5/15.
 */
public class FibonacciDequeViewAdapter extends RecyclerView.Adapter<FibonacciDequeViewAdapter.DataObjectHolder> {
    private static final String TAG = FibonacciDequeViewAdapter.class.getSimpleName();
    private FibonacciDeque data;

    public FibonacciDequeViewAdapter(FibonacciDeque dataset) {
        data = dataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
        DataObjectHolder vh = new DataObjectHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.n.setText(Integer.toString(position));
        try {
            FibonacciItem fibItem = data.get(position);
            BigInteger fN = fibItem.getFn();
            holder.fN.setText(fN.toString());
        } catch (IndexOutOfBoundsException e) {
            holder.fN.setText("error");
        }
    }

    @Override
    public int getItemCount() {
//        return data.size();
        return Integer.MAX_VALUE; // not truly infinite, but no human could possible scroll this list to the end
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView n;
        TextView fN;

        public DataObjectHolder(View itemView) {
            super(itemView);
            n = (TextView) itemView.findViewById(R.id.n);
            fN = (TextView) itemView.findViewById(R.id.fn);
        }

    }
}
