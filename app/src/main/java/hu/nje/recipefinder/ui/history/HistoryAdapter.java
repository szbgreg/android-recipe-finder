package hu.nje.recipefinder.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.nje.recipefinder.R;
import hu.nje.recipefinder.data.local.entity.SearchHistoryEntity;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    public interface OnHistoryClickListener {
        void onHistoryClick(SearchHistoryEntity item);
    }

    private final List<SearchHistoryEntity> items = new ArrayList<>();
    private final OnHistoryClickListener listener;

    public HistoryAdapter(OnHistoryClickListener listener) {
        this.listener = listener;
    }

    public void setItems(List<SearchHistoryEntity> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);

        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        SearchHistoryEntity item = items.get(position);

        holder.queryTextView.setText(item.searchText);

        holder.itemView.setOnClickListener(v -> listener.onHistoryClick(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView queryTextView;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            queryTextView = itemView.findViewById(R.id.historyQueryTextView);
        }
    }
}