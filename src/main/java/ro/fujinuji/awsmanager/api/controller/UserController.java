package ro.fujinuji.awsmanager.api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.fujinuji.awsmanager.api.model.CreateUserRequest;
import ro.fujinuji.awsmanager.api.converters.*;
import ro.fujinuji.awsmanager.model.User;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User user = UserConverter.fromUiUser(createUserRequest);

        userService.saveUser(user);
    }

    @GetMapping("/{userEmail}")
    public User getUserByEmail(@PathVariable("userEmail") String userEmail) throws AWSManagerException {
        return userService.getUserByEmail(userEmail);
    }
}
