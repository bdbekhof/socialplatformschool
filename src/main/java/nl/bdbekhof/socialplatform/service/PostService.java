package nl.bdbekhof.socialplatform.service;

import nl.bdbekhof.socialplatform.model.Post;
import nl.bdbekhof.socialplatform.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repo;
    public PostService(PostRepository repo) { this.repo = repo; }
    public Post create(Post p) { return repo.save(p); }
    public List<Post> search(String term) { return repo.findByContentContainingIgnoreCase(term); }
}
