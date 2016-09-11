package cen4270.services;

import cen4270.exceptions.ChargeCreditCardException;
import cen4270.exceptions.PurchaseException;
import cen4270.exceptions.SendEmailException;
import cen4270.models.Email;
import cen4270.models.Item;
import cen4270.models.User;

/**
 * This service is used to make purchases
 */
public class PurchaseService {
    private AccountService accountService;
    private BankService bankService;
    private InventoryService inventoryService;
    private NotificationService notificationService;

    public PurchaseService(AccountService accountService, BankService bankService, InventoryService inventoryService, NotificationService notificationService) {
        this.accountService = accountService;
        this.bankService = bankService;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
    }

    /**
     * Purchase an item
     * @param userEmail The email of the user making the purchase
     * @param itemId The ID of item to purchase
     * @param quantity The amount of items to purchase
     */
    public void purchaseItem(String userEmail, String itemId, int quantity) throws PurchaseException, ChargeCreditCardException, SendEmailException {
        User user = accountService.getRegisteredUser(userEmail);

        if(user == null) {
            throw new PurchaseException("User does not exist");
        }

        Item item = inventoryService.getItem(itemId);

        if(item == null) {
            throw new PurchaseException("Item does not exist");
        }

        int totalPrice = calculatePrice(user, item);
        bankService.chargeCreditCard(user.getCreditCard(), totalPrice);

        notificationService.sendEmail(new Email(
                user.getEmail(),
                "Thank you for your purchase of " + item.getName() + "!",
                "Your ordered has been confirmed and will ship as soon as possible"
        ));
    }

    private int calculatePrice(User user, Item item) throws PurchaseException {
        int totalPrice = item.getPrice();
        int shippingCost = 0;
        int tax = 0;

        switch(user.getRegion()) {
            case East:
                shippingCost = 5;
                tax = 2;
                break;
            case West:
                shippingCost = 10;
                tax = 5;
                break;
            case Pacific:
                shippingCost = 20;
                tax = 10;
                break;
            default:
                throw new PurchaseException("Region is not supported");
        }

        totalPrice += shippingCost + tax;

        if(user.isPremiumUser()) {
            // Apply discount to premium users
            totalPrice -= 2;
        }

        return totalPrice;
    }
}
