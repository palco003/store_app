package cen4270.services;

import cen4270.exceptions.RegisterUserException;
import cen4270.models.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This service is used to manage user accounts
 */
public class AccountService {
    private List<User> users;
    private BankService bankService;

    public AccountService() {
        this.users = new LinkedList<User>();
        this.bankService = new BankService();
    }

    /**
     * Register a user account
     * @param user The user account to register
     * @throws RegisterUserException Exception thrown if user cannot be registered
     */
    public void registerUser(User user) throws RegisterUserException {
        if(getRegisteredUser(user.getEmail()) != null) {
            throw new RegisterUserException("Email address already exists");
        }

        if(user.getFirstName() == null || user.getFirstName().length() == 0
                || user.getLastName() == null || user.getLastName().length() == 0) {
            throw new RegisterUserException("User must have a first and last name");
        }

        if(!user.getEmail().matches("^.*[@].*[.].*$")) {
            throw new RegisterUserException(("Email address is not valid"));
        }

        if(!bankService.isCreditCardValid(user.getCreditCard())) {
            throw new RegisterUserException(("Credit card is not valid"));
        }

        users.add(user);
    }

    /**
     * Returns a registered user
     * @param email The email of the registered user to look for
     * @return The user or null, if the email does not belong to a registered user
     */
    public User getRegisteredUser(String email) {
        for(int i = 0; i < users.size()-1; i++) {
            if(users.get(i).getEmail().equalsIgnoreCase(email)) {
                return users.get(i);
            }
        }

        return null;
    }
}
