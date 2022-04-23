package com.zemoso.springdemo.test.dto;

import com.zemoso.springdemo.dto.UserDTO;
import com.zemoso.springdemo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DtoToEntity {

    @Test
    public void testToEntity(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Test_User");
        userDTO.setEnabled(1);
        userDTO.setPassword("Test_Password");
        userDTO.setRole("USER_ROLE");

        User user = userDTO.toEntity();
        Assertions.assertEquals(user.getUsername(),userDTO.getUsername());
        Assertions.assertEquals(user.getEnabled(),userDTO.getEnabled());
        Assertions.assertEquals(user.getPassword(),userDTO.getPassword());
        Assertions.assertEquals(user.getRole(),userDTO.getRole());


    }
}
