package br.com.gabriel.muxixi.presentation.base;

/**
 * Created by Gabriel on 08/07/2017.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}