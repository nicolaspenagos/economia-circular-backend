package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomException;
import com.icesi.economiacircularicesi.mapper.UserMapper;
import com.icesi.economiacircularicesi.model.User.TermsAndConditions;
import com.icesi.economiacircularicesi.model.User.User;
import com.icesi.economiacircularicesi.repository.UserRepository.TermsAndConditionsRepository;
import com.icesi.economiacircularicesi.repository.UserRepository.UserRepository;
import com.icesi.economiacircularicesi.service.UserService;
import com.icesi.economiacircularicesi.utils.ErrorExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    public final TermsAndConditionsRepository termsAndConditionsRepository;
    public final UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {

        validateUniqueEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword())); //Encrypt the password using Bcrypt encrypted hash

        User savedUser = userRepository.save(user);
        saveTermsAndConditions(savedUser.getUserId(), user.getTermsAndConditionsHistory());

        return savedUser;

    }

    private void saveTermsAndConditions(UUID userId, List<TermsAndConditions> termsAndConditionsList) {

        for (TermsAndConditions currentTC : termsAndConditionsList) {
            User userRef = new User();
            userRef.setUserId(userId);
            currentTC.setUser(userRef);

            termsAndConditionsRepository.save(currentTC);
        }

    }


    @Override
    public List<User> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public User getUser(UUID userId) {
        //TODO if the user does not exist, should getUser return null or an exception?
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public ResponseEntity<UUID> deleteUser(UUID userId) {

        Optional.ofNullable(getUser(userId))
                .ifPresentOrElse(
                        (user) -> deleteUserAndRelations(user),
                        () -> {
                            ErrorExceptionUtils.throwCustomException(HttpStatus.BAD_REQUEST, ErrorCode.CODE_U05_USER_NOT_FOUND);
                        }
                );

        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @Override
    public User updateUser(UUID userId,User userUpdate) {

        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException(HttpStatus.BAD_REQUEST, new CustomError(ErrorCode.CODE_U05_USER_NOT_FOUND, ErrorCode.CODE_U05_USER_NOT_FOUND.getMessage())));

        userMapper.updateUserFromUser(userUpdate, user);

        saveTermsAndConditions(user.getUserId(), user.getTermsAndConditionsHistory());

        return userRepository.save(user);//TODO Check this
    }

    private void deleteUserAndRelations(User user) {

        for (TermsAndConditions currentTC : user.getTermsAndConditionsHistory()) {
            termsAndConditionsRepository.delete(currentTC);
        }
        userRepository.delete(user);

    }


    private void validateUniqueEmail(String email) {

        for (User currentUser : getUsers()) {
            if (currentUser.getEmail().equals(email))
                ErrorExceptionUtils.throwCustomException(HttpStatus.BAD_REQUEST, ErrorCode.CODE_U04_DUPLICATED_EMAIL);
        }

    }

}
