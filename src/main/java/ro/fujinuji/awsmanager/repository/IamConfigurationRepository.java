package ro.fujinuji.awsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.fujinuji.awsmanager.model.UserIamConfiguration;

import java.util.Optional;

@Repository
public interface IamConfigurationRepository extends JpaRepository<UserIamConfiguration, String> {

    @Query("FROM iam_configuration ic WHERE ic.user.amazonUserId=?1")
    Optional<UserIamConfiguration> getByAmazonUserId(String amazonUserIq);

    @Query("FROM iam_configuration ic WHERE ic.user.internalUserId=?1")
    Optional<UserIamConfiguration> getByUserId(String userId);
}
