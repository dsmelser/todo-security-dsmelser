package todo.data;

import org.springframework.jdbc.core.RowMapper;
import todo.models.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TodoMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo toReturn = new Todo();

        toReturn.setTodoId( rs.getInt("todoId") );
        toReturn.setText( rs.getString("todoText"));
        toReturn.setUserId( rs.getInt("authorId") );
        toReturn.setPublic( rs.getBoolean("isPublic"));
        toReturn.setCreateDate( LocalDate.parse( rs.getString("createDate") ) );


        return toReturn;
    }
}
