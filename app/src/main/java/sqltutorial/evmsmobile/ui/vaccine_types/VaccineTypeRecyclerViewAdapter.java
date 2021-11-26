package sqltutorial.evmsmobile.ui.vaccine_types;

import static sqltutorial.evmsmobile.ui.vaccine_types.VaccineTypesFragment.CURRENT_VACCINE_TYPE;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sqltutorial.evmsmobile.R;
import sqltutorial.evmsmobile.data.model.VaccineType;

public class VaccineTypeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<VaccineType> vaccineTypeList;

    public VaccineTypeRecyclerViewAdapter(ArrayList<VaccineType> vaccineTypeList) {
        this.vaccineTypeList = vaccineTypeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaccine_type, parent, false);
        VaccineTypeViewHolder vaccineTypeViewHolder = new VaccineTypeViewHolder(v);
        return vaccineTypeViewHolder;
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

        public VaccineTypeViewHolder(View vaccineTypeView) {
            super(vaccineTypeView);

            cardView = vaccineTypeView.findViewById(R.id.vaccine_type_card_layout);
            vaccineTypeNameTextView = vaccineTypeView.findViewById(R.id.vaccine_type_name);
            vaccineTypeDescriptionTextView = vaccineTypeView.findViewById(R.id.vaccine_type_description);
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
                //Navigation.findNavController(cardView).navigate(R.id.action_home_fragment_to_add_vaccine_type_fragment);
            });
        }

    }
}
