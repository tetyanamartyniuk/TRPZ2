package src.main.serverConfigs;

public class ConfigHandler {

    private final ConfigReader reader;

    public ConfigHandler() {
        this.reader = new ConfigReader("config.properties");
    }

    public int getPort() {
        return reader.getInt("port");
    }

    public int getNumberOfThreads() {
        return reader.getInt("numberOfThreads");
    }

    public int getQueueLimit() {
        return reader.getInt("queueLimit");
    }

    public String getDatabaseUrl() {
        return reader.getString("databaseUrl");
    }

    public String getDatabaseUser() {
        return reader.getString("databaseUser");
    }

    public String getDatabasePassword() {
        return reader.getString("databasePassword");
    }

//    public int getServerMonitorPort() {
//        return reader.getInt("monitorPort");
//    }
//
//    public String getServerMonitorUrl() {
//        return reader.getString("monitorUrl");
//    }

    public String getAdminUsername(){return reader.getString("adminUsername");}

    public String getAdminPassword(){return reader.getString("adminPassword");}



}
