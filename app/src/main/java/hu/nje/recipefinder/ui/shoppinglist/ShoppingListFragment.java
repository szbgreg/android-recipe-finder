package hu.nje.recipefinder.ui.shoppinglist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.nje.recipefinder.R;
import hu.nje.recipefinder.data.local.database.AppDatabase;
import hu.nje.recipefinder.data.local.entity.ShoppingListItemEntity;

public class ShoppingListFragment extends Fragment {

    private ShoppingListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.shoppingListRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new ShoppingListAdapter();
        recyclerView.setAdapter(adapter);

        List<ShoppingListItemEntity> items =
                AppDatabase.getInstance(requireContext())
                        .shoppingListDao()
                        .getAllItems();

        adapter.setItems(items);

        return view;
    }
}