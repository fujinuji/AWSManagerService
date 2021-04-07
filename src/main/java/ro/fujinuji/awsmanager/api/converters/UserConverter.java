package ro.fujinuji.awsmanager.api.converters;

import ro.fujinuji.awsmanager.api.model.CreateUserRequest;
import ro.fujinuji.awsmanager.model.User;

public class UserConverter {

    public static User fromUiUser(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setAmazonUserId(createUserRequest.getAmazonUserId());
        user.setUserEmail(createUserRequest.getUserEmail());
        user.setUserName(createUserRequest.getUserName());
        return user;
    }
}
