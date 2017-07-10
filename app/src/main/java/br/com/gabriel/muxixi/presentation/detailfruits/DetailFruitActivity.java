package br.com.gabriel.muxixi.presentation.detailfruits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.gabriel.muxixi.R;
import br.com.gabriel.muxixi.data.remote.model.Fruits;
import br.com.gabriel.muxixi.util.PicassoCache;

public class DetailFruitActivity extends AppCompatActivity implements DetailFruitContract.View {

    private TextView tv_name_fruit;
    private TextView tv_price_fruit;
    private TextView tv_preco_fruit;
    private ImageView img_fruit;

    private Fruits fruit;

    private DetailFruitPresenter detailFruitPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fruit);
        detailFruitPresenter = new DetailFruitPresenter();
        detailFruitPresenter.attachView(this);

        tv_name_fruit = (TextView) findViewById(R.id.tv_name_fruit);
        tv_price_fruit = (TextView) findViewById(R.id.tv_price_fruit);
        tv_preco_fruit = (TextView) findViewById(R.id.tv_preco_fruit);
        img_fruit = (ImageView) findViewById(R.id.img_fruit);

        Bundle bundle = getIntent().getExtras();
        fruit = bundle.getParcelable("fruit");

        detailFruitPresenter.showFruit(fruit);
    }

    @Override
    public void showResult(Fruits fruits) {
        PicassoCache.getPicassoInstance(DetailFruitActivity.this).load(fruits.getImage()).into(img_fruit);
        tv_name_fruit.setText(String.format("Nome: %s",fruits.getName()));
        tv_price_fruit.setText(String.format("Preço: $ %s",fruits.getPrice()));
        tv_preco_fruit.setText(String.format("Preço: R$ %s",fruits.getValueConverted(fruits.getPrice())));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailFruitPresenter.detachView();
    }
}
