package com.jagdon.jagdon_bottom_nav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentProfile extends Fragment {

    private EditText nameEditText;
    private RadioGroup genderRadioGroup;
    private CheckBox termsCheckBox;
    private Button submitButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views
        nameEditText = view.findViewById(R.id.nameEditText);
        genderRadioGroup = view.findViewById(R.id.genderRadioGroup);
        termsCheckBox = view.findViewById(R.id.termsCheckBox);
        submitButton = view.findViewById(R.id.submitButton);

        // Set onClick listener for the submit button
        submitButton.setOnClickListener(v -> submitProfile());

        return view;
    }

    private void submitProfile() {
        String name = nameEditText.getText().toString();
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedGenderButton = requireActivity().findViewById(selectedGenderId);
        String gender = selectedGenderButton != null ? selectedGenderButton.getText().toString() : "Not specified";
        boolean termsAccepted = termsCheckBox.isChecked();

        StringBuilder result = new StringBuilder();
        result.append("Name: ").append(name).append("\n");
        result.append("Gender: ").append(gender).append("\n");
        result.append("Terms accepted: ").append(termsAccepted ? "Yes" : "No");

        // Display the result as a Toast message (or handle it as needed)
        Toast.makeText(getActivity(), result.toString(), Toast.LENGTH_LONG).show();
    }
}
