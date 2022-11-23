package com.concurrent.concurrent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

@Transactional
public Post findPost(Long id) {
    Post post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    post.increaseViewCount();
    return post;
}

@Transactional
public Post findPost_JPQL(Long id) throws InterruptedException {
    postRepository.increaseViewCount(id);
    Thread.sleep(500);
    Post post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    return post;
}
}
