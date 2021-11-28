package sqltutorial.evmsmobile.ui.vaccine_types.maintain;

import androidx.annotation.Nullable;

public class MaintainVaccineTypeFormState {
    @Nullable
    private final Integer vaccineTypeNameError;
    @Nullable
    private final Integer vaccineTypeDescriptionError;

    private boolean isDataValid;

    MaintainVaccineTypeFormState(@Nullable Integer vaccineTypeNameError, @Nullable Integer vaccineTypeDescriptionError) {
        this.vaccineTypeNameError = vaccineTypeNameError;
        this.vaccineTypeDescriptionError = vaccineTypeDescriptionError;
    }

    MaintainVaccineTypeFormState(boolean isDataValid) {
        this(null, null);
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getVaccineTypeNameError() {
        return vaccineTypeNameError;
    }

    @Nullable
    public Integer getVaccineTypeDescriptionError() {
        return vaccineTypeDescriptionError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}
