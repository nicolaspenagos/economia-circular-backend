package com.icesi.economiacircularicesi.repository.UserRepository;

import com.icesi.economiacircularicesi.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

}
