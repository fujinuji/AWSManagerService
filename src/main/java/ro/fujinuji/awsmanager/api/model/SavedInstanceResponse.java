package ro.fujinuji.awsmanager.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavedInstanceResponse {
    private String instance;
    private String username;
    private String configurationId;
}
