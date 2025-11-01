package src.main;

import java.util.List;

public class StatisticsRepository implements IRepository<StatisticsRecord> {
    private DatabaseContext context;

    public StatisticsRepository(DatabaseContext context) {
        this.context = context;
    }

    @Override
    public void add(StatisticsRecord entity) {
        context.getStatistics().add(entity);
    }

    @Override
    public StatisticsRecord getById(int id) {
        return context.getStatistics().get(id);
    }

    @Override
    public List<StatisticsRecord> getAll() {
        return context.getStatistics();
    }
}
