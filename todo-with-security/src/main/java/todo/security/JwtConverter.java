package todo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtConverter {

    Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public User getUserFromToken( String token ){
        throw new UnsupportedOperationException();
    }

    public String getTokenFromUser( User toConvert ){

        String commaSeparatedRoles = "";

        for( var authority : toConvert.getAuthorities()  ){
            commaSeparatedRoles += authority + ",";
        }

        commaSeparatedRoles = commaSeparatedRoles.substring(0, commaSeparatedRoles.length() - 1);

        return Jwts.builder()
                .setIssuer("todo-app")
                .setSubject( toConvert.getUsername() )
                .claim( "authorities", commaSeparatedRoles )
                .setExpiration( new Date( System.currentTimeMillis() + 15 * 60 * 1000) )
                .signWith( secretKey )
                .compact();
    }

}
