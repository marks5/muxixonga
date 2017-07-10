package br.com.gabriel.muxixi.presentation.fruits;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.gabriel.muxixi.R;
import br.com.gabriel.muxixi.data.remote.model.Fruits;

/**
 * Created by Gabriel on 09/07/2017.
 */

public class ListFruitsAdapter extends RecyclerView.Adapter<FruitsViewHolder> {

    private List<Fruits> items;

    public ListFruitsAdapter(List<Fruits> items) {
        this.items = items;
    }

    @Override
    public FruitsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruit_list, parent, false);
        return new FruitsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FruitsViewHolder holder, int position) {
        Fruits fruit = items.get(position);

        holder.tv_value_fruit.setText(String.format("$ %s",fruit.getPrice()));
        holder.tv_name_fruit.setText(String.format("%s",fruit.getName()));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    void setItems(List<Fruits> fruitsList){
        this.items = fruitsList;
        this.notifyDataSetChanged();
    }


}