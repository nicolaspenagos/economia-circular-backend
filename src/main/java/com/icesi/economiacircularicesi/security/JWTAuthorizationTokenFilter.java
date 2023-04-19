
package com.icesi.economiacircularicesi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomException;

import com.icesi.economiacircularicesi.utils.ErrorExceptionUtils;
import com.icesi.economiacircularicesi.utils.JWTParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Component
@Order(1)
public class JWTAuthorizationTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String USER_ID_CLAIM_NAME = "userId";
    private static final String OPTIONS = "OPTIONS";
    private static final String API_DOCS_PATH = "api-docs";
    private static final String SWAGGER_UI_PATH = "swagger-ui";
    private static final String[] excludedPaths = {"POST /login","POST /users", "OPTIONS /users", "OPTIONS /login", "OPTIONS /questions"};




    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (containsToken(request)) {

                String jwtToken = request.getHeader(AUTHORIZATION_HEADER).replace(TOKEN_PREFIX, StringUtils.EMPTY);
                Claims claims = JWTParser.decodeJWT(jwtToken);
                SecurityContext context = parseClaims(jwtToken, claims);
                isAuthorized(request, context, response);
                SecurityContextHolder.setUserContext(context);
                filterChain.doFilter(request, response);
            } else {
                CustomException userDemoException = new CustomException(HttpStatus.UNAUTHORIZED, new CustomError(ErrorCode.CODE_A01_NOT_AUTHENTICATED, ErrorCode.CODE_A01_NOT_AUTHENTICATED.getMessage()));
                createUnauthorizedFilter( userDemoException, response);
            }
        } catch (JwtException e) {
            CustomException userDemoException = new CustomException(HttpStatus.UNAUTHORIZED, new CustomError(ErrorCode.CODE_A01_NOT_AUTHENTICATED, ErrorCode.CODE_A01_NOT_AUTHENTICATED.getMessage()));
            createUnauthorizedFilter( userDemoException, response);
        }  catch (CustomException e) { // Handle the CustomException thrown by isAuthorized method
            createUnauthorizedFilter(e, response);
        }finally {
            SecurityContextHolder.clearContext();
        }
    }

    private SecurityContext parseClaims(String jwtToken, Claims claims) {
        String userId = claimKey(claims, USER_ID_CLAIM_NAME);

        SecurityContext context = new SecurityContext();
        try {
            context.setUserId(UUID.fromString(userId));
            context.setToken(jwtToken);
        } catch (IllegalArgumentException e) {
            throw new MalformedJwtException("Error parsing jwt");
        }
        return context;
    }

    private String claimKey(Claims claims, String key) {
        String value = (String) claims.get(key);
        return Optional.ofNullable(value).orElseThrow();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String method = request.getMethod();
        String uriPath = request.getRequestURI();

        if(method.equalsIgnoreCase(OPTIONS)){
            return true;
        }

        if(getMainPath(uriPath).equals(SWAGGER_UI_PATH)||getMainPath(uriPath).equals(API_DOCS_PATH))
            return true;

        String methodPlusPath = method + " " + uriPath;

        return Arrays.stream(excludedPaths).anyMatch(path -> path.equalsIgnoreCase(methodPlusPath));
    }

    private boolean containsToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(AUTHORIZATION_HEADER);
        return authenticationHeader != null && authenticationHeader.startsWith(TOKEN_PREFIX);
    }

    @SneakyThrows
    private void createUnauthorizedFilter(CustomException userDemoException, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();

        CustomError userDemoError = userDemoException.getError();

        String message = objectMapper.writeValueAsString(userDemoError);

        response.setStatus(401);
        response.setHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        response.getWriter().write(message);
        response.getWriter().flush();
    }

    private void isAuthorized(HttpServletRequest request, SecurityContext context, HttpServletResponse response){

        System.out.println();
        String[] usersPermissions = new UserRolePermissions().getUserRolePermissionsList(context.getUserId().toString());

        boolean authorizedFlag = searchPermission(usersPermissions, request);

        if(!authorizedFlag){
            ErrorExceptionUtils.throwCustomException(HttpStatus.UNAUTHORIZED, ErrorCode.CODE_A04_UNAUTHORIZED);
        }
    }

    private boolean searchPermission(String[] permissions, HttpServletRequest request){
        String requestToString = request.getMethod() + " " + request.getRequestURI();


        for(String permission:permissions){
            if(permission.endsWith("*") && validateSharedPaths(permission, requestToString)){
              return true;
            }

            if(permission.equals(requestToString))
                return true;
        }

        return false;
    }


    private boolean validateSharedPaths(String permission, String request){

        String[] permissionParts = permission.split("/");
        String[] requestParts = request.split("/");

        if (permissionParts.length < 2 || requestParts.length < 2) {
            return false;
        }

        for (int i = 0; i < permissionParts.length - 1; i++) {
            if (!permissionParts[i].equals(requestParts[i])) {
                return false;
            }
        }

        return true;
    }

    private String getMainPath(String path){
        return path.split("/")[1];
    }

}



