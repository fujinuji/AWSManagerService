package ro.fujinuji.awsmanager.api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.fujinuji.awsmanager.api.model.CreateIamConfigurationRequest;
import ro.fujinuji.awsmanager.api.converters.*;
import ro.fujinuji.awsmanager.model.UserIamConfiguration;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.service.ConfigurationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PostMapping(path = "/iam", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveIamConfiguration(@Valid @RequestBody CreateIamConfigurationRequest createIamConfigurationRequest) throws AWSManagerException {
        UserIamConfiguration iamConfiguration = ConfigurationConverter.iamFromUi(createIamConfigurationRequest);

        configurationService.saveIamConfiguration(iamConfiguration);
    }

    @GetMapping("/iam/amazon/{amazonUserId}")
    public UserIamConfiguration getConfigurationForUserWithAmazonId(@PathVariable("amazonUserId") String amazonUserId) throws AWSManagerException {
        return configurationService.getConfigurationByAmazonUser(amazonUserId);
    }

    @GetMapping("/iam/{userId}")
    public UserIamConfiguration getConfigurationForUser(@PathVariable("userId") String userId) throws AWSManagerException {
        return configurationService.getConfigurationByUser(userId);
    }
}
