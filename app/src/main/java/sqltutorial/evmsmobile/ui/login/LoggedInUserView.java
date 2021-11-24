package sqltutorial.evmsmobile.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private final String email;
    private final String displayName;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String email, String displayName) {
        this.email = email;
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }
}