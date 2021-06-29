package ro.fujinuji.awsmanager.api.controller;

import org.springframework.web.bind.annotation.*;
import ro.fujinuji.awsmanager.api.converters.InstancesConverter;
import ro.fujinuji.awsmanager.api.model.AvailableInstance;
import ro.fujinuji.awsmanager.model.SSHKey;
import ro.fujinuji.awsmanager.api.model.SavedInstanceResponse;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.service.AmazonEC2Service;
import ro.fujinuji.awsmanager.service.ConfigurationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/instances")
public class InstanceController {

    private final ConfigurationService configurationService;
    private final AmazonEC2Service amazonEC2Service;

    public InstanceController(ConfigurationService configurationService, AmazonEC2Service amazonEC2Service) {
        this.configurationService = configurationService;
        this.amazonEC2Service = amazonEC2Service;
    }

    @GetMapping("/{user_id}")
    public List<SavedInstanceResponse> getSavedInstances(@PathVariable("user_id") String userId) throws AWSManagerException {
        List<SSHKey> sshKeys = this.configurationService.getSSHKeysByUserId(userId);

        return sshKeys.stream().map(InstancesConverter::toUi).collect(Collectors.toList());
    }

    @GetMapping("/available/{user_id}")
    public List<AvailableInstance> getAvailableInstances(@PathVariable("user_id") String userId) throws AWSManagerException {
        List<String> allInstances = amazonEC2Service.getInstancesFromUser(userId);
        List<String> existingSavedInstances = configurationService.getExistingInstances(userId);

        allInstances.removeIf(existingSavedInstances::contains);

        return allInstances.stream().map(InstancesConverter::toUi).collect(Collectors.toList());
    }
}
