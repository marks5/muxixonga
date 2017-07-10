package br.com.gabriel.muxixi.injection;

import br.com.gabriel.muxixi.data.FruitsRepository;
import br.com.gabriel.muxixi.data.FruitsRepositoryImpl;
import br.com.gabriel.muxixi.data.remote.FruitsRestService;
import br.com.gabriel.muxixi.data.remote.MockFruitsRestServiceImpl;

/**
 * Created by Gabriel on 10/07/2017.
 */

public class InjectionMock {

    private static FruitsRestService fruitsRestService;

    public static FruitsRepository provideListFruits() {
        return new FruitsRepositoryImpl(provideFruitsRestService());
    }

    static FruitsRestService provideFruitsRestService() {
        if (fruitsRestService == null) {
            fruitsRestService = new MockFruitsRestServiceImpl();
        }
        return fruitsRestService;
    }

}
