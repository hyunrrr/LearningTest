package com.concurrent.concurrent;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostAcceptanceTest {

    @Autowired
    PostRepository postRepository;

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

@Test
void concurrent_persistence() throws Exception {
    int threadNumber = 10;
    Thread[] threads = new Thread[threadNumber];

    for (int i = 0; i<threadNumber; ++i) {
        threads[i] = new Thread(this::persistence);
    }
    for (Thread thread : threads) {
        thread.start();
//        Thread.sleep(500);
    }

    for (Thread thread : threads) {
        thread.join();
    }

    Post post = postRepository.findById(1L).get();
    System.out.println("@@@@viewCount: " + post.getViewCount());
}

void persistence() {
    ExtractableResponse<Response> response = RestAssured
            .given().log().all()
            .when().get("/post/1")
            .then().log().all()
            .extract();

    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
}

@Test
void concurrent_Jpql() throws InterruptedException {
    int threadNumber = 50;
    Thread[] threads = new Thread[threadNumber];

    for (int i = 0; i<threadNumber; ++i) {
        threads[i] = new Thread(this::jpql);
    }

    long start = System.currentTimeMillis();
    for (Thread thread : threads) {
        thread.start();
    }

    for (Thread thread : threads) {
        thread.join();
    }
    System.out.println("@@@Time: " + (System.currentTimeMillis() - start));

    Thread.sleep(50);
    Post post = postRepository.findById(1L).get();
    System.out.println("@@@@viewCount: " + post.getViewCount());
}

void jpql() {
    ExtractableResponse<Response> response = RestAssured
            .given().log().all()
            .when().get("/post/1/jpql")
            .then().log().all()
            .extract();
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
}
}
