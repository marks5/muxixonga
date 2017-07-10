package br.com.gabriel.muxixi.presentation.fruits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import br.com.gabriel.muxixi.R;
import br.com.gabriel.muxixi.data.remote.model.Fruits;
import br.com.gabriel.muxixi.injection.Injection;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListFruitsActivity extends AppCompatActivity implements ListFruitsContract.View {

    private ListFruitsPresenter listFruitsPresenter;
    private ListFruitsAdapter listFruitAdapter;
    private ProgressBar progressBar;
    private TextView error_msg;
    private RecyclerView rv_fruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fruits);
        listFruitsPresenter = new ListFruitsPresenter(Injection.provideListFruits(), Schedulers.io(), AndroidSchedulers.mainThread());
        listFruitsPresenter.attachView(this);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        error_msg = (TextView) findViewById(R.id.error_msg);
        rv_fruits = (RecyclerView) findViewById(R.id.rv_fruits);
        listFruitAdapter = new ListFruitsAdapter(null);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        rv_fruits.setLayoutManager(glm);

        rv_fruits.setAdapter(listFruitAdapter);

        listFruitsPresenter.listFruits();
    }

    @Override
    public void showResults(List<Fruits> fruits) {
        rv_fruits.setVisibility(View.VISIBLE);
        error_msg.setVisibility(View.GONE);
        listFruitAdapter.setItems(fruits);
    }

    @Override
    public void showError(String message) {
        error_msg.setVisibility(View.VISIBLE);
        rv_fruits.setVisibility(View.GONE);
        error_msg.setText(message);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        rv_fruits.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        rv_fruits.setVisibility(View.VISIBLE);
        error_msg.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listFruitsPresenter.detachView();
    }
}
