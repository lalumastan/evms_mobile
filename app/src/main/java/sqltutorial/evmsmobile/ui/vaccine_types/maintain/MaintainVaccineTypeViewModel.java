package sqltutorial.evmsmobile.ui.vaccine_types.maintain;

import static sqltutorial.evmsmobile.ui.vaccine_types.list.VaccineTypeListFragment.CURRENT_VACCINE_TYPE;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import sqltutorial.evmsmobile.R;
import sqltutorial.evmsmobile.data.api.AsyncDataConnectTask;
import sqltutorial.evmsmobile.data.api.RestApiCall;
import sqltutorial.evmsmobile.data.model.VaccineType;

public class MaintainVaccineTypeViewModel extends ViewModel {
    
    private final MutableLiveData<MaintainVaccineTypeFormState> maintainVaccineTypeFormState = new MutableLiveData<>();
    private final MutableLiveData<VaccineType> addVaccineTypeResult = new MutableLiveData<>();

    LiveData<MaintainVaccineTypeFormState> getMaintainVaccineTypeFormState() {
        return maintainVaccineTypeFormState;
    }

    public MutableLiveData<VaccineType> getAddVaccineTypeResult() {
        return addVaccineTypeResult;
    }

    public void addVaccineTypeDataChanged(String name, String description) {
        if (name == null || "".equals(name)) {
            maintainVaccineTypeFormState.setValue(new MaintainVaccineTypeFormState(R.string.invalid_vaccine_type_name, null));
        } else if (description == null || "".equals(description)) {
            maintainVaccineTypeFormState.setValue(new MaintainVaccineTypeFormState(null, R.string.invalid_vaccine_type_description));
        } else {
            maintainVaccineTypeFormState.setValue(new MaintainVaccineTypeFormState(true));
        }
    }

    public void addVaccineType(String name, String description) {
        VaccineType vaccineType = new VaccineType(name, description);
        Gson gson = new Gson();
        String json = gson.toJson(vaccineType);
        RestApiCall restApiCall = new RestApiCall("vaccine_types/add", "POST", json);
        new AsyncDataConnectTask(this).execute(restApiCall);
    }

    public void editVaccineType(String name, String description) {
        CURRENT_VACCINE_TYPE.setName(name);
        CURRENT_VACCINE_TYPE.setDescription(description);
        Gson gson = new Gson();
        String json = gson.toJson(CURRENT_VACCINE_TYPE);
        RestApiCall restApiCall = new RestApiCall("vaccine_types/edit", "PUT", json);
        new AsyncDataConnectTask(this).execute(restApiCall);
    }

    public void deleteVaccineType(String name) {
        RestApiCall restApiCall = new RestApiCall("vaccine_types/delete/" + name, "DELETE");
        new AsyncDataConnectTask(this).execute(restApiCall);
    }
}