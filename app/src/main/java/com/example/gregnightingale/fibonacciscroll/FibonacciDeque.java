package com.example.gregnightingale.fibonacciscroll;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Created by gregnightingale on 5/7/15.
 */
public class FibonacciDeque extends ArrayDeque<FibonacciItem> {

    public FibonacciDeque(int numElements) {
        super(numElements);
        // initialize the Fibonacci Series
        BigInteger nMinus2 = BigInteger.ZERO;
        BigInteger nMinus1 = BigInteger.ONE;
        this.add(new FibonacciItem(0, BigInteger.ZERO));
        this.add(new FibonacciItem(1, BigInteger.ONE));
        for (int i = 2; i <= numElements; i++) {
            BigInteger next = nMinus2.add(nMinus1);
            this.add(new FibonacciItem(i, next));
            nMinus2 = nMinus1;
            nMinus1 = next;
        }
    }

    public void slide(int delta) {

        if (delta == 0) return;
        if (delta > 0) {
            for (int i = 0; i < delta; i++) {
                this.removeFirst();
                this.addLast(nextHigherFib());
            }
        } else if (delta < 0) {
            for (int i = 0; i > delta; i--) {
                this.removeLast();
                this.addFirst(nextLowerFib());
            }
        }
    }

    /**
     * find the nth Fibonacci Number in the deque
     * @param n
     * @return null if not found
     */
    public FibonacciItem get(int n) throws IndexOutOfBoundsException {
        int i = 0;
        for (Iterator<FibonacciItem> iter = this.iterator(); iter.hasNext(); i++) {
            FibonacciItem fibItem = iter.next();
            BigInteger fN = fibItem.getFn();
            int chkN = fibItem.getN();
            if (chkN == n) {
                return fibItem;
            }
        }
        throw new IndexOutOfBoundsException("requested index not within current Deque!");
    }

    private FibonacciItem nextHigherFib() {
        final Iterator<FibonacciItem> iterator = this.descendingIterator();
        FibonacciItem item = iterator.next();
        BigInteger fnMinus1 = item.getFn();
        int n = item.getN();
        item = iterator.next();
        BigInteger fnMinus2 = item.getFn();
        FibonacciItem result = new FibonacciItem(n + 1, fnMinus1.add(fnMinus2));
        return result;
    }

    private FibonacciItem nextLowerFib() {
        final Iterator<FibonacciItem> iterator = this.iterator();
        FibonacciItem item = iterator.next();
        BigInteger fn = item.getFn();
        int n = item.getN();
        item = iterator.next();
        BigInteger nPlus1 = item.getFn();
        FibonacciItem result = new FibonacciItem(n - 1, nPlus1.subtract(fn));
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
