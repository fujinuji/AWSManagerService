package ro.fujinuji.awsmanager.service;

import ro.fujinuji.awsmanager.model.Executor;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;

import java.util.List;

public interface ExecutorService {

    void saveExecutor(Executor executor);

    void deleteExecutor(String executorId);

    List<Executor> getAll(String userId) throws AWSManagerException;

    Executor getByName(String executorName, String userId) throws AWSManagerException;

    Executor getByExecutorId(String executorId) throws AWSManagerException;
}
