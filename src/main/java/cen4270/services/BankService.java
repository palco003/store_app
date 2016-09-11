package cen4270.services;

import cen4270.exceptions.ChargeCreditCardException;
import cen4270.models.CreditCard;

import java.util.Date;

/**
 * This service provides functionality to process transactions
 */
public class BankService {
    /**
     * Verify if a credit card is valid
     * @param creditCard The credit card to validate
     * @return true if the credit card is valid. False, otherwise.
     */
    public boolean isCreditCardValid(CreditCard creditCard) {
        if(creditCard.getNumber().length() != 16) {
            return false;
        }

        if(creditCard.getNumber().matches("\\[0-9\\]\\+$")) {
            return false;
        }

        if(creditCard.getExpirationDate().after(new Date())) {
            return false;
        }

        return true;
    }

    /**
     * Charges a credit card
     * @param creditCard The credit card to charge
     * @param amount The amount to charge the credit card
     * @throws ChargeCreditCardException Exception thrown if the credit cannot be charged for any reason
     */
    public void chargeCreditCard(CreditCard creditCard, int amount) throws ChargeCreditCardException {
        System.out.println("Charging a credit card. Amount: " + amount + "; Number: [Number omitted for security]...");

        try {
            // The following sleep is used to simulate the bank service making
            // a request to charge the credit card's bank. A real implementation
            // would make the request over the internet and wait for the response.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("...Credit card charged");
    }
}
