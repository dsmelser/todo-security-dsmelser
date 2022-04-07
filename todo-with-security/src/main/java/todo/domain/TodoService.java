package todo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.data.TodoRepo;
import todo.models.Todo;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoRepo repo;

    public List<Todo> getPublicTodos() {
        return repo.findAllPublic();
    }
}
