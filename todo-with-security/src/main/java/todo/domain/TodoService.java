package todo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.data.TodoRepo;
import todo.data.UserRepo;
import todo.models.AppUser;
import todo.models.Todo;

import java.security.Principal;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoRepo tRepo;

    @Autowired
    UserRepo uRepo;

    public List<Todo> getPublicTodos() {
        return tRepo.findAllPublic();
    }

    public void deleteById(Integer todoId, Principal user) throws InvalidUserException {

        Todo toDelete = tRepo.findById(todoId);

        AppUser requester = uRepo.findByUsername(user.getName());

        //TODO: make sure we didn't get back any nulls, check that todoId isn't null
        //          check that user isn't null etc etc

        if( requester.getRoles().contains("ADMIN")
                ||
            requester.getUserId().intValue() == toDelete.getUserId().intValue() ){
            //this is an admin OR this is the same person who wrote the todo
            tRepo.remove(todoId);
        } else {
            throw new InvalidUserException("Only admins and the author of the todo may delete it.");
        }




    }
}
