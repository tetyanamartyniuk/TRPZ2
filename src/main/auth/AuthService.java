package src.main.auth;


import src.main.serverConfigs.ConfigHandler;

public class AuthService {

    ConfigHandler configHandler = new ConfigHandler();
    private final String username = configHandler.getAdminUsername();
    private final String password = configHandler.getAdminPassword();

    public boolean authenticate(String u, String p) {
        return username.equals(u) && password.equals(p);
    }


}



