package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.LoginDTO.LoginDTO;
import com.icesi.economiacircularicesi.dto.LoginDTO.TokenDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
public interface LoginAPI{
    @PostMapping
    TokenDTO login(@RequestBody LoginDTO loginDTO);

}
