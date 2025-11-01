package src.main;

import java.util.List;

public class RequestsRepository implements IRepository<HttpRequest> {
    private DatabaseContext context;

    public RequestsRepository(DatabaseContext context) {
        this.context = context;
    }

    public void add(HttpRequest entity) {
        context.getRequests().add(entity);
    }

    public HttpRequest getById(int id) {
        return context.getRequests().get(id);
    }

    public List<HttpRequest> getAll() {
        return context.getRequests();
    }
}

