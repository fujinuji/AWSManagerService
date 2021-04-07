package ro.fujinuji.awsmanager.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateIamConfigurationRequest {
    private String userId;
    private String iamKeyId;
    private String iamAccessKey;
}
