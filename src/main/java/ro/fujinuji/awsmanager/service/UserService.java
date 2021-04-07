package ro.fujinuji.awsmanager.service;

import ro.fujinuji.awsmanager.model.User;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;

public interface UserService {
    void saveUser(User user);

    User getUserByEmail(String userEmail) throws AWSManagerException;
}
