package com.example.gregnightingale.fibonacciscroll;

import android.os.Looper;
import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Created by gregnightingale on 5/7/15.
 */
public class FibonacciDeque extends ArrayDeque<FibonacciItem> {
    public FibonacciDeque(int numElements) {
        super(numElements);
        BigInteger nMinus2 = BigInteger.ZERO;
        BigInteger nMinus1 = BigInteger.ONE;
        this.add(new FibonacciItem(0, BigInteger.ZERO));
        this.add(new FibonacciItem(1, BigInteger.ONE));
        for (int i=2; i<=numElements; i++) {
            BigInteger next = nMinus2.add(nMinus1);
            this.add(new FibonacciItem(i, next));
            nMinus2 = nMinus1;
            nMinus1 = next;
        }
//        Log.d("FibonacciDeque", this.toString());
    }
    public void slide(int delta) {

//        if (Looper.myLooper() == Looper.getMainLooper()) {
//            Log.d("FibonacciDeque", "is on UI Thread");
//        } else {
//            Log.d("FibonacciDeque", "is NOT on UI Thread");
//        };

        if (delta==0) return;
        if (delta>0) {
            for (int i=0; i<delta; i++) {
                this.removeFirst();
                this.addLast(nextHigherFib());
//                Log.d("FibonacciDeque:Up", this.toString());
            }
        } else if (delta<0) {
            for (int i=0; i>delta; i--) {
                this.removeLast();
                this.addFirst(nextLowerFib());
//                Log.d("FibonacciDeque:Dn", this.toString());
            }
        }
    }

    private FibonacciItem nextHigherFib() {
        final Iterator<FibonacciItem> iterator = this.descendingIterator();
        FibonacciItem item = iterator.next();
        BigInteger fnMinus1 = item.getFn();
        int n = item.getN();
        item = iterator.next();
        BigInteger fnMinus2 = item.getFn();
        FibonacciItem result = new FibonacciItem(n+1, fnMinus1.add(fnMinus2));
        return result;
    }

    private FibonacciItem nextLowerFib() {
        final Iterator<FibonacciItem> iterator = this.iterator();
        FibonacciItem item = iterator.next();
        BigInteger fn = item.getFn();
        int n = item.getN();
        item = iterator.next();
        BigInteger nPlus1 = item.getFn();
        FibonacciItem result = new FibonacciItem(n-1, nPlus1.subtract(fn));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        final Iterator<FibonacciItem> iterator = this.iterator();
        builder.append("\n");
        while (iterator.hasNext()) {
            FibonacciItem next = iterator.next();
            final BigInteger fn = next.getFn();
            final int n = next.getN();
            builder.append(Integer.toString(n));
            builder.append(":");
            builder.append(fn.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
