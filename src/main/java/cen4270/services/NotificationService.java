package cen4270.services;

import cen4270.exceptions.SendEmailException;
import cen4270.models.Email;

/**
 * This service is used to send emails
 */
public class NotificationService {
    /**
     * Sends an email
     * @param email The email to send
     * @throws SendEmailException Exception thrown if the email cannot be sent
     */
    public void sendEmail(Email email) throws SendEmailException {
        System.out.println("Sending email...");

        try {
            // The following sleep is used to simulate the time taken to
            // send an email over the web. A real implementation would
            // actually send out an email, but this should be enough for the
            // purpose of this assignment.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("...Email sent.");
    }
}
