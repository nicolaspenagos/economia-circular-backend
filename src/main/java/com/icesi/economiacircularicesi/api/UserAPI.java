package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.UserDTO.UserDTO;
import com.icesi.economiacircularicesi.dto.UserDTO.UserNoPassDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("users")
public interface UserAPI {

    @PostMapping
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @GetMapping
    List<UserNoPassDTO> getUsers();

    @GetMapping("/{userId}")
    UserNoPassDTO getUser(@PathVariable UUID userId);

    @DeleteMapping("/{userId}") //TODO What should deleteUser return? is this ok?
    ResponseEntity<UUID> deleteUser(@PathVariable UUID userId);

    //TODO PATCH update users terms and conds
    @PatchMapping("/{userId}")
    UserNoPassDTO updateUser(@PathVariable UUID userId, @RequestBody UserDTO userDTO);

}
