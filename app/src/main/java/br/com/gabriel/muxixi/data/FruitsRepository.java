package br.com.gabriel.muxixi.data;

import java.util.List;

import br.com.gabriel.muxixi.data.remote.model.Fruits;
import rx.Observable;

/**
 * Created by Gabriel on 08/07/2017.
 */

public interface FruitsRepository {
    Observable<List<Fruits>> listFruits();
}
