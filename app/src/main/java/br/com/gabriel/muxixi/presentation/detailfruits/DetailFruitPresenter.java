package br.com.gabriel.muxixi.presentation.detailfruits;


import br.com.gabriel.muxixi.data.remote.model.Fruits;
import br.com.gabriel.muxixi.presentation.base.BasePresenter;

/**
 * Created by Gabriel on 08/07/2017.
 */

public class DetailFruitPresenter extends BasePresenter<DetailFruitContract.View> implements DetailFruitContract.Presenter {

    @Override
    public void showFruit(Fruits fruit) {
        getView().showResult(fruit);
    }
}