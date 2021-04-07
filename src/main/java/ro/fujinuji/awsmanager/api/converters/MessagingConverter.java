package ro.fujinuji.awsmanager.api.converters;

import ro.fujinuji.awsmanager.api.model.CreateMessageRequest;
import ro.fujinuji.awsmanager.model.SendMessageRequest;

public class MessagingConverter {

    public static SendMessageRequest fromUi(CreateMessageRequest createMessageRequest) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setReceiverEmail(createMessageRequest.getReceiverEmail());

        return sendMessageRequest;
    }
}
