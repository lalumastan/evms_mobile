package sqltutorial.evmsmobile.data.api;

import android.os.AsyncTask;

import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import sqltutorial.evmsmobile.data.model.VaccineType;
import sqltutorial.evmsmobile.ui.login.LoginViewModel;

import sqltutorial.evmsmobile.ui.vaccine_types.list.VaccineTypeListViewModel;

// Data will be passed as a string
public class AsyncDataConnectTask extends AsyncTask<RestApiCall, Integer, String> {

    ViewModel viewModel;

    public AsyncDataConnectTask(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected String doInBackground(RestApiCall... restApiCalls) {
        restApiCalls[0].sendRequest();

        return restApiCalls[0].response;
    }

    @Override
    protected void onPostExecute(String s) {

        if (viewModel instanceof LoginViewModel) {
            LoginViewModel loginViewModel = (LoginViewModel) viewModel;
            loginViewModel.setResult(s);
        }
        else if (viewModel instanceof VaccineTypeListViewModel) {
            System.out.println(s);
            VaccineTypeListViewModel displayVaccineTypeViewModel = (VaccineTypeListViewModel) viewModel;
            ArrayList<VaccineType> vaccineTypeList = new ArrayList<VaccineType>();
            try {
                JSONArray ja = new JSONArray(s);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    VaccineType note = new VaccineType(jo.getString("name"), jo.getString("description"));
                    vaccineTypeList.add(note);
                }
                System.out.println(vaccineTypeList);

            } catch (Exception e) {
                e.printStackTrace();
            }
            displayVaccineTypeViewModel.setVaccineTypeList(vaccineTypeList);
        }
    }
}
