package cen4270.models;

import java.util.Date;

public class CreditCard {
    private String number;

    private Date expirationDate;

    public CreditCard(String number, Date expirationDate) {
        this.number = number;
        this.expirationDate = expirationDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
