package ro.fujinuji.awsmanager.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.fujinuji.awsmanager.api.model.CreateMessageRequest;
import ro.fujinuji.awsmanager.model.SendMessageRequest;
import ro.fujinuji.awsmanager.model.User;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.service.MessagingService;
import ro.fujinuji.awsmanager.api.converters.*;
import ro.fujinuji.awsmanager.service.UserService;

@RestController
@RequestMapping("/api/messaging")
public class MessagingController {

    private final MessagingService messagingService;
    private final UserService userService;

    public MessagingController(MessagingService messagingService, UserService userService) {
        this.messagingService = messagingService;
        this.userService = userService;
    }

    @PostMapping("/send")
    public void sendEmail(@RequestBody CreateMessageRequest createMessageRequest) throws AWSManagerException {
        SendMessageRequest sendMessageRequest = MessagingConverter.fromUi(createMessageRequest);
        User receiver = userService.getUserByEmail(sendMessageRequest.getReceiverEmail());

        messagingService.sendMessage(sendMessageRequest, receiver);
    }
}
