package ro.fujinuji.awsmanager.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity(name = "iam_configuration")
public class UserIamConfiguration {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String configurationId;
    private String iamKeyId;
    private String iamSecretKey;

    @OneToOne
    private User user;

}
