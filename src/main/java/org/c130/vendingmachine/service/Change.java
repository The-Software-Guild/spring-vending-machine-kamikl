package org.c130.vendingmachine.service;

import java.math.BigDecimal;

public class Change {
    private int change;
    private int[] coins = new int[]{50,20,10,5,2,1};

    public Change(int change) {
        this.change = change;
    }

    public int[] calculateChange() {
        int[] changeArray = new int[]{0,0,0,0,0,0};
        int current = change;
        for(int i = 0; i<coins.length; i++) {
            int remainder = current % coins[i];
            if(remainder < current) {
                changeArray[i] = (current - remainder) / coins[i];
            }
            current = remainder;
        }
        return changeArray;
    }
}
