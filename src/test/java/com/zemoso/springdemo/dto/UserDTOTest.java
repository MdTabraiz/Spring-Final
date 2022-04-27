package com.zemoso.springdemo.dto;

import com.zemoso.springdemo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class UserDTOTest {

    @Test
    void testToEntity(){

        UserDTO userDTO = new UserDTO();
        userDTO.setUserDtoUsername("Test User");
        userDTO.setUserDtoPassword("Test Password");
        userDTO.setUserDtoEnabled(1);
        userDTO.setUserDtoRole("Test_Role");

        User user = userDTO.toEntity();

        Assertions.assertEquals(user.getUsername(),userDTO.getUserDtoUsername());
        Assertions.assertEquals(user.getPassword(),userDTO.getUserDtoPassword());
        Assertions.assertEquals(user.getRole(),userDTO.getUserDtoRole());
        Assertions.assertEquals(user.getEnabled(),userDTO.getUserDtoEnabled());



    }
}
