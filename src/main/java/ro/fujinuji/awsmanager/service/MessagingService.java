package ro.fujinuji.awsmanager.service;

import ro.fujinuji.awsmanager.model.EmailTemplates;
import ro.fujinuji.awsmanager.model.SendMessageRequest;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.utils.PlaceholderReplacer;

public interface MessagingService {
    void sendMessage(SendMessageRequest messageRequest, PlaceholderReplacer placeholderReplacer, EmailTemplates emailTemplate) throws AWSManagerException;
}
