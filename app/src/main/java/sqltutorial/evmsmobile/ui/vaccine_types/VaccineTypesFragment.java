package sqltutorial.evmsmobile.ui.vaccine_types;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import sqltutorial.evmsmobile.R;
import sqltutorial.evmsmobile.data.model.VaccineType;
import sqltutorial.evmsmobile.databinding.FragmentVaccineTypesBinding;
import sqltutorial.evmsmobile.ui.FragmentWithOptionsMenu;

public class VaccineTypesFragment extends FragmentWithOptionsMenu {

    public static VaccineType CURRENT_VACCINE_TYPE;

    private VaccineTypesViewModel mViewModel;
    private RecyclerView recyclerView;

    private FragmentVaccineTypesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentVaccineTypesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action there", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mViewModel = new ViewModelProvider(this, new VaccineTypesViewModelFactory()).get(VaccineTypesViewModel.class);
        //mViewModel = new DisplayNoteViewModel();
        mViewModel.fetchAllVaccineTypeLists();
        recyclerView = view.findViewById(R.id.vaccine_types_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mViewModel.getVaccineTypeList().observe(this.getViewLifecycleOwner(), vaccineTypeList -> {
            VaccineTypeRecyclerViewAdapter vaccineTypeRecyclerViewAdapter = new VaccineTypeRecyclerViewAdapter(vaccineTypeList);
            recyclerView.setAdapter(vaccineTypeRecyclerViewAdapter);
        });
    }

}