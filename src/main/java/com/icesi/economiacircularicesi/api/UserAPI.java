package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("users")
public interface UserAPI {
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO);
    @GetMapping
    public List<UserDTO> getUsers();


}
