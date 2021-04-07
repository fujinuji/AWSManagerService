package ro.fujinuji.awsmanager.service.impl;

import org.springframework.stereotype.Service;
import ro.fujinuji.awsmanager.model.UserIamConfiguration;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.model.exception.ConfigurationNotFoundException;
import ro.fujinuji.awsmanager.repository.IamConfigurationRepository;
import ro.fujinuji.awsmanager.service.ConfigurationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    private final IamConfigurationRepository iamConfigurationRepository;

    public ConfigurationServiceImpl(IamConfigurationRepository iamConfigurationRepository) {
        this.iamConfigurationRepository = iamConfigurationRepository;
    }

    @Override
    public void saveIamConfiguration(UserIamConfiguration iamConfiguration) {
        iamConfigurationRepository.save(iamConfiguration);
    }

    @Override
    public UserIamConfiguration getConfigurationByUser(String userId) throws AWSManagerException {
        return iamConfigurationRepository.getByAmazonUserId(userId).orElseThrow(ConfigurationNotFoundException::new);
    }
}
