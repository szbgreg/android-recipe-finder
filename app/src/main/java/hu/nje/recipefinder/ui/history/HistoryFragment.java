package hu.nje.recipefinder.ui.history;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.nje.recipefinder.R;
import hu.nje.recipefinder.data.local.database.AppDatabase;
import hu.nje.recipefinder.data.local.entity.SearchHistoryEntity;

public class HistoryFragment extends Fragment {

    private HistoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.historyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new HistoryAdapter(item -> {
            Bundle args = new Bundle();
            args.putString("query", item.searchText);
            args.putString("type", "search");

            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_history_to_list, args);
        });

        recyclerView.setAdapter(adapter);

        List<SearchHistoryEntity> historyItems =
                AppDatabase.getInstance(requireContext())
                        .searchHistoryDao()
                        .getLast10Searches();

        adapter.setItems(historyItems);

        return view;
    }
}