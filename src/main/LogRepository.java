package src.main;

import java.util.List;

public class LogRepository implements IRepository<Log> {
    private DatabaseContext context;

    public LogRepository(DatabaseContext context) {
        this.context = context;
    }

    public void add(Log entity) {
        context.getLogs().add(entity);
    }

    public Log getById(int id) {
        return context.getLogs().get(id);
    }

    public List<Log> getAll() {
        return context.getLogs();
    }
}