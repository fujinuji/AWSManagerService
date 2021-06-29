package ro.fujinuji.awsmanager.utils;

import ro.fujinuji.awsmanager.api.model.NotifyIAMUserCreationRequest;

public class UserCreatePlaceholderReplacer implements PlaceholderReplacer{

    private NotifyIAMUserCreationRequest notifyIAMUserCreationRequest;

    public UserCreatePlaceholderReplacer(NotifyIAMUserCreationRequest notifyIAMUserCreationRequest) {
        this.notifyIAMUserCreationRequest = notifyIAMUserCreationRequest;
    }

    @Override
    public String replace(String email) {
        email = email.replace("{user_name}", notifyIAMUserCreationRequest.getUser());
        email = email.replace("{account_id}", notifyIAMUserCreationRequest.getAccountId());
        email = email.replace("{user_id}", notifyIAMUserCreationRequest.getUserName());
        email = email.replace("{user_password}", notifyIAMUserCreationRequest.getPassword());
        return email;
    }
}
