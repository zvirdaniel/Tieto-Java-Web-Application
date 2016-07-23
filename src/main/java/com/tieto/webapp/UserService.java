package com.tieto.webapp;

/**
 * Created by Daniel Zvir on 21.07.2016.
 */
class UserService {
    boolean isValidLogin(String username, String password) {
        return "admin".equals(username) && "admin".equals(password);
    }
}
