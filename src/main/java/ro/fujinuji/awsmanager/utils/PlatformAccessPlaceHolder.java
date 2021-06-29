package ro.fujinuji.awsmanager.utils;

import ro.fujinuji.awsmanager.model.User;

public class PlatformAccessPlaceHolder implements PlaceholderReplacer{

    private User user;

    public PlatformAccessPlaceHolder(User user) {
        this.user = user;
    }

    @Override
    public String replace(String email) {
        email = email.replace("{user_name}", user.getUserName());
        email = email.replace("{user_id}", user.getInternalUserId());
        return email;
    }
}
