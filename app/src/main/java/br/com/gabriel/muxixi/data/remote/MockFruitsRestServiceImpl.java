package br.com.gabriel.muxixi.data.remote;

import java.util.ArrayList;
import java.util.List;

import br.com.gabriel.muxixi.data.remote.model.Fruit;
import br.com.gabriel.muxixi.data.remote.model.Fruits;
import rx.Observable;

/**
 * Created by Gabriel on 10/07/2017.
 */

public class MockFruitsRestServiceImpl implements FruitsRestService {

    private Fruit fruits = new Fruit();
    private static Observable listFruitsResult = null;

    public MockFruitsRestServiceImpl() {
        List<Fruits> fruitses = new ArrayList<>();
        fruitses.add(fruit1());
        fruitses.add(fruit2());
        fruits.setFruits(fruitses);
    }

    @Override
    public Observable<Fruit> listFruits() {
        if (listFruitsResult != null) {
            return listFruitsResult;
        }
        return Observable.just(fruits);
    }

    Fruits fruit1(){
        Fruits fruit = new Fruits();
        fruit.setName("BANANA");
        fruit.setPrice(0.35);
        fruit.setImage("banana_img");
        return fruit;
    }

    Fruits fruit2(){
        Fruits fruit = new Fruits();
        fruit.setName("MAMAO");
        fruit.setPrice(0.45);
        fruit.setImage("mamao_img");
        return fruit;
    }

    public static void setListFruitsResult(Observable list) {
        listFruitsResult = list;
    }


}
