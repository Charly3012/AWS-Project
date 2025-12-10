package xyz.larchy.sicei.Services;

public interface ISessionService {
    String login(int id, String password);
    boolean verifySession(String sessionString);
    boolean  logoutSession(String sessionString);
}
