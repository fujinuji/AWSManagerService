package ro.fujinuji.awsmanager.api.converters;

import ro.fujinuji.awsmanager.api.model.AvailableInstance;
import ro.fujinuji.awsmanager.api.model.SavedInstanceResponse;
import ro.fujinuji.awsmanager.model.SSHKey;

public class InstancesConverter {

    public static SavedInstanceResponse toUi(SSHKey sshKey) {
        SavedInstanceResponse savedInstanceResponse = new SavedInstanceResponse();
        savedInstanceResponse.setInstance(sshKey.getInstanceName());
        savedInstanceResponse.setUsername(sshKey.getSshUserName());
        savedInstanceResponse.setConfigurationId(sshKey.getSshKeyId());
        return savedInstanceResponse;
    }

    public static AvailableInstance toUi(String instanceName) {
        AvailableInstance availableInstance = new AvailableInstance();
        availableInstance.setId(instanceName);
        availableInstance.setName(instanceName);

        return availableInstance;
    }
}
