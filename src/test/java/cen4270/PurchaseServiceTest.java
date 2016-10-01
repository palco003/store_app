package cen4270;

import cen4270.clients.FakeBankClient;
import cen4270.clients.FakeInventoryClient;
import cen4270.clients.FakeNotificationClient;
import cen4270.exceptions.PurchaseException;
import cen4270.models.*;
import cen4270.services.FakeAccountService;
import cen4270.services.PurchaseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by pabloalcocer on 9/21/16.
 */
public class PurchaseServiceTest {
    private FakeAccountService fakeAccountService;
    private FakeBankClient fakeBankClient;
    private FakeInventoryClient fakeInventoryClient;
    private FakeNotificationClient fakeNotificationClient;


    private PurchaseService fakePurchaseService;

    private User fakeUser;
    private Item fakeItem;
    private CreditCard fakeCC;
    private Exception expectedException;

    @Before
    public void setup() {
        fakeInventoryClient = new FakeInventoryClient();
        fakeAccountService = new FakeAccountService();
        fakeBankClient = new FakeBankClient();
        fakeNotificationClient = new FakeNotificationClient();
        fakePurchaseService = new PurchaseService(fakeAccountService, fakeBankClient, fakeInventoryClient, fakeNotificationClient);

        fakeCC = new CreditCard("1234567887654321", new Date(120, 5, 5));
        fakeUser = new User("Pablo", "Alcocer", "palco@fiu.edu", fakeCC, Region.East, true);
        fakeItem = new Item("123", "TV", 100, 5);
        expectedException = null;

    }

    @Test
    public void calculatePrice_EastRegionPremium_ReturnsPrice() {
        int expectedPrice = 0;

        try {
            expectedPrice = fakePurchaseService.calculatePrice(fakeUser, fakeItem);
        } catch (PurchaseException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedPrice, 105);
    }

    @Test
    public void calculatePrice_WestRegionPremium_ReturnsPrice() {
        int expectedPrice = 0;
        fakeUser.setRegion(Region.West);

        try {
            expectedPrice = fakePurchaseService.calculatePrice(fakeUser, fakeItem);
        } catch (PurchaseException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedPrice, 113);
    }

    @Test
    public void calculatePrice_PacificRegionPremium_ReturnsPrice() {
        int expectedPrice = 0;
        fakeUser.setRegion(Region.Pacific);

        try {
            expectedPrice = fakePurchaseService.calculatePrice(fakeUser, fakeItem);
        } catch (PurchaseException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedPrice, 128);
    }

    @Test
    public void calculatePrice_EastNotPremium_ReturnsPrice() {
        int expectedPrice = 0;
        fakeUser.setPremiumUser(false);

        try {
            expectedPrice = fakePurchaseService.calculatePrice(fakeUser, fakeItem);
        } catch (PurchaseException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedPrice, 107);
    }

    @Test
    public void calculatePrice_BadRegion_ThrowsPurchaseException() {
        fakeUser.setRegion(Region.South);

        try {
            fakePurchaseService.calculatePrice(fakeUser, fakeItem);
        } catch (PurchaseException e) {
            expectedException = e;
        }
        Assert.assertEquals(expectedException.getMessage(), "Region is not supported");
    }

    @Test
    public void calculatePrice_NullRegion_ThrowsNullPointerException() {
        fakeUser.setRegion(null);

        try {
            fakePurchaseService.calculatePrice(fakeUser, fakeItem);
        } catch (NullPointerException n) {
            expectedException = n;
        } catch (Exception e) {
            ;
        }

        Assert.assertNull(expectedException.getMessage());
    }

    @Test
    public void purchaseItem_NullUser_ThrowsPurchaseException() {
        try {
            fakePurchaseService.purchaseItem("palco", "123", 3);
        } catch (PurchaseException p) {
            expectedException = p;
        } catch (Exception e) {
            ;
        }
        Assert.assertEquals(expectedException.getMessage(), "User does not exist");
    }

    @Test
    public void purchaseItem_NullItem_ThrowsPurchaseException() {
        try {
            fakeAccountService.registerUser(fakeUser);
            fakePurchaseService.purchaseItem("palco@fiu.edu", "121", 3);
        } catch (PurchaseException p) {
            expectedException = p;
        } catch (Exception e) {
            ;
        }
        Assert.assertEquals(expectedException.getMessage(), "Item does not exist");
    }

    @Test
    public void purchaseItem_ItemExists_SendEmail() {
        fakePurchaseService.setNotificationClient(fakeNotificationClient);
        try{
            fakeAccountService.registerUser(fakeUser);
            fakePurchaseService.purchaseItem("palco@fiu.edu", "1", 1);
        } catch (Exception e){
            ;
        }

        Email expectedEmail = new Email(
                "palco@fiu.edu",
                "Thank you for your purchase of Book!",
                "Your ordered has been confirmed and will ship as soon as possible."
        );
        Assert.assertEquals(expectedEmail.toString(), fakeNotificationClient.email.toString());
    }

//    @Test
//    public void purchaseItem_ItemExists_ThrowsSendEmailException() {
//        fakeNotificationClient.setWillThrow(new SendEmailException("Unable to send."));
//
//        try{
//            fakeAccountService.registerUser(fakeUser);
//            fakePurchaseService.purchaseItem("palco@fiu.edu", "1", 1);
//        } catch (SendEmailException s){
//            expectedException = s;
//        } catch (Exception e){
//            ;
//        }
//        Assert.assertEquals(expectedException.getMessage(), "Unable to send.");
//    }
}