package cen4270.services;

import cen4270.exceptions.RegisterUserException;
import cen4270.models.User;

/**
 * Created by pabloalcocer on 9/26/16.
 */
public interface AccountServiceInterface {
    void registerUser(User user) throws RegisterUserException;
    User getRegisteredUser(String email);
}
