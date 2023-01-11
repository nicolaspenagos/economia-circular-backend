package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.model.TermsAndConditions;
import com.icesi.economiacircularicesi.model.User;
import com.icesi.economiacircularicesi.repository.TermsAndConditionsRepository;
import com.icesi.economiacircularicesi.repository.UserRepository;
import com.icesi.economiacircularicesi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    public final TermsAndConditionsRepository termsAndConditionsRepository;

    @Override
    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        saveTermsAndConditions(savedUser.getUserId(), user.getTermsAndConditionsHistory());
        return savedUser;
    }
    @Override
    public List<User> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    private void saveTermsAndConditions(UUID userId, List<TermsAndConditions> termsAndConditionsList){
        for(TermsAndConditions currentTC:termsAndConditionsList){
            User userRef = new User();
            userRef.setUserId(userId);
            currentTC.setUser(userRef);
            termsAndConditionsRepository.save(currentTC);
        }
    }

}
