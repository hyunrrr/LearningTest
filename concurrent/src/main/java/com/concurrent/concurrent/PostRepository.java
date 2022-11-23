package com.concurrent.concurrent;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long postId);

@Async
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Modifying(clearAutomatically = true)
@Query("UPDATE Post p SET p.viewCount = p.viewCount + 1 WHERE p.id = :postId")
void increaseViewCount(Long postId);
}
