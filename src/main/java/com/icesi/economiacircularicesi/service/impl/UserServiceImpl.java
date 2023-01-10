package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.model.User;
import com.icesi.economiacircularicesi.repository.TermsAndConditionsRepository;
import com.icesi.economiacircularicesi.repository.UserRepository;
import com.icesi.economiacircularicesi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    public final TermsAndConditionsRepository termsAndConditionsRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }


}
