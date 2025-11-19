package nl.bdbekhof.socialplatform.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import nl.bdbekhof.socialplatform.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByContentContainingIgnoreCase(String term);
}
