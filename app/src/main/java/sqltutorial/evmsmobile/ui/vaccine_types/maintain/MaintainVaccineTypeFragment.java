package sqltutorial.evmsmobile.ui.vaccine_types.maintain;

import static sqltutorial.evmsmobile.MainActivity.hideKeyboard;
import static sqltutorial.evmsmobile.data.model.LoggedInUser.ADMIN;
import static sqltutorial.evmsmobile.ui.login.LoginFragment.CURRENT_LOGIN_USERROLE;
import static sqltutorial.evmsmobile.ui.vaccine_types.list.VaccineTypeListFragment.CURRENT_VACCINE_TYPE;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import sqltutorial.evmsmobile.R;
import sqltutorial.evmsmobile.data.model.VaccineType;
import sqltutorial.evmsmobile.ui.FragmentWithOptionsMenu;

public class MaintainVaccineTypeFragment extends FragmentWithOptionsMenu {

    private MaintainVaccineTypeViewModel mViewModel;

    private MenuItem deleteMenuItem;
    private boolean isEdit;
    private VaccineType vaccineTypeToDelete;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isEdit = CURRENT_VACCINE_TYPE != null;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (ADMIN.equals(CURRENT_LOGIN_USERROLE)) {
            deleteMenuItem = menu.findItem(R.id.action_delete);
            deleteMenuItem.setVisible(isEdit);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maintain_vaccine_type, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            Resources resources = this.getResources();
            builder.setMessage(resources.getString(R.string.delete_confirmation));
            builder.setPositiveButton(resources.getString(R.string.yes), (dialogInterface, i) -> {
                dialogInterface.dismiss();
                mViewModel.deleteVaccineType(vaccineTypeToDelete.getName());
                returnHome();
            });

            builder.setNegativeButton(resources.getString(R.string.no), (dialogInterface, i) -> dialogInterface.dismiss());
            final AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Enables the hamburger menu
        final EditText nameEditText = view.findViewById(R.id.editTextName);
        final EditText descriptionEditText = view.findViewById(R.id.editTextDescription);

        final Button addVaccineTypeButton = view.findViewById(R.id.addVaccineType);
        addVaccineTypeButton.setOnClickListener(v -> {
            hideKeyboard(requireActivity(), v);
            if (isEdit) {
                mViewModel.editVaccineType(nameEditText.getText().toString(), descriptionEditText.getText().toString());
            } else {
                mViewModel.addVaccineType(nameEditText.getText().toString(), descriptionEditText.getText().toString());
            }
            returnHome();
        });

        if (isEdit) {
            this.requireActivity().setTitle(R.string.update);
            ((AppCompatActivity) this.requireActivity()).getSupportActionBar().setTitle(R.string.update);
            nameEditText.setText(CURRENT_VACCINE_TYPE.getName());
            nameEditText.setEnabled(false);
            descriptionEditText.setText(CURRENT_VACCINE_TYPE.getDescription());
            addVaccineTypeButton.setText(R.string.update);
            addVaccineTypeButton.setEnabled(true);
            vaccineTypeToDelete = CURRENT_VACCINE_TYPE;
        }
        mViewModel = new ViewModelProvider(this, new MaintainVaccineTypeViewModelFactory()).get(MaintainVaccineTypeViewModel.class);

        mViewModel.getMaintainVaccineTypeFormState().observe(getViewLifecycleOwner(), addVaccineTypeFormState -> {
            if (addVaccineTypeFormState == null) {
                return;
            }
            addVaccineTypeButton.setEnabled(addVaccineTypeFormState.isDataValid());
            if (addVaccineTypeFormState.getVaccineTypeNameError() != null) {
                nameEditText.setError(getString(addVaccineTypeFormState.getVaccineTypeNameError()));
            }
            if (addVaccineTypeFormState.getVaccineTypeDescriptionError() != null) {
                descriptionEditText.setError(getString(addVaccineTypeFormState.getVaccineTypeDescriptionError()));
            }
        });

        mViewModel.getAddVaccineTypeResult().observe(getViewLifecycleOwner(), vaccineType -> {
            if (vaccineType == null || vaccineType.getName() == null || "".equals(vaccineType.getName())) {
                return;
            }
            String opr = isEdit ? "edited" : "added";
            if (vaccineType.getName() != null) {
                Snackbar.make(view, vaccineType.getName() + " " + opr + " successfully.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                deleteMenuItem.setVisible(true);
                vaccineTypeToDelete = vaccineType;
            } else {
                Snackbar.make(view, "Vaccine Type " + opr + " failed!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.addVaccineTypeDataChanged(nameEditText.getText().toString(), descriptionEditText.getText().toString());
            }
        };
        nameEditText.addTextChangedListener(afterTextChangedListener);
        descriptionEditText.addTextChangedListener(afterTextChangedListener);
    }

    private void returnHome() {
        NavHostFragment.findNavController(MaintainVaccineTypeFragment.this).navigate(R.id.action_nav_maintain_vaccine_type_to_nav_vaccine_type_list);
    }
}