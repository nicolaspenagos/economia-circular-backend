package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.UserDTO;
import com.icesi.economiacircularicesi.dto.UserNoPassDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("users")
public interface UserAPI {

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO);
    @GetMapping
    public List<UserNoPassDTO> getUsers();

    @GetMapping("/{userId}")
    UserNoPassDTO getUser(@PathVariable UUID userId);

}
