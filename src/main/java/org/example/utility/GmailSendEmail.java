package org.example.utility;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import io.cucumber.testng.CucumberOptions;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.Base64;

public class GmailSendEmail {

    private static final String APPLICATION_NAME = "Gmail API Java Send Email";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    public static void main(String... args) throws Exception {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, TestSetup.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // List of recipient email addresses
        final String RECIPIENTS =
//                "ashishrana6124@gmail.com, " +
//                "ashish.rana1024@gmail.com," +
                  "ashishrana@wattmonk.com";

        // Send the email to each recipient
            MimeMessage email = createEmailWithAttachment(RECIPIENTS, "ashishrana@wattmonk.com", "Automation Report", createHtmlTableContent(), Paths.get("target/cucumber-reports.html").toAbsolutePath().toString());
            sendMessage(service, "me", email);

    }
    /**
     * Creates an email with the specified parameters and an attachment.
     *
     * @param to       The comma-separated list of recipient email addresses.
     * @param from     The sender's email address.
     * @param subject  The subject of the email.
     * @param bodyText The body text of the email.
     * @param filePath The file path of the attachment.
     * @return A MimeMessage object representing the email.
     * @throws MessagingException If there is an issue with email construction.
     * @throws IOException        If there is an issue with file handling.
     */

    // Method to create an email with an attachment
    public static MimeMessage createEmailWithAttachment(String to, String from, String subject, String bodyText, String filePath)
            throws MessagingException, IOException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(from));
        email.addRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
        email.setSubject(subject);

        // Create the message body
        MimeBodyPart body = new MimeBodyPart();
        body.setContent(bodyText, "text/html");  // For HTML content

        // Create the attachment
        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(filePath);

        // Combine body and attachment
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(body);
        multipart.addBodyPart(attachment);

        email.setContent(multipart);

        return email;
    }

    /**
     * Sends an email using the Gmail API.
     *
     * @param service The Gmail service instance.
     * @param userId  The user ID (usually "me" for authenticated user).
     * @param email   The MimeMessage object representing the email.
     * @throws MessagingException If there is an issue with email sending.
     * @throws IOException        If there is an issue with I/O operations.
     */

    // Method to send the email
    public static void sendMessage(Gmail service, String userId, MimeMessage email)
            throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.getEncoder().encodeToString(rawMessageBytes);

        Message message = new Message();
        message.setRaw(encodedEmail);

        service.users().messages().send(userId, message).execute();
    }
    public static String createHtmlTableContent() {
        StringBuilder report = new StringBuilder();

        report.append("<html><body>");
        report.append("<h2>Cucumber Test Results</h2>");
        report.append("<table border='1'>");
        report.append("<tr><th>Total Scenarios</th><th>Passed</th><th>Failed</th><th>Skipped</th></tr>");
        report.append("<tr>")
                .append("<td>").append(5).append("</td>")
                .append("<td>").append(4).append("</td>")
                .append("<td>").append(3).append("</td>")
                .append("<td>").append(1).append("</td>")
                .append("</tr>");
        report.append("</table>");
        report.append("</body></html>");

        return report.toString();
    }


}
