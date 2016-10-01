package cen4270.clients;

import cen4270.exceptions.ChargeCreditCardException;
import cen4270.models.CreditCard;

/**
 * Created by pabloalcocer on 9/26/16.
 */
public interface BankClientInterface {
    void chargeCreditCard(CreditCard creditCard, int amt) throws ChargeCreditCardException;
}
