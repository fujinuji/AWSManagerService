package ro.fujinuji.awsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.fujinuji.awsmanager.model.SSHKey;

import java.util.List;
import java.util.Optional;

@Repository
public interface SSHKeyRepository extends JpaRepository<SSHKey, String> {

    @Query("FROM ssh_key ssk WHERE ssk.instanceName=?1 AND ssk.user.amazonUserId=?2")
    Optional<SSHKey> getByInstanceNameAndUserId(String instanceName, String userId);

    @Query("FROM ssh_key ssk WHERE ssk.user.amazonUserId=?1")
    List<SSHKey> getByUserId(String userId);

    @Query("SELECT ssk.instanceName FROM ssh_key ssk WHERE ssk.user.amazonUserId=?1")
    List<String> getInstancesByUserId(String userId);
}
