package com.icesi.economiacircularicesi.test.security;

import com.icesi.economiacircularicesi.security.UserRolePermissions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRolePermissionsTest {

    private UserRolePermissions userRolePermissions;

    @BeforeEach
    private void init() {
        userRolePermissions = new UserRolePermissions();

    }

    @Test
    void getUserPermissionsListTest() {

        String[] expectedUserAPIPermissions = {"GET /users/8c0bce38-2e73-4177-b93b-9c091d6ca2a3", "DELETE /users/8c0bce38-2e73-4177-b93b-9c091d6ca2a3", "PATCH /users/8c0bce38-2e73-4177-b93b-9c091d6ca2a3"};

        String[] actualUserAPIPermissions = userRolePermissions.getUserRolePermissionsList("8c0bce38-2e73-4177-b93b-9c091d6ca2a3");

        for (int i = 0; i < expectedUserAPIPermissions.length; i++) {
            assertEquals(expectedUserAPIPermissions[i], actualUserAPIPermissions[i]);
        }

    }

}


