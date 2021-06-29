package ro.fujinuji.awsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.fujinuji.awsmanager.model.Executor;

import java.util.List;
import java.util.Optional;

public interface ExecutorRepository extends JpaRepository<Executor, String> {

    @Query("FROM executor ex WHERE ex.name=?1 AND ex.user.amazonUserId=?2")
    Optional<Executor> getExecutorByName(String name, String userId);

    @Query("FROM executor ex WHERE ex.user.amazonUserId=?1")
    List<Executor> getAllByUserId(String userId);

    Optional<Executor> getByExecutorId(String executorId);
}
