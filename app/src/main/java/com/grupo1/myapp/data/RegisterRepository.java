package com.grupo1.myapp.data;

import com.grupo1.myapp.data.model.RegisteredUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class RegisterRepository {

    private static volatile RegisterRepository instance;

    private RegisterDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private RegisteredUser user = null;

    // private constructor : singleton access
    private RegisterRepository(RegisterDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static RegisterRepository getInstance(RegisterDataSource dataSource) {
        if (instance == null) {
            instance = new RegisterRepository(dataSource);
        }
        return instance;
    }

    public boolean isUserRegistered() {
        return user != null;
    }

    public void clearRegisteredUser() {
        user = null;
    }

    private void setRegisteredUser(RegisteredUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<Boolean> register(String username, String email, String password) {
        // Handle registration
        Result<Boolean> result = dataSource.register(username, email, password);
        if (result instanceof Result.Success) {
            // If registration is successful, set the registered user information
            setRegisteredUser(new RegisteredUser(username, email));
        }
        return result;
    }
}
