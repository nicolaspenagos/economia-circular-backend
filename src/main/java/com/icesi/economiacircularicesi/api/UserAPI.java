package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.UserDTO;
import com.icesi.economiacircularicesi.dto.UserNoPassDTO;
import org.springframework.http.ResponseEntity;
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
    @DeleteMapping("/{userId}") //TODO What should deleteUser return? is this ok?
    public ResponseEntity<UUID> deleteUser(@PathVariable UUID userId);

}
