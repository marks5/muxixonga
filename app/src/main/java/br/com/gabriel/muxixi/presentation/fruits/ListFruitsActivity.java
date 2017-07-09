package br.com.gabriel.muxixi.presentation.fruits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import br.com.gabriel.muxixi.R;
import br.com.gabriel.muxixi.data.remote.model.Fruits;
import br.com.gabriel.muxixi.injection.Injection;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListFruitsActivity extends AppCompatActivity implements ListFruitsContract.View {

    private ListFruitsPresenter listFruitsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fruits);
        listFruitsPresenter = new ListFruitsPresenter(Injection.provideListFruits(), Schedulers.io(), AndroidSchedulers.mainThread());
        listFruitsPresenter.attachView(this);
    }

    @Override
    public void showResults(List<Fruits> fruits) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listFruitsPresenter.detachView();
    }
}
