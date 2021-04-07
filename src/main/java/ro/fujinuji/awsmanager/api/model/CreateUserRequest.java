package ro.fujinuji.awsmanager.api.model;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class CreateUserRequest {
    @NotNull
    private String amazonUserId;

    @NotNull
    private String userName;

    @NotNull
    @Pattern(regexp = "^(.+)@(.+)$")
    private String userEmail;
}
