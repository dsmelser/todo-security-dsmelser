package todo.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import todo.models.Todo;

import java.util.List;

@Repository
public class TodoDbRepo implements TodoRepo {
    @Autowired
    JdbcTemplate template;

    @Override
    public List<Todo> findAllPublic() {
        String sql = "SELECT * FROM todos where isPublic = 1;";

        return template.query(sql, new TodoMapper());
    }

    @Override
    public List<Todo> findByUserId(Integer userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Todo findById(Integer todoId) {
        return template.query("select * from todos where todoId = ?", new TodoMapper(), todoId).stream().findAny().orElse(null);
    }

    @Override
    public Todo add(Todo toAdd) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Integer todoId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void edit(Todo updated) {
        throw new UnsupportedOperationException();
    }
}
