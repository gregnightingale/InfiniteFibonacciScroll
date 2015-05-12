package com.example.gregnightingale.fibonacciscroll;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.math.BigInteger;

/**
 * Created by gregnightingale on 5/5/15.
 */
public abstract class SlidingWindowRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    public final String TAG = this.getClass().getSimpleName();

    private int currentTop, savedTop;

    private LinearLayoutManager mLinearLayoutManager;

    public SlidingWindowRecyclerOnScrollListener(LinearLayoutManager layoutManager) {
        super();
        this.mLinearLayoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
//        Log.d(this.getClass().getSimpleName(), "onScrollStateChanged" + newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        currentTop = mLinearLayoutManager.findFirstVisibleItemPosition();

        int rowChange = currentTop - savedTop;
        if (rowChange != 0) {
            onScrollToNewRow(rowChange);
        }

        savedTop = currentTop;

        super.onScrolled(recyclerView, dx, dy);
    }

    public abstract void onScrollToNewRow(int rowChange);
}
