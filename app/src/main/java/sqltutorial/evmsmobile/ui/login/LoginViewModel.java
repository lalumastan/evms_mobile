package sqltutorial.evmsmobile.ui.login;

import android.util.Base64;
import android.util.Patterns;

import static sqltutorial.evmsmobile.ui.login.LoginFragment.CURRENT_LOGGEDIN_USERNAME;
import static sqltutorial.evmsmobile.ui.login.LoginFragment.CURRENT_LOGGEDIN_AUTHORIZATION;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import sqltutorial.evmsmobile.R;
import sqltutorial.evmsmobile.data.api.AsyncDataConnectTask;
import sqltutorial.evmsmobile.data.api.RestApiCall;
import sqltutorial.evmsmobile.data.model.LoggedInUser;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        CURRENT_LOGGEDIN_USERNAME = username;
        CURRENT_LOGGEDIN_AUTHORIZATION = Base64.encodeToString((username + ":" + password).getBytes(), Base64.NO_WRAP);
        RestApiCall restApiCall = new RestApiCall("login", "POST");
        AsyncDataConnectTask task = (AsyncDataConnectTask) new AsyncDataConnectTask(this).execute(restApiCall);
    }

    public void setResult(String role) {
        if (role != null && !role.isEmpty()) {
            loginResult.setValue(new LoginResult(new LoggedInUser(CURRENT_LOGGEDIN_USERNAME, CURRENT_LOGGEDIN_AUTHORIZATION, role)));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}