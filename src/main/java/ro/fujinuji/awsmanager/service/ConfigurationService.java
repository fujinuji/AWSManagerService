package ro.fujinuji.awsmanager.service;

import ro.fujinuji.awsmanager.model.SSHKey;
import ro.fujinuji.awsmanager.model.UserIamConfiguration;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;

import java.io.File;
import java.util.List;

public interface ConfigurationService {

    void saveIamConfiguration(UserIamConfiguration iamConfiguration) throws AWSManagerException;

    UserIamConfiguration getConfigurationByAmazonUser(String userId) throws AWSManagerException;

    UserIamConfiguration getConfigurationByUser(String userId) throws AWSManagerException;

    void saveSSHKeyToUser(SSHKey sshKey) throws AWSManagerException;

    SSHKey getSSHKeyPair(String instanceName, String userId) throws AWSManagerException;

    List<SSHKey> getSSHKeysByUserId(String userId) throws AWSManagerException;

    void deleteSSHConfiguration(String configurationId) throws AWSManagerException;

    List<String> getExistingInstances(String userId) throws AWSManagerException;
}
