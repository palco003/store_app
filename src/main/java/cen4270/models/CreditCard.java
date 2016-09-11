package cen4270.models;

import java.util.Date;

public class CreditCard {
    private String number;

    private Date expirationDate;

    private String securityCode;

    public CreditCard(String number, Date expirationDate, String securityCode) {
        this.number = number;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
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

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
