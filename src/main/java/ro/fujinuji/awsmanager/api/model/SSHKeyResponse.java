package ro.fujinuji.awsmanager.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SSHKeyResponse {
    private String instanceUsername;
    private String rsaKey;
}
