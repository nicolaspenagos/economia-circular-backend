package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.constant.UserErrorCode;
import com.icesi.economiacircularicesi.model.TermsAndConditions;
import com.icesi.economiacircularicesi.model.User;
import com.icesi.economiacircularicesi.repository.TermsAndConditionsRepository;
import com.icesi.economiacircularicesi.repository.UserRepository;
import com.icesi.economiacircularicesi.service.UserService;
import com.icesi.economiacircularicesi.utils.UserExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        validateUniqueEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    private void validateUniqueEmail(String email){
        for(User currentUser: getUsers()){
            if(currentUser.getEmail().equals(email))
                UserExceptionUtils.throwUserException(HttpStatus.BAD_REQUEST, UserErrorCode.CODE_04_DUPLICATED_EMAIL);
        }
    }

}
