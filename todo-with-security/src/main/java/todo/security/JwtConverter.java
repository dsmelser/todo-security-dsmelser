package todo.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtConverter {

    Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public User getUserFromToken( String token ){
        throw new UnsupportedOperationException();
    }

    public String getTokenFromUser( User toConvert ){
        throw new UnsupportedOperationException();
    }

}
