package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TmpTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("'일' 엔티티가 빈 컬렉션을 가지고 save 될 때 학습테스트")
    @Test
    void oneToManySaveTest2() {
        Team team = new Team();

        System.out.println("before save team");
        teamRepository.save(team);
        System.out.println("after save team");
    }

    @DisplayName("일대다 단방향 '일' 엔티티가 '다' 엔티티를 컬렉션에 가지고 save")
    @Test
    void oneToManySaveTest() {
        Team team = new Team();
        Member member = new Member("name");
        team.addMember(member);

        System.out.println("before save member");
        memberRepository.save(member);
        System.out.println("after save member");
        teamRepository.save(team);
        System.out.println("after save team");
    }

    @DisplayName("이미 save 된 '일' 엔티티에 '다' 엔티티를 추가")
    @Test
    void oneToManySaveTest3() {
        Team team = new Team();
        teamRepository.save(team);
        System.out.println("team already saved");

        Member member = new Member("name");
        team.addMember(member);
        System.out.println("before save member");
        memberRepository.save(member);
        System.out.println("after save member");
    }
}
