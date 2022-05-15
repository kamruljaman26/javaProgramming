package controllers;

import java.util.Scanner;

/**
 * 2) for membership card, just check if the membership card is getting scanned.
 * Ask the Customer to manually input the card number. Take that input and save
 * it in a variable called membership card number and print the number in receipt
 * inside checkout.Java
 */
public class MembershipCard {

    // membership card is a type of card
    private String cardNumber;

    // empty constructor
    public MembershipCard() {}

    public MembershipCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // scan card
//    
    public void scanCard(){
    	int counter = 0;
        while(counter == 0 ){ 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your membership card number: ");
        
        int CNLength;// this is the valid length of card number. for example 16 or 12 digits. 
        //We dont have any specific numbers so not giving this veriable any specific number now.
        
        
        
        
        cardNumber = scanner.nextLine();
            
        int typedLength = cardNumber.length();//length of the card number that customer typed in.
        int intCN = Integer.valueOf(cardNumber); 
        if (intCN >= 0 && typedLength == CNLength){
        System.out.println("Membership card scanned successfully."); 
        counter++;
    	}
    	else{
    	System.out.println("Invalid Card number. Please try again.");
    	}
    }
    }

    // get card number
    public String getCardNumber() {
        return cardNumber;
    }
}
