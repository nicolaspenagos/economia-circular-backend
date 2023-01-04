package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("users")
public interface UserAPI {
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO);
}
