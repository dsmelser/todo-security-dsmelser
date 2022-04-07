package todo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.domain.UserService;
import todo.security.JwtConverter;

import java.util.Map;

@RestController
@RequestMapping("/api/security")
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String,String> credentials ){

//        * [ ] create UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( credentials.get("username"), credentials.get("password") );
//        * [ ] in a try/catch( AuthenticationException ex) block...
//          * [ ] Authentication authResult = authManager.authenticate( token );
//          * [ ] if( authResult.isAuthenticated() ){
//            * [ ] String jwt = converter.getTokenFromUser( (User)authResult.getPrincipal());
//            * [ ] return ResponseEnttiy.ok( jwt );
//          * [ ] }
//          * [ ] catch( AuthenticationException ex ){
//            * [ ] ex.printStackTrace( System.err ); }
//          * [ ] return new ResponseEntity( HttpStatus.FORBIDDEN );

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken( credentials.get("username"), credentials.get("password") );

        try {
            Authentication authResult = authManager.authenticate(token);

            if( authResult.isAuthenticated() ){
                User toConvert = (User)authResult.getPrincipal();

                String jwt = converter.getTokenFromUser( toConvert );

                return ResponseEntity.ok( jwt );
            }
        } catch ( AuthenticationException ex ){
            ex.printStackTrace(System.err);
        }


        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
