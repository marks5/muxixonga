package br.com.gabriel.muxixi.data;

import java.io.IOException;
import java.util.List;

import br.com.gabriel.muxixi.data.remote.FruitsRestService;
import br.com.gabriel.muxixi.data.remote.model.Fruits;
import rx.Observable;

/**
 * Created by Gabriel on 08/07/2017.
 */

public class FruitsRepositoryImpl implements FruitsRepository {

    private FruitsRestService fruitsRestService;

    public FruitsRepositoryImpl(FruitsRestService fruitsRestService) {
        this.fruitsRestService = fruitsRestService;
    }

    @Override
    public Observable<List<Fruits>> listFruits() {
        return Observable.defer(() -> fruitsRestService.listFruits().concatMap(
                fruit -> Observable.from(fruit.getFruits()).toList()))
                .retryWhen(observable -> observable.flatMap(o -> {
                    if (o instanceof IOException) {
                        return Observable.just(null);
                    }
                    return Observable.error(o);
                }));
    }
}
