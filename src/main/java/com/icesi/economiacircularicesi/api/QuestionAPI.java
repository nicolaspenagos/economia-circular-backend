package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("questions")
public interface QuestionAPI {
    @PostMapping
    public QuestionDTO createQuestion(@RequestBody QuestionDTO questionDTO);

    @GetMapping
    public List<QuestionDTO> getQuestions();
}

/*
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

    //TODO PATCH update users terms and conds
    @PatchMapping("/{userId}")
    public UserNoPassDTO updateUser(@PathVariable UUID userId, @RequestBody UserDTO userDTO);

}
 */