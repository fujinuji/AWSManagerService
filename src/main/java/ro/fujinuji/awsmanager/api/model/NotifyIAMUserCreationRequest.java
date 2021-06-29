package ro.fujinuji.awsmanager.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifyIAMUserCreationRequest {
    private String accountId;
    private String userName;
    private String password;

    private String user;
    private String emailReceiver;
}
