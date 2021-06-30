package ro.fujinuji.awsmanager.model;

import org.springframework.core.io.ClassPathResource;
import ro.fujinuji.awsmanager.model.exception.ConfigurationNotFoundException;

import java.io.IOException;
import java.nio.charset.Charset;

public enum EmailTemplates {

    PLATFORM_ACCESS("Platform access", "platform-access.html"),
    USER_CREATE("IAM User create", "iam-user-create.html"),
    SSH_FILE("SSH key", "ssh-key.html");

    private String templateName;
    private String header;

    EmailTemplates(String header, String templateName) {
        this.header = header;
        this.templateName = templateName;
    }

    private static final String EMAIL_TEMPLATES_FOLDER = "email_templates/";

    public String getTemplate() throws ConfigurationNotFoundException {
        try {
            return new String(new ClassPathResource(EMAIL_TEMPLATES_FOLDER + templateName)
                    .getInputStream().readAllBytes(),
                    Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new ConfigurationNotFoundException();
        }
    }

    public String getHeader() {
        return header;
    }
}
