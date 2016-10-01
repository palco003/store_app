package cen4270.services;

import cen4270.exceptions.RegisterUserException;
import cen4270.models.CreditCard;
import cen4270.models.User;

/**
 * Created by pabloalcocer on 9/26/16.
 */
public class FakeAccountService implements AccountServiceInterface{
    public User user;
    public String email;
    public CreditCard creditCard;

    public void registerUser(User user) throws RegisterUserException{
        this.user = user;
    }
    public User getRegisteredUser(String email){
        this.email = email;
        return user;
    }
}
