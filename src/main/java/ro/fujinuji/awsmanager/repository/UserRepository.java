package ro.fujinuji.awsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fujinuji.awsmanager.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> getUserByUserEmail(String userEmail);

    Optional<User> getUserByAmazonUserId(String amazonUserId);
}
