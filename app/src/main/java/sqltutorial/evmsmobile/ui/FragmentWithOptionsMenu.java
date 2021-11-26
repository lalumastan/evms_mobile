package sqltutorial.evmsmobile.ui;

import static sqltutorial.evmsmobile.ui.login.LoginFragment.CURRENT_LOGGEDIN_AUTHORIZATION;
import static sqltutorial.evmsmobile.ui.login.LoginFragment.CURRENT_LOGGEDIN_USERNAME;
import static sqltutorial.evmsmobile.ui.login.LoginFragment.CURRENT_LOGGEDIN_USERROLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import sqltutorial.evmsmobile.MainActivity;
import sqltutorial.evmsmobile.R;

public class FragmentWithOptionsMenu extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_delete).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_signout) {
            CURRENT_LOGGEDIN_USERNAME = null;
            CURRENT_LOGGEDIN_USERROLE = null;
            CURRENT_LOGGEDIN_AUTHORIZATION = null;

            MainActivity mainActivity = (MainActivity) this.getActivity();
            Intent intent = new Intent(mainActivity, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            mainActivity.finish();

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

}
