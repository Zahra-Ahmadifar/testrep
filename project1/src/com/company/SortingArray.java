package com.company;

/**
 * Created by Asus on 2/13/2021.
 */

public class SortingArray {
    int swapCounter = 0;
    int count=0;

    public int sort(int[] inputArray) {

        for (int i = 0; i < inputArray.length - 1; i++) {
            int min = i;
            for (int j = i; j < inputArray.length; j++) {
                if (inputArray[j] < inputArray[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(min, i, inputArray);
            }
        }


        return swapCounter;
    }

    public void swap(int min, int i, int[] inputArray) {
        int temp;
        temp = inputArray[min];
        inputArray[min] = inputArray[i];
        inputArray[i] = temp;
        swapCounter += 2;
    }

}
