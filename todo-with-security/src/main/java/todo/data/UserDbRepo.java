package todo.data;

import org.springframework.stereotype.Repository;
import todo.models.AppUser;

@Repository
public class UserDbRepo implements UserRepo {
    @Override
    public AppUser findByUsername(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public AppUser add(AppUser toAdd) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Integer userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void edit(AppUser updated) {
        throw new UnsupportedOperationException();
    }
}
