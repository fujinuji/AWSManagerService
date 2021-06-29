package ro.fujinuji.awsmanager.service.impl;

import org.springframework.stereotype.Service;
import ro.fujinuji.awsmanager.model.SSHKey;
import ro.fujinuji.awsmanager.model.UserIamConfiguration;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.model.exception.ConfigurationNotFoundException;
import ro.fujinuji.awsmanager.repository.IamConfigurationRepository;
import ro.fujinuji.awsmanager.repository.SSHKeyRepository;
import ro.fujinuji.awsmanager.service.ConfigurationService;

import java.io.File;
import java.util.List;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    private final IamConfigurationRepository iamConfigurationRepository;
    private final SSHKeyRepository sshKeyRepository;

    public ConfigurationServiceImpl(IamConfigurationRepository iamConfigurationRepository, SSHKeyRepository sshKeyRepository) {
        this.iamConfigurationRepository = iamConfigurationRepository;
        this.sshKeyRepository = sshKeyRepository;
    }

    @Override
    public void saveIamConfiguration(UserIamConfiguration iamConfiguration) throws AWSManagerException {
        iamConfigurationRepository.getByUserId(iamConfiguration.getUser().getInternalUserId())
                .ifPresent(iamConfiguration1 -> iamConfiguration.setConfigurationId(iamConfiguration1.getConfigurationId()));

        iamConfigurationRepository.save(iamConfiguration);
    }

    @Override
    public UserIamConfiguration getConfigurationByAmazonUser(String userId) throws AWSManagerException {
        return iamConfigurationRepository.getByAmazonUserId(userId).orElseThrow(ConfigurationNotFoundException::new);
    }

    @Override
    public UserIamConfiguration getConfigurationByUser(String userId) throws AWSManagerException {
        return iamConfigurationRepository.getByUserId(userId).orElseThrow(ConfigurationNotFoundException::new);
    }

    @Override
    public void saveSSHKeyToUser(SSHKey sshKey) throws AWSManagerException {
        sshKeyRepository.save(sshKey);
    }

    @Override
    public SSHKey getSSHKeyPair(String instanceName, String userId) throws AWSManagerException {
        return sshKeyRepository.getByInstanceNameAndUserId(instanceName, userId).orElseThrow(ConfigurationNotFoundException::new);
    }

    @Override
    public List<SSHKey> getSSHKeysByUserId(String userId) throws AWSManagerException {
        return sshKeyRepository.getByUserId(userId);
    }

    @Override
    public void deleteSSHConfiguration(String configurationId) throws AWSManagerException {
        sshKeyRepository.deleteById(configurationId);
    }

    @Override
    public List<String> getExistingInstances(String userId) throws AWSManagerException {
        return sshKeyRepository.getInstancesByUserId(userId);
    }
}
