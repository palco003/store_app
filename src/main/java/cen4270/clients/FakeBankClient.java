package cen4270.clients;

import cen4270.exceptions.ChargeCreditCardException;
import cen4270.models.CreditCard;

/**
 * Created by pabloalcocer on 9/26/16.
 */
public class FakeBankClient implements BankClientInterface{
    public CreditCard creditCard;
    public int amount;
    public ChargeCreditCardException willThrow;

    public void chargeCreditCard(CreditCard creditCard, int amount) throws ChargeCreditCardException{
        this.creditCard = creditCard;
        this.amount = amount;

        if(willThrow != null){
            throw willThrow;
        }
    }
}
