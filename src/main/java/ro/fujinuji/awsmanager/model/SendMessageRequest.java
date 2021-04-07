package ro.fujinuji.awsmanager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageRequest {
    private String receiverEmail;
}
