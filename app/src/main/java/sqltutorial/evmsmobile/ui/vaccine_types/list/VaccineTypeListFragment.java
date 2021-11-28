package sqltutorial.evmsmobile.ui.vaccine_types.list;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import sqltutorial.evmsmobile.R;
import sqltutorial.evmsmobile.data.model.VaccineType;
import sqltutorial.evmsmobile.databinding.FragmentVaccineTypeListBinding;
import sqltutorial.evmsmobile.ui.FragmentWithOptionsMenu;

public class VaccineTypeListFragment extends FragmentWithOptionsMenu {

    public static VaccineType CURRENT_VACCINE_TYPE;

    private VaccineTypeListViewModel mViewModel;
    private RecyclerView recyclerView;

    private FragmentVaccineTypeListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentVaccineTypeListBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CURRENT_VACCINE_TYPE = null;
                Navigation.findNavController(view).navigate(R.id.action_nav_vaccine_type_list_to_nav_maintain_vaccine_type);
            }
        });

        mViewModel = new ViewModelProvider(this, new VaccineTypeListViewModelFactory()).get(VaccineTypeListViewModel.class);
        mViewModel.fetchAllVaccineTypeLists();
        recyclerView = view.findViewById(R.id.vaccine_types_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mViewModel.getVaccineTypeList().observe(this.getViewLifecycleOwner(), vaccineTypeList -> {
            VaccineTypeListRecyclerViewAdapter vaccineTypeListRecyclerViewAdapter = new VaccineTypeListRecyclerViewAdapter(vaccineTypeList);
            recyclerView.setAdapter(vaccineTypeListRecyclerViewAdapter);
        });
    }

}