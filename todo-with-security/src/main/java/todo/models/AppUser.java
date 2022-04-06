package todo.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;
import java.util.stream.Collectors;

public class AppUser extends User {

    Set<String> roles;
    Integer userId;

    public AppUser( Integer userId, String username, String password, Set<String> roles ){
        super( username, password,
                roles.stream().map(
                        r -> new SimpleGrantedAuthority("ROLE_" + r)).collect(Collectors.toList()));

        this.roles = roles;
        this.userId = userId;
    }



    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AppUser appUser = (AppUser) o;

        if (roles != null ? !roles.equals(appUser.roles) : appUser.roles != null) return false;
        return userId != null ? userId.equals(appUser.userId) : appUser.userId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
