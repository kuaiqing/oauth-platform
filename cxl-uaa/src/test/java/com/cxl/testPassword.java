package com.cxl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author cxl
 * @description
 * @date 2020/09/09 0009
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ContextConfiguration(classes = testPassword.class)
public class testPassword {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Test
    public void getPassword (){
        String encode = passwordEncoder.encode("123");
        log.info(encode);

    }

}
