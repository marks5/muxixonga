package br.com.gabriel.muxixi.presentation.fruits;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.gabriel.muxixi.R;
import br.com.gabriel.muxixi.data.remote.model.Fruits;
import br.com.gabriel.muxixi.presentation.detailfruits.DetailFruitActivity;

/**
 * Created by Gabriel on 09/07/2017.
 */

public class ListFruitsAdapter extends RecyclerView.Adapter<FruitsViewHolder> {

    private List<Fruits> items;
    private Context context;

    public ListFruitsAdapter(List<Fruits> items) {
        this.items = items;
    }

    @Override
    public FruitsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruit_list, parent, false);
        context = parent.getContext();
        return new FruitsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FruitsViewHolder holder, int position) {
        Fruits fruit = items.get(position);

        holder.tv_value_fruit.setText(String.format("$ %s",fruit.getPrice()));
        holder.tv_name_fruit.setText(String.format("%s",fruit.getName()));

        holder.cv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailFruitActivity.class);
                intent.putExtra("fruit",fruit);
                context.startActivity(intent);
            }
        });
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