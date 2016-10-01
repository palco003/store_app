package cen4270.clients;

import cen4270.exceptions.SendEmailException;
import cen4270.models.Email;

/**
 * Created by pabloalcocer on 9/26/16.
 */
public interface NotificationClientInterface {
    void sendEmail(Email email) throws SendEmailException;
}
