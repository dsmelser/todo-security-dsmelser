package todo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtConverter {

    Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public User getUserFromToken( String token ){

        try {
            JwtParser parser = Jwts.parserBuilder()
                    .requireIssuer("todo-app")
                    .setSigningKey(secretKey)
                    .build();

            Jws<Claims> claims = parser.parseClaimsJws(token.substring(7));

            String username = claims.getBody().getSubject();

            String authorities = (String) claims.getBody().get("authorities");

            String[] authSplit = authorities.split(",");

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            for (String auth : authSplit) {
                grantedAuthorities.add(new SimpleGrantedAuthority(auth));
            }

            return new User(username, username, grantedAuthorities);
        } catch( JwtException ex ){
            ex.printStackTrace( System.err );
            return null;
        }
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
