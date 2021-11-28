package sqltutorial.evmsmobile.data.model;

import androidx.annotation.NonNull;

public class VaccineType {
    private String name;
    private String description;

    public VaccineType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return "VaccineType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
