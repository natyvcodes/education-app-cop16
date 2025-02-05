package com.grupo1.myapp.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    private String userId;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName, String userId) {
        this.displayName = displayName;
        this.userId = userId;
    }

    String getDisplayName() {
        return displayName;
    }

    public String getUserId() {
        return userId;
    }
}