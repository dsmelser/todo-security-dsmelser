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

    public Todo getById(Integer todoId, Principal user) throws InvalidUserException {
        Todo toReturn = tRepo.findById(todoId);
        AppUser requester = uRepo.findByUsername(user.getName());
        if(requester.getRoles().contains("ADMIN") ||                                   //if admin
            requester.getUserId().intValue() == toReturn.getUserId().intValue() ||     //or if the actual author
            toReturn.getPublic()                                                       //or the todo is public
        ){
            return toReturn;
        } else {
            throw new InvalidUserException( "Only admins and author of the todo may look up private todos." );
        }


    }

    public void edit(Todo edited, Principal requester) throws InvalidUserException {

        if( edited == null ){ throw new IllegalArgumentException( "edited is null" );}
        if( edited.getTodoId() == null ){ throw new IllegalArgumentException( "todo id is null" );}

        Todo oldData = tRepo.findById( edited.getTodoId() );

        if( oldData == null ){ throw new IllegalArgumentException( "invalid todo id" );}

        if( edited.getText() == null ) { throw new IllegalArgumentException( "text is null" );}
        if( edited.getUserId() == null ) { throw new IllegalArgumentException( "user id is null" );}

        AppUser matchingUser = uRepo.findById( edited.getUserId() );

        if( matchingUser == null ) { throw new IllegalArgumentException("Invalid user id."); }

        if( edited.getPublic() == null ) { throw new IllegalArgumentException( "isPublic is null" );}

        AppUser requestingUser = uRepo.findByUsername( requester.getName() );

        if(requestingUser.getRoles().contains("ADMIN") ||                                   //if admin
                requestingUser.getUserId().intValue() == edited.getUserId().intValue() ) {

            tRepo.edit( edited );
        } else {
            throw new InvalidUserException("Only admins and the author of the todo may edit it.");
        }



    }
}
