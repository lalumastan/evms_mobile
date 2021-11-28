package sqltutorial.evmsmobile.ui.vaccine_types.list;

import static sqltutorial.evmsmobile.ui.vaccine_types.list.VaccineTypeListFragment.CURRENT_VACCINE_TYPE;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sqltutorial.evmsmobile.R;
import sqltutorial.evmsmobile.data.model.VaccineType;
import sqltutorial.evmsmobile.databinding.FragmentVaccineTypeBinding;

public class VaccineTypeListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<VaccineType> vaccineTypeList;

    public VaccineTypeListRecyclerViewAdapter(ArrayList<VaccineType> vaccineTypeList) {
        this.vaccineTypeList = vaccineTypeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VaccineTypeListRecyclerViewAdapter.VaccineTypeViewHolder(FragmentVaccineTypeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VaccineType vaccineType = vaccineTypeList.get(position);
        ((VaccineTypeViewHolder) holder).bind(vaccineType);
    }

    @Override
    public int getItemCount() {
        return vaccineTypeList.size();
    }

    public class VaccineTypeViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView vaccineTypeNameTextView, vaccineTypeDescriptionTextView;

        public VaccineTypeViewHolder(FragmentVaccineTypeBinding binding) {
            super(binding.getRoot());

            cardView = binding.vaccineTypeCardLayout;
            vaccineTypeNameTextView = binding.vaccineTypeName;
            vaccineTypeDescriptionTextView = binding.vaccineTypeDescription;
        }

        private String fixNull(String str) {
            return str == null || (str != null && "null".equals(str)) ? "" : str;
        }

        public void bind(final VaccineType vaccineType) {
            String title = vaccineType.getName(), description = vaccineType.getDescription();
            vaccineTypeNameTextView.setText(fixNull(title));
            vaccineTypeDescriptionTextView.setText(fixNull(description));
            cardView.setOnClickListener(e -> {
                CURRENT_VACCINE_TYPE = vaccineType;
                Navigation.findNavController(cardView).navigate(R.id.action_nav_vaccine_type_list_to_nav_maintain_vaccine_type);
            });
        }

    }
}
