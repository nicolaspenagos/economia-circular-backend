
package com.icesi.economiacircularicesi.security;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;

@NoArgsConstructor
public class UserRolePermissions {
    private String[] userRolePermissionsList = {"GET /users/{userId}", "DELETE /users/{userId}","PATCH /users/{userId}", "GET /activities","GET /activities/*", "GET /principles","GET /principles/*","GET /questions", "GET /questions/*", "GET /report/{userId}/*","POST /response", "PATCH /response/*", "GET /response/users/{userId}", "GET /response/users/active/{userId}"};

    public String[] getUserRolePermissionsList(String uuid){
        return Arrays.stream(userRolePermissionsList).map(permission->permission.replace("{userId}", uuid)).toArray(String[]::new);
    }

}