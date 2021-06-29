package ro.fujinuji.awsmanager.service;

import ro.fujinuji.awsmanager.model.exception.AWSManagerException;

import java.util.List;

public interface AmazonEC2Service {

    List<String> getInstancesFromUser(String userId) throws AWSManagerException;
}
