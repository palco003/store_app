package cen4270.clients;

import cen4270.exceptions.SendEmailException;
import cen4270.models.Email;

/**
 * Created by pabloalcocer on 9/26/16.
 */
public class FakeNotificationClient implements NotificationClientInterface {
    public Email email;
    public SendEmailException willThrow;

    public void sendEmail(Email email) throws SendEmailException {
        this.email = email;

        if(willThrow != null){
            throw willThrow;
        }
    }

    public void setWillThrow(SendEmailException willThrow) {
        this.willThrow = willThrow;
    }
}
