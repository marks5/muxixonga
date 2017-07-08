package br.com.gabriel.muxixi.presentation.fruits;

import java.util.List;

import br.com.gabriel.muxixi.data.remote.model.Fruits;
import br.com.gabriel.muxixi.presentation.base.MvpPresenter;
import br.com.gabriel.muxixi.presentation.base.MvpView;

/**
 * Created by Gabriel on 08/07/2017.
 */

public interface ListFruitsContract {
    interface View extends MvpView {
        void showResults(List<Fruits> fruits);

        void showError(String message);

        void showLoading();

        void hideLoading();
    }

    interface Presenter extends MvpPresenter<View> {
        void listFruits();
    }
}
