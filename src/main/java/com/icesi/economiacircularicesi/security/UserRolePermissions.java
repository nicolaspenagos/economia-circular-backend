package com.icesi.economiacircularicesi.security;

import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
public class UserRolePermissions {
    private String[] userRolePermissionsList = {"GET /users/{userId}", "DELETE /users/{userId}","PATCH /users/{userId}"};

    public String[] getUserRolePermissionsList(String uuid){
        return Arrays.stream(userRolePermissionsList).map(permission->permission.replace("{userId}", uuid)).toArray(String[]::new);
    }

}
