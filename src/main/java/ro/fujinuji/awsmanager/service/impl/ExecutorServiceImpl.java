package ro.fujinuji.awsmanager.service.impl;

import org.springframework.stereotype.Service;
import ro.fujinuji.awsmanager.model.Executor;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.model.exception.ConfigurationNotFoundException;
import ro.fujinuji.awsmanager.repository.ExecutorRepository;
import ro.fujinuji.awsmanager.service.ExecutorService;

import java.util.List;

@Service
public class ExecutorServiceImpl implements ExecutorService {

    private final ExecutorRepository executorRepository;

    public ExecutorServiceImpl(ExecutorRepository executorRepository) {
        this.executorRepository = executorRepository;
    }

    @Override
    public void saveExecutor(Executor executor) {
        executorRepository.save(executor);
    }

    @Override
    public void deleteExecutor(String executorId) {
        executorRepository.deleteById(executorId);
    }

    @Override
    public List<Executor> getAll(String userId) throws AWSManagerException {
        return executorRepository.getAllByUserId(userId);
    }

    @Override
    public Executor getByName(String executorName, String userId) throws AWSManagerException {
        return executorRepository.getExecutorByName(executorName, userId).orElseThrow(ConfigurationNotFoundException::new);
    }

    @Override
    public Executor getByExecutorId(String executorId) throws AWSManagerException {
        return executorRepository.getByExecutorId(executorId).orElseThrow(ConfigurationNotFoundException::new);
    }
}
