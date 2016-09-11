package cen4270.services;

import cen4270.exceptions.CannotRegisterUserException;
import cen4270.models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * This service is used to manage user accounts
 */
public class AccountService {
    private Map<String, User> users;
    private BankService bankService;

    public AccountService() {
        this.users = new HashMap<String, User>();
        this.bankService = new BankService();
    }

    /**
     * Register a user account
     * @param user The user account to register
     * @throws CannotRegisterUserException Exception thrown if user cannot be registered
     */
    public void registerUser(User user) throws CannotRegisterUserException {
        if(users.containsKey(user.getEmail())) {
            throw new CannotRegisterUserException("Email address already exists");
        }

        if(user.getFirstName() == null || user.getFirstName().length() == 0
                || user.getLastName() == null || user.getLastName().length() == 0) {
            throw new CannotRegisterUserException("User must have a first and last name");
        }

        if(!user.getEmail().matches("^.*[@].*[.].*$")) {
            throw new CannotRegisterUserException(("Email address is not valid"));
        }

        if(bankService.isCreditCardValid(user.getCreditCard())) {
            throw new CannotRegisterUserException(("Credit card is not valid"));
        }

        users.put(user.getEmail(), user);
    }

    /**
     * Returns a registered user
     * @param email The email of the registered user to look for
     * @return The user or null, if the email does not belong to a registered user
     */
    public User getRegisteredUser(String email) {
        return users.get(email);
    }
}
