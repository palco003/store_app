package cen4270;

import cen4270.exceptions.RegisterUserException;
import cen4270.models.CreditCard;
import cen4270.models.Region;
import cen4270.models.User;
import cen4270.services.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by pabloalcocer on 9/21/16.
 */

public class AccountServiceTest {
    private AccountService accountService;
    private User fakeUser;
    private CreditCard fakeCC;
    private Exception expectedException;

    @Before
    public void setup() {
        accountService = new AccountService();
        fakeCC = new CreditCard("1234567887654321", new Date(120, 5, 5));
        fakeUser = new User("Pablo", "Alcocer", "palco@fiu.edu", fakeCC, Region.East, true);
        expectedException = null;
    }

    @Test
    public void registerUser_DuplicateEmail_RegisterUserException() {
        accountService.setUser(fakeUser);

        try {
            accountService.registerUser(fakeUser);
        } catch (RegisterUserException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedException.getMessage(), "Email address already exists");
    }

    @Test
    public void registerUser_NullFirstName_RegisterUserException() throws Exception {
        fakeUser.setFirstName(null);

        try {
            accountService.registerUser(fakeUser);
        } catch (RegisterUserException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedException.getMessage(), "User must have a first and last name");
    }

    @Test
    public void registerUser_NullLastName_RegisterUserException() throws Exception {
        fakeUser.setLastName(null);

        try {
            accountService.registerUser(fakeUser);
        } catch (RegisterUserException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedException.getMessage(), "User must have a first and last name");
    }

    @Test
    public void registerUser_EmptyFirstName_RegisterUserException() throws Exception {
        fakeUser.setFirstName("");

        try {
            accountService.registerUser(fakeUser);
        } catch (RegisterUserException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedException.getMessage(), "User must have a first and last name");
    }

    @Test
    public void registerUser_EmptyLastName_RegisterUserException() throws Exception {
        fakeUser.setLastName("");

        try {
            accountService.registerUser(fakeUser);
        } catch (RegisterUserException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedException.getMessage(), "User must have a first and last name");
    }

    @Test
    public void registerUser_BadEmail_RegisterUserException() throws Exception {
        fakeUser.setEmail("palco003");

        try {
            accountService.registerUser(fakeUser);
        } catch (RegisterUserException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedException.getMessage(), "Email address is not valid");
    }

    @Test
    public void registerUser_InvalidCharactersInCC_RegisterUserException() throws Exception {
        fakeCC.setNumber("abcdef1234567890");

        try {
            accountService.registerUser(fakeUser);
        } catch (RegisterUserException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedException.getMessage(), "Credit card is not valid");
    }

    @Test
    public void registerUser_ValidCharactersInCC_RegisterUserException() throws Exception {
        try {
            accountService.registerUser(fakeUser);
        } catch (RegisterUserException e) {
            expectedException = e;
        }
        Assert.assertNull(expectedException);
    }

    @Test
    public void registerUser_BadLengthCC_RegisterUserException() throws Exception {
        fakeCC.setNumber("12345");
        try {
            accountService.registerUser(fakeUser);
        } catch (RegisterUserException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedException.getMessage(), "Credit card is not valid");
    }

    @Test
    public void registerUser_BadExpirationInCC_RegisterUserException() throws Exception {
        fakeCC.setExpirationDate(new Date(100, 1, 1));

        try {
            accountService.registerUser(fakeUser);
        } catch (RegisterUserException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedException.getMessage(), "Credit card is not valid");
    }

    @Test
    public void getRegisteredUser_EmptyList_ReturnsNull() {
        Assert.assertNull(accountService.getRegisteredUser("palco@fiu.edu"));
    }

    @Test
    public void getRegisteredUser_UserFound_ReturnsUser() {
        accountService.setUser(fakeUser);

        Assert.assertEquals(fakeUser, accountService.getRegisteredUser("palco@fiu.edu"));
    }

    @Test
    public void getRegisteredUser_UserNotFound_ReturnsUser() {
        accountService.setUser(fakeUser);
        fakeUser.setEmail("abc@gmail.com");

        Assert.assertNotEquals(fakeUser, accountService.getRegisteredUser("palco@fiu.edu"));
    }
}