package ro.fujinuji.awsmanager.service;

import org.springframework.web.multipart.MultipartFile;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;

import java.io.File;

public interface BucketService {

    String uploadFile(String instanceName, MultipartFile multipartFile) throws Exception;

    String getSSHKey(String fileName) throws AWSManagerException;

    void saveFileToS3Bucket(String fileName, File file) throws AWSManagerException;

}
