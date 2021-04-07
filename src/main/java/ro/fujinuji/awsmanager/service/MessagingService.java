package ro.fujinuji.awsmanager.service;

import ro.fujinuji.awsmanager.model.SendMessageRequest;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;

public interface MessagingService {
    void sendMessage(SendMessageRequest messageRequest) throws AWSManagerException;
}
