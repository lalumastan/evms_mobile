package sqltutorial.evmsmobile.ui.home;

import static sqltutorial.evmsmobile.ui.login.LoginFragment.CURRENT_LOGGEDIN_USERNAME;
import static sqltutorial.evmsmobile.ui.login.LoginFragment.CURRENT_LOGGEDIN_USERROLE;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    
    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome " + CURRENT_LOGGEDIN_USERNAME + " (" + CURRENT_LOGGEDIN_USERROLE + ")");
    }

    public LiveData<String> getText() {
        return mText;
    }
}