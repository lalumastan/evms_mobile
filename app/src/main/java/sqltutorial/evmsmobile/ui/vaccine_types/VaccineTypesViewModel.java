package sqltutorial.evmsmobile.ui.vaccine_types;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import sqltutorial.evmsmobile.data.api.AsyncDataConnectTask;
import sqltutorial.evmsmobile.data.api.RestApiCall;
import sqltutorial.evmsmobile.data.model.VaccineType;

public class VaccineTypesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<VaccineType>> mVaccineTypesList;

    public VaccineTypesViewModel() {
        mVaccineTypesList = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<VaccineType>> getVaccineTypeList() {
        return mVaccineTypesList;
    }

    public void setVaccineTypeList(ArrayList<VaccineType> vaccineTypeList) {
        mVaccineTypesList.setValue(vaccineTypeList);
    }

    public void fetchAllVaccineTypeLists() {
        RestApiCall restApiCall = new RestApiCall("vaccine_types", "GET");
        AsyncDataConnectTask task = (AsyncDataConnectTask) new AsyncDataConnectTask(this).execute(restApiCall);
    }
}