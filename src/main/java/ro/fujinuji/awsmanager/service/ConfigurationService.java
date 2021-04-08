package ro.fujinuji.awsmanager.service;

import ro.fujinuji.awsmanager.model.UserIamConfiguration;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;

public interface ConfigurationService {

    void saveIamConfiguration(UserIamConfiguration iamConfiguration) throws AWSManagerException;

    UserIamConfiguration getConfigurationByAmazonUser(String userId) throws AWSManagerException;

    UserIamConfiguration getConfigurationByUser(String userId) throws AWSManagerException;
}
