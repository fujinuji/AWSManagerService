package ro.fujinuji.awsmanager.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveDefaultSSHKeyRequest {
    private String sshKey;
    private String receiverEmail;
}
