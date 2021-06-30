package ro.fujinuji.awsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fujinuji.awsmanager.model.Token;

public interface TokenRepository extends JpaRepository<Token, String> {
}
