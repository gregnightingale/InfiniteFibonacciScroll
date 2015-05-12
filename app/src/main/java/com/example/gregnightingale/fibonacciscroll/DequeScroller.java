package com.example.gregnightingale.fibonacciscroll;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by gregnightingale on 5/5/15.
 */
public abstract class DequeScroller extends RecyclerView.OnScrollListener {

    public final String TAG = this.getClass().getSimpleName();

    private int currentTop, savedTop;

    private LinearLayoutManager linearLayoutManager;

    public DequeScroller(LinearLayoutManager layoutManager) {
        super();
        this.linearLayoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        currentTop = linearLayoutManager.findFirstVisibleItemPosition();

        int rowChange = currentTop - savedTop;
        if (rowChange != 0) {
            onScrollToNewRow(rowChange);
        }

        savedTop = currentTop;

        super.onScrolled(recyclerView, dx, dy);
    }

    public abstract void onScrollToNewRow(int rowChange);
}
