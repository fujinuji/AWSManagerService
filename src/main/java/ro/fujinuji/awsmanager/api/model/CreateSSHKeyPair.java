package ro.fujinuji.awsmanager.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSSHKeyPair {
    private String vmUserName;
}
