package todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.domain.TodoService;
import todo.models.Todo;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    TodoService service;

    @GetMapping("/public")
    public ResponseEntity getPublicTodos(){
        List<Todo> allPublic = service.getPublicTodos();

        return ResponseEntity.ok( allPublic );
    }

}
