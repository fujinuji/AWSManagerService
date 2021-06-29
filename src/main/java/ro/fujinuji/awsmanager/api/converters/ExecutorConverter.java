package ro.fujinuji.awsmanager.api.converters;

import ro.fujinuji.awsmanager.api.model.CreateExecutorRequest;
import ro.fujinuji.awsmanager.api.model.ExecutorResponse;
import ro.fujinuji.awsmanager.model.ExecutionUnit;
import ro.fujinuji.awsmanager.model.Executor;

import java.util.stream.Collectors;

public class ExecutorConverter {

    public static Executor fromUi(CreateExecutorRequest createExecutorRequest) {
        Executor executor= new Executor();

        executor.setName(createExecutorRequest.getExecutorName());
        executor.setExecutionUnits(createExecutorRequest.getCommands().stream()
        .map(command -> {
            ExecutionUnit executionUnit = new ExecutionUnit();
            executionUnit.setCommand(command);
            return executionUnit;
        })
        .collect(Collectors.toList()));

        return executor;
    }

    public static ExecutorResponse toUi(Executor executor) {
        ExecutorResponse executorResponse = new ExecutorResponse();

        executorResponse.setExecutorName(executor.getName());
        executorResponse.setCommands(executor.getExecutionUnits().stream().map(ExecutionUnit::getCommand).collect(Collectors.toList()));
        executorResponse.setId(executor.getExecutorId());
        return executorResponse;
    }
}
