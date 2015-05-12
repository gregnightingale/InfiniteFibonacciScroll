package com.example.gregnightingale.fibonacciscroll;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.Iterator;

/**
 * Created by gregnightingale on 5/5/15.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> {
    private static final String TAG = MyRecyclerViewAdapter.class.getSimpleName();
    private FibonacciDeque data;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyRecyclerViewAdapter(FibonacciDeque dataset) {
        data = dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //...
        DataObjectHolder vh = new DataObjectHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
//        Log.d(TAG, "onBindViewHolder(" + position + ")");
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        int i = 0;
        for (Iterator<FibonacciItem> iter = data.iterator(); iter.hasNext(); i++) {
            FibonacciItem fibItem = iter.next();
            BigInteger fN = fibItem.getFn();
            int n = fibItem.getN();
            if (n == position) {
                holder.n.setText(Integer.toString(n));
                holder.fN.setText(fN.toString());
                break;
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
//        return data.size();
        return Integer.MAX_VALUE;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
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
