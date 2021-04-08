package ro.fujinuji.awsmanager.api.converters;

import ro.fujinuji.awsmanager.api.model.CreateIamConfigurationRequest;
import ro.fujinuji.awsmanager.model.User;
import ro.fujinuji.awsmanager.model.UserIamConfiguration;

public class ConfigurationConverter {

    public static UserIamConfiguration iamFromUi(CreateIamConfigurationRequest createIamConfigurationRequest) {
        UserIamConfiguration userIamConfiguration = new UserIamConfiguration();
        userIamConfiguration.setIamKeyId(createIamConfigurationRequest.getIamKeyId());
        userIamConfiguration.setIamSecretKey(createIamConfigurationRequest.getIamSecretKey());

        User user = new User();
        user.setInternalUserId(createIamConfigurationRequest.getUserId());
        userIamConfiguration.setUser(user);

        return userIamConfiguration;
    }
}
