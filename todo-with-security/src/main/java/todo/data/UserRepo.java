package todo.data;

import todo.models.AppUser;

public interface UserRepo {

    AppUser findByUsername(String username );

    AppUser add( AppUser toAdd );

    boolean remove( Integer userId );

    void edit( AppUser updated );

}
