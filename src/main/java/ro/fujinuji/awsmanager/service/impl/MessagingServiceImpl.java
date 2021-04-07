package ro.fujinuji.awsmanager.service.impl;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.stereotype.Service;
import ro.fujinuji.awsmanager.model.SendMessageRequest;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.model.exception.MessageNotSentException;
import ro.fujinuji.awsmanager.service.MessagingService;

@Service
public class MessagingServiceImpl implements MessagingService {


    static final String FROM = "alexandru.cojoc1@gmail.com";

    static final String SUBJECT = "AWS Manager platform access";

    static final String HTMLBODY = "<h1>Amazon SES test (AWS SDK for Java)</h1>"
            + "<p>This email was sent with <a href='https://aws.amazon.com/ses/'>"
            + "Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>"
            + "AWS SDK for Java</a>";

    @Override
    public void sendMessage(SendMessageRequest messageRequest) throws AWSManagerException {
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIA4B3KZOHADVVMNQLR", "cIM0+PMrMVD445i5w/Pi9ky0+0M1KFrIqbHvX5qd\n"));

        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withCredentials(awsCredentialsProvider)
                            .withRegion(Regions.EU_CENTRAL_1).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(messageRequest.getReceiverEmail()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(HTMLBODY)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(FROM);
            client.sendEmail(request);
        } catch (Exception e) {
            throw new MessageNotSentException("Message could not be sent", e);
        }
    }
}
