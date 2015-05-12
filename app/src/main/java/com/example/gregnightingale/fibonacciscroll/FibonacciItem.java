package com.example.gregnightingale.fibonacciscroll;

import java.math.BigInteger;

/**
 * Created by gregnightingale on 5/7/15.
 */
public class FibonacciItem {

    private Integer n;
    private BigInteger fn;

    public FibonacciItem(int n, BigInteger fn) {
        //copy constructor
        this.n = new Integer(n);
        this.fn = BigInteger.ZERO.add(fn);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public BigInteger getFn() {
        return fn;
    }

    public void setFn(BigInteger fn) {
        this.fn = fn;
    }
}
