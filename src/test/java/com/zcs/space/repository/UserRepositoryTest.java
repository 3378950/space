package com.zcs.space.repository;

import com.zcs.space.entity.User;
import com.zcs.space.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    void findByUsername() {
        User user = new User();
        user.setUsername("zcs");
        user.setNickname("xiaozhang");
        user.setEnabled(true);
        user.setLocked(false);
        user.setPassword("123456");
        user.setGender(Gender.MALE);
        User saveUser = repository.save(user);
        System.out.println(saveUser.toString());
        User res = repository.get("zcs");
    }
}