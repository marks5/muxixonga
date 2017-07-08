package br.com.gabriel.muxixi.data.remote;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Gabriel on 08/07/2017.
 */

public interface FruitsRestService {
    @GET("/fruits.json")
    Observable<br.com.gabriel.muxixi.data.remote.model.Fruit> listFruits();
}
