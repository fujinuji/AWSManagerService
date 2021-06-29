package ro.fujinuji.awsmanager.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.service.BucketService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {

    private static final String DEFAULT_SSH_PREFIX = "SSH_KEY_";

    private final AmazonS3 s3client;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    public BucketServiceImpl(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    @Override
    public String uploadFile(String fileNameSuffix, MultipartFile multipartFile) throws Exception {
        File file = convertMultiPartToFile(multipartFile);
        String fileName = generateFileName(fileNameSuffix);

        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return fileName;
    }

    @Override
    public String getSSHKey(String fileName) throws AWSManagerException {
        S3Object s3Object = s3client.getObject(bucketName, fileName);
        InputStreamReader streamReader = new InputStreamReader(s3Object.getObjectContent(), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        return reader.lines().collect(Collectors.joining("\n"));
    }

    @Override
    public void saveFileToS3Bucket(String fileName, File file) throws AWSManagerException {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(String instanceName) {
        return DEFAULT_SSH_PREFIX + instanceName;
    }
}
