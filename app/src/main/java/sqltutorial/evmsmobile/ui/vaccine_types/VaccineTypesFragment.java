package sqltutorial.evmsmobile.ui.vaccine_types;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import sqltutorial.evmsmobile.R;
import sqltutorial.evmsmobile.databinding.FragmentLoginBinding;
import sqltutorial.evmsmobile.databinding.FragmentVaccineTypesBinding;
import sqltutorial.evmsmobile.ui.FragmentWithOptionsMenu;

public class VaccineTypesFragment extends FragmentWithOptionsMenu {

    private VaccineTypesViewModel mViewModel;

    public static VaccineTypesFragment newInstance() {
        return new VaccineTypesFragment();
    }

    private FragmentVaccineTypesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentVaccineTypesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VaccineTypesViewModel.class);
        // TODO: Use the ViewModel

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action there", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}