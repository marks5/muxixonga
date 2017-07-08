package br.com.gabriel.muxixi.presentation.fruits;

import java.util.List;

import br.com.gabriel.muxixi.data.FruitsRepository;
import br.com.gabriel.muxixi.data.remote.model.Fruits;
import br.com.gabriel.muxixi.presentation.base.BasePresenter;
import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by Gabriel on 08/07/2017.
 */

public class ListFruitsPresenter extends BasePresenter<ListFruitsContract.View> implements ListFruitsContract.Presenter {
    private final Scheduler mainScheduler, ioScheduler;
    private FruitsRepository fruitsRepository;

    ListFruitsPresenter(FruitsRepository userRepository, Scheduler ioScheduler, Scheduler mainScheduler) {
        this.fruitsRepository = userRepository;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }

    @Override
    public void listFruits() {
        checkViewAttached();
        getView().showLoading();

        addSubscription(fruitsRepository.listFruits().subscribeOn(ioScheduler).observeOn(mainScheduler).subscribe(new Subscriber<List<Fruits>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
                getView().showError(e.getMessage());
            }

            @Override
            public void onNext(List<Fruits> fruitses) {
                getView().hideLoading();
                getView().showResults(fruitses);
            }
        }));
    }
}