package br.com.gabriel.muxixi.presentation.detailfruits;

import br.com.gabriel.muxixi.data.remote.model.Fruits;
import br.com.gabriel.muxixi.presentation.base.MvpPresenter;
import br.com.gabriel.muxixi.presentation.base.MvpView;

/**
 * Created by Gabriel on 08/07/2017.
 */

public interface DetailFruitContract {
    interface View extends MvpView {
        void showResult(Fruits fruits);
    }

    interface Presenter extends MvpPresenter<View> {
        void showFruit(Fruits fruit);
    }
}
