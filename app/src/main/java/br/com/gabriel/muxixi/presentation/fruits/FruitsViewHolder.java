package br.com.gabriel.muxixi.presentation.fruits;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.gabriel.muxixi.R;

/**
 * Created by Gabriel on 09/07/2017.
 */

public class FruitsViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_name_fruit;
    public TextView tv_value_fruit;
    public CardView cv_item;

    public FruitsViewHolder(View itemView) {
        super(itemView);
        tv_name_fruit = (TextView) itemView.findViewById(R.id.tv_name_fruit);
        tv_value_fruit = (TextView) itemView.findViewById(R.id.tv_value_fruit);
        cv_item = (CardView) itemView.findViewById(R.id.cv_item);

    }
}
