package ro.fujinuji.awsmanager.service.impl;

import org.springframework.stereotype.Service;
import ro.fujinuji.awsmanager.model.User;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.model.exception.UserNotFoundException;
import ro.fujinuji.awsmanager.repository.UserRepository;
import ro.fujinuji.awsmanager.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String userEmail) throws AWSManagerException {
        return userRepository.getUserByUserEmail(userEmail).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserById(String userId) throws AWSManagerException {
        return userRepository.getUserByAmazonUserId(userId).orElseThrow(UserNotFoundException::new);
    }
}
