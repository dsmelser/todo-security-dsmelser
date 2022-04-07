package todo.data;

import org.springframework.jdbc.core.RowMapper;
import todo.models.AppUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class UserMapper implements RowMapper<AppUser> {

    Set<String> roles;

    public UserMapper( Set<String> roles ){
        this.roles = roles;
    }

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Integer userId, String username, String password, Set<String> roles
        AppUser toReturn = new AppUser(
                rs.getInt("userId"),
                rs.getString("username"),
                rs.getString("password"),
                roles);

        return toReturn;
    }
}
