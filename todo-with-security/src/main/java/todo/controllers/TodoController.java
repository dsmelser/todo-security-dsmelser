package todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.domain.InvalidUserException;
import todo.domain.TodoService;
import todo.models.Todo;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
@CrossOrigin( origins = {"http://localhost:3000"})
public class TodoController {

    @Autowired
    TodoService service;

    @GetMapping("/public")
    public ResponseEntity getPublicTodos(){
        return ResponseEntity.ok( service.getPublicTodos() );
    }

    @GetMapping("/{todoId}")
    public ResponseEntity getById( @PathVariable Integer todoId, Principal user ) throws InvalidUserException {
        return ResponseEntity.ok( service.getById( todoId, user ));

    }

    @PutMapping
    public ResponseEntity edit( @RequestBody Todo edited, Principal user ) throws InvalidUserException {
        service.edit( edited, user );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity deleteById(@PathVariable Integer todoId, Principal user) throws InvalidUserException {
        service.deleteById( todoId, user );
        return ResponseEntity.ok().build();
    }

}
