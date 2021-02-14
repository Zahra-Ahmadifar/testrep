package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	 SortingArray sortingArray = new SortingArray();
        System.out.print("please enter length of Array :");
        Scanner userInput = new Scanner(System.in);
        int length = Integer.parseInt(userInput.nextLine());
            if (length < 1 || length > 300000) {
            System.out.print("Array length should be between 1-300000.please enter appropriate number:");
            length = Integer.parseInt(userInput.nextLine());
            }

        int inputArray[] = new int[length];

        for (int input = 0; input < length; input++) {

            System.out.print(String.format("please enter value of index %d :", input + 1));
            inputArray[input] = Integer.parseInt(userInput.nextLine());
            for (int i = input-1; i >= 0  ; i--) {
                if (inputArray[input]==inputArray[i]) {
                    System.out.println("you cant enter duplicate number");
                    System.out.print(String.format("please enter value of index %d :", input + 1));
                    inputArray[input] = Integer.parseInt(userInput.nextLine());

                }

            }



    }


        int swapCounter = sortingArray.sort(inputArray);
        System.out.println("number of indexes that are swapping after sorting : " + swapCounter);



    }
}
