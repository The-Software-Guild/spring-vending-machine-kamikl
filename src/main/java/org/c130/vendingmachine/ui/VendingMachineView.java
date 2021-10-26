package org.c130.vendingmachine.ui;

import org.c130.vendingmachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

@Component
public class VendingMachineView {
    private UserIO io;

    @Autowired
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void printItems(List<Item> itemList) {
        System.out.println("Current stock:");
        for (Item currentItem : itemList) {
            String studentInfo = String.format("Item:%s Price:£%s Quantity:%s",
                    currentItem.getName(),
                    currentItem.getCost(),
                    currentItem.getQty());
            io.print(studentInfo);
        }
    }

    public BigDecimal takeMoney() {
        return io.readBigDecimal("Please enter amount of money to be entered into machine (Between 0.01 and 100.00)", new BigDecimal("0.01"), new BigDecimal("100"));
    }

    public String takeSelection() {
        return io.readString("Enter the item name to purchase.");
    }

    public void displayErrorMessage(String message) {
        System.out.println(message);
    }

    public void displayChange(int[] changeArray) {
        io.print("Change:");
        String change = String.format("50p:%s 20p:%s 10p:%s 5p:%s 2p:%s 1p:%s",
                changeArray[0],
                changeArray[1],
                changeArray[2],
                changeArray[3],
                changeArray[4],
                changeArray[5]);
        io.print(change);
    }


    public void viewBalance(BigDecimal balance) {
        io.print("Current balance: " + NumberFormat.getCurrencyInstance().format(balance));
    }

    public String promptContinue(){
        do {
            String response = io.readString("Would you like to continue? Enter 'y' to continue or 'n' to exit program");
            if (response.equals("y") || response.equals("n")) {
                return response;
            }
            else {
                System.out.println("Only enter y or n");
            }
        }while(true);
    }
}
