package br.com.gabriel.muxixi.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.gabriel.muxixi.data.remote.FruitsRestService;
import br.com.gabriel.muxixi.data.remote.model.Fruit;
import br.com.gabriel.muxixi.data.remote.model.Fruits;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Gabriel on 08/07/2017.
 */
public class FruitsRepositoryImplTest {

    private static final String NAME_FRUIT_1_BANANA = "banana";
    private static final Double PRICE_FRUIT_1_BANANA = 35.0;
    private static final String NAME_FRUIT_2_MAMAO = "mamao";
    private static final Double PRICE_FRUIT_2_MAMAO = 7.0;

    @Mock
    FruitsRestService fruitsRestService;

    private FruitsRepository fruitsRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        fruitsRepository = new FruitsRepositoryImpl(fruitsRestService);
    }

    @Test
    public void listFruits_InvokesCorrectApiCalls() {
        //Given
        when(fruitsRestService.listFruits()).thenReturn(Observable.just(githubUserList()));

        //When
        TestSubscriber<List<Fruits>> subscriber = new TestSubscriber<>();
        fruitsRepository.listFruits().subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        List<List<Fruits>> onNextEvents = subscriber.getOnNextEvents();
        List<Fruits> fruitas = onNextEvents.get(0);
        Assert.assertEquals(NAME_FRUIT_1_BANANA, fruitas.get(0).getName());
        Assert.assertEquals(PRICE_FRUIT_1_BANANA, fruitas.get(0).getPrice());
        Assert.assertEquals(NAME_FRUIT_2_MAMAO, fruitas.get(1).getName());
        Assert.assertEquals(PRICE_FRUIT_2_MAMAO, fruitas.get(1).getPrice());
        verify(fruitsRestService).listFruits();
    }

    private Fruit githubUserList() {
        Fruits fruta1 = new Fruits();
        fruta1.setName(NAME_FRUIT_1_BANANA);
        fruta1.setPrice(PRICE_FRUIT_1_BANANA);

        Fruits fruta2 = new Fruits();
        fruta2.setName(NAME_FRUIT_2_MAMAO);
        fruta2.setPrice(PRICE_FRUIT_2_MAMAO);

        List<Fruits> listFruits = new ArrayList<>();
        listFruits.add(fruta1);
        listFruits.add(fruta2);
        Fruit fruit = new Fruit();
        fruit.setFruits(listFruits);
        return fruit;
    }

    @Test
    public void listFruits_IOException() {
        //Given
        when(fruitsRestService.listFruits())
                .thenReturn(getIOExceptionError(), Observable.just(githubUserList()));

        //When
        TestSubscriber<List<Fruits>> subscriber = new TestSubscriber<>();
        fruitsRepository.listFruits().subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        verify(fruitsRestService, times(2)).listFruits();
    }

    private Observable getIOExceptionError() {
        return Observable.error(new IOException());
    }


}