package nl.bdbekhof.socialplatform.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import nl.bdbekhof.socialplatform.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
