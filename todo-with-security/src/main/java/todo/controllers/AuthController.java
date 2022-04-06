package todo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import todo.domain.UserService;
import todo.security.JwtConverter;

import java.util.Map;

@RestController
public class AuthController {

    AuthenticationManager authManager;
    JwtConverter converter;
    UserService service;

    public AuthController(
            AuthenticationManager authManager,
            JwtConverter converter,
            UserService service ){
        this.authManager = authManager;
        this.converter = converter;
        this.service = service;
    }

    public ResponseEntity<String> login(@RequestBody Map<String,String> credentials ){
        throw new UnsupportedOperationException();
    }

}
