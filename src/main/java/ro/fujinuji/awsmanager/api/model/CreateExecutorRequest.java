package ro.fujinuji.awsmanager.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateExecutorRequest {

    private String executorName;
    private List<String> commands;
}
