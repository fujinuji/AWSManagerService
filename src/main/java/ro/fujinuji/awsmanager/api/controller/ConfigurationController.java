package ro.fujinuji.awsmanager.api.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.fujinuji.awsmanager.api.model.CreateIamConfigurationRequest;
import ro.fujinuji.awsmanager.api.converters.*;
import ro.fujinuji.awsmanager.api.model.SSHKeyResponse;
import ro.fujinuji.awsmanager.api.model.SaveDefaultSSHKeyRequest;
import ro.fujinuji.awsmanager.model.*;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.service.*;
import ro.fujinuji.awsmanager.utils.TokenPlaceHolderReplacer;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    private final ConfigurationService configurationService;
    private final BucketService bucketService;
    private final UserService userService;
    private final TokenService tokenService;
    private final MessagingService messagingService;

    public ConfigurationController(ConfigurationService configurationService, BucketService bucketService, UserService userService, TokenService tokenService, MessagingService messagingService) {
        this.configurationService = configurationService;
        this.bucketService = bucketService;
        this.userService = userService;
        this.tokenService = tokenService;
        this.messagingService = messagingService;
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

    @PostMapping(value = "/ssh/{instance_name}/{user_id}/{vm_username}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveSSHFileForInstance(@PathVariable("instance_name") String instanceName,
                                       @PathVariable("user_id") String userId,
                                       @PathVariable("vm_username") String vmUsername,
                                       @RequestParam("file") MultipartFile multipartFile) throws Exception {
        String savedFileName = bucketService.uploadFile(instanceName + "_" + userId, multipartFile);
        SSHKey sshKey = new SSHKey();
        sshKey.setFileName(savedFileName);
        sshKey.setInstanceName(instanceName);
        sshKey.setSshUserName(vmUsername);
        sshKey.setUser(userService.getUserById(userId));

        configurationService.saveSSHKeyToUser(sshKey);
    }

    @PostMapping(value = "/ssh/{file-name}")
    public void saveSSHFileForInstanceDefault(@PathVariable("file-name") String fileName,
                                              @RequestBody SaveDefaultSSHKeyRequest saveDefaultSSHKeyRequest) throws Exception {

        File convFile = new File(fileName);
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(saveDefaultSSHKeyRequest.getSshKey().getBytes());
        fos.close();

        bucketService.saveFileToS3Bucket(fileName, convFile);
        Token token = new Token();
        token.setFileName(fileName);

        String tokenId = tokenService.saveToken(token);
        token.setTokenId(tokenId);

        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setReceiverEmail(saveDefaultSSHKeyRequest.getReceiverEmail());

        messagingService.sendMessage(sendMessageRequest, new TokenPlaceHolderReplacer(token), EmailTemplates.SSH_FILE);
    }

    @GetMapping(value = "/ssh/{token}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getDefaultSSHKey(@PathVariable("token") String token) throws AWSManagerException {
        Token resourceToken = tokenService.getTokenById(token);
        String sshKey = bucketService.getSSHKey(resourceToken.getFileName());

        Resource resource = new ByteArrayResource(sshKey.getBytes());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + token + "\"")
                .body(resource);
    }

    @GetMapping("/ssh/{instance_name}/{user_id}")
    public SSHKeyResponse getSSHFileForInstance(@PathVariable("instance_name") String instanceName,
                                       @PathVariable("user_id") String userId) throws AWSManagerException {

        SSHKey sshKey = configurationService.getSSHKeyPair(instanceName, userId);
        String finalSSHKey = bucketService.getSSHKey(sshKey.getFileName());

        SSHKeyResponse sshKeyResponse = new SSHKeyResponse();
        sshKeyResponse.setInstanceUsername(sshKey.getSshUserName());
        sshKeyResponse.setRsaKey(finalSSHKey);

        return sshKeyResponse;
    }

    @DeleteMapping("/ssh/{configuration_id}")
    public void deleteConfiguration(@PathVariable("configuration_id") String configurationId) throws AWSManagerException {
        configurationService.deleteSSHConfiguration(configurationId);
    }
}
