package nl.bdbekhof.socialplatform.controller;

import nl.bdbekhof.socialplatform.model.Post;
import nl.bdbekhof.socialplatform.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) { this.postService = postService; }

    @GetMapping("/")
    public String feed(Model model) {
        model.addAttribute("posts", postService.search("")); // all
        return "feed";
    }

    @PostMapping("/posts")
    public String createPost(@RequestParam String content) {
        Post p = new Post();
        p.setContent(content);
        postService.create(p);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam String q, Model model) {
        List<Post> results = postService.search(q);
        model.addAttribute("posts", results);
        return "feed";
    }
}
