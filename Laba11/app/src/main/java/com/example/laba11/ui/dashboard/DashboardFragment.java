package com.example.laba11.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.laba11.R;
import com.example.laba11.databinding.FragmentDashboardBinding;

import org.w3c.dom.Text;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            TextView name = (TextView) root.findViewById(R.id.text_dashboard);
            name.setText(getArguments().getString("name"));

            TextView calories = (TextView) root.findViewById(R.id.text_calorie);
            String formattedCalorie = String.format("Калорийность: %d", getArguments().getInt("calorie"));
            calories.setText(formattedCalorie);

            TextView time = (TextView) root.findViewById(R.id.text_time);
            String formattedTime = String.format("Время: %d", getArguments().getInt("time"));
            time.setText(formattedTime);

            TextView ingredients = (TextView) root.findViewById(R.id.text_ingredients);
            String formattedIngredients = String.format("Ингредиенты: %s", getArguments().getString("ingredients"));
            ingredients.setText(formattedIngredients);

            TextView difficulty = (TextView) root.findViewById(R.id.text_difficulty);
            String formattedDifficulty = String.format("Уровень сложности: %d", getArguments().getInt("difficulty"));
            difficulty.setText(formattedDifficulty);

        }

        return root;
    }

    @Override
    public void onDestroyView() {
//        Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_bottom_nav).popBackStack();
        super.onDestroyView();
        binding = null;
    }
}