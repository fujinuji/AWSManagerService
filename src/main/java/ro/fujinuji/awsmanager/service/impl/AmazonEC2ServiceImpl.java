package ro.fujinuji.awsmanager.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import org.springframework.stereotype.Service;
import ro.fujinuji.awsmanager.model.UserIamConfiguration;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.service.AmazonEC2Service;
import ro.fujinuji.awsmanager.service.ConfigurationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AmazonEC2ServiceImpl implements AmazonEC2Service {

    private final ConfigurationService configurationService;

    public AmazonEC2ServiceImpl(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public List<String> getInstancesFromUser(String userId) throws AWSManagerException {
        UserIamConfiguration iamConfiguration = configurationService.getConfigurationByAmazonUser(userId);
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(iamConfiguration.getIamKeyId(), iamConfiguration.getIamSecretKey());

        List<Instance> instanceList = new ArrayList<>();

        AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        boolean done = false;

        DescribeInstancesRequest request = new DescribeInstancesRequest();
        while(!done) {
            DescribeInstancesResult response = ec2.describeInstances(request);

            for(Reservation reservation : response.getReservations()) {
                instanceList.addAll(reservation.getInstances());
            }

            request.setNextToken(response.getNextToken());

            if(response.getNextToken() == null) {
                done = true;
            }
        }

        return instanceList.stream().map(instance -> instance.getTags().stream()
                    .filter(tag -> "name".toLowerCase().equals(tag.getKey().toLowerCase()))
                    .findFirst())
                .map(instance -> instance.orElse(null))
                .filter(Objects::nonNull)
                .map(tag -> tag.withKey("name").getValue())
                .collect(Collectors.toList());
    }
}
