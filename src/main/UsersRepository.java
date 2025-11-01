package src.main;

import java.util.List;

public class UsersRepository implements IRepository<User> {
    private DatabaseContext context;

    public UsersRepository(DatabaseContext context) {
        this.context = context;
    }

    @Override
    public void add(User entity) {
        context.getUsers().add(entity);
    }

    @Override
    public User getById(int id) {
        return context.getUsers().get(id);
    }

    @Override
    public List<User> getAll() {
        return context.getUsers();
    }
}