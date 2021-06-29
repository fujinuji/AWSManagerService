package ro.fujinuji.awsmanager.api.controller;

import org.springframework.web.bind.annotation.*;
import ro.fujinuji.awsmanager.api.converters.ExecutorConverter;
import ro.fujinuji.awsmanager.api.model.CreateExecutorRequest;
import ro.fujinuji.awsmanager.api.model.ExecutorResponse;
import ro.fujinuji.awsmanager.model.ExecutionUnit;
import ro.fujinuji.awsmanager.model.Executor;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.service.ExecutorService;
import ro.fujinuji.awsmanager.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/executor")
public class ExecutorController {

    private final ExecutorService executorService;
    private final UserService userService;

    public ExecutorController(ExecutorService executorService, UserService userService) {
        this.executorService = executorService;
        this.userService = userService;
    }

    @PostMapping("/{user_id}")
    public void saveExecutor(@RequestBody CreateExecutorRequest createExecutorRequest, @PathVariable("user_id") String userId) throws AWSManagerException {
        Executor executor = ExecutorConverter.fromUi(createExecutorRequest);
        executor.setUser(userService.getUserById(userId));

        executorService.saveExecutor(executor);
    }

    @GetMapping("/{user_id}")
    public List<ExecutorResponse> getAll(@PathVariable("user_id") String userId) throws AWSManagerException {
        List<Executor> executors = executorService.getAll(userId);

        return executors.stream().map(ExecutorConverter::toUi).collect(Collectors.toList());
    }

    @GetMapping("/{executor_name}/{user_id}")
    public ExecutorResponse getExecutor(@PathVariable("executor_name") String executorName, @PathVariable("user_id") String userId) throws AWSManagerException {
        Executor executor = executorService.getByName(executorName, userId);

        return ExecutorConverter.toUi(executor);
    }

    @PutMapping("/{executor_id}")
    public void updateExecutor(@RequestBody CreateExecutorRequest createExecutorRequest, @PathVariable("executor_id") String executorId) throws AWSManagerException {
        Executor executor = executorService.getByExecutorId(executorId);

        List<String> oldCommands = executor.getExecutionUnits().stream().map(ExecutionUnit::getCommand).collect(Collectors.toList());
        List<String> newCommands = createExecutorRequest.getCommands();

        executor.getExecutionUnits().removeIf(executionUnit -> !newCommands.contains(executionUnit.getCommand()));
        executor.getExecutionUnits().addAll(newCommands.stream()
                .filter(newCommand -> !oldCommands.contains(newCommand))
                .map(command -> {
                    ExecutionUnit executionUnit = new ExecutionUnit();
                    executionUnit.setCommand(command);
                    executionUnit.setExecutor(executor);

                    return executionUnit;
                }).collect(Collectors.toList()));

        executorService.saveExecutor(executor);
    }

    @DeleteMapping("/{executor_id}")
    public void deleteExecutor(@PathVariable("executor_id") String executorId) {
        executorService.deleteExecutor(executorId);
    }
}
