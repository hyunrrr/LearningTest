package com.concurrent.concurrent;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> findPost(@PathVariable Long postId) {
        Post post = postService.findPost(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/post/{postId}/jpql")
    public ResponseEntity<Post> findPostJpql(@PathVariable Long postId) throws InterruptedException {
        Post post = postService.findPost_JPQL(postId);
        return ResponseEntity.ok(post);
    }
}
