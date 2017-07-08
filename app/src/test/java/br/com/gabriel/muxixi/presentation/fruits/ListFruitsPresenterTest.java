package br.com.gabriel.muxixi.presentation.fruits;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.gabriel.muxixi.data.FruitsRepository;
import br.com.gabriel.muxixi.data.remote.model.Fruit;
import br.com.gabriel.muxixi.data.remote.model.Fruits;
import br.com.gabriel.muxixi.presentation.base.BasePresenter;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Gabriel on 08/07/2017.
 */
public class ListFruitsPresenterTest {
    @Mock
    FruitsRepository fruitsRepository;
    @Mock
    ListFruitsContract.View view;

    ListFruitsPresenter listFruitsPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        listFruitsPresenter = new ListFruitsPresenter(fruitsRepository, Schedulers.immediate(), Schedulers.immediate());
        listFruitsPresenter.attachView(view);
    }

    private static final String NAME_FRUIT_1_BANANA = "banana";
    private static final Double PRICE_FRUIT_1_BANANA = 35.0;
    private static final String NAME_FRUIT_2_MAMAO = "mamao";
    private static final Double PRICE_FRUIT_2_MAMAO = 7.0;

    @Test
    public void listFruits_ReturnsResults() {
        Fruit fruit = getDummyUserList();
        when(fruitsRepository.listFruits()).thenReturn(Observable.<List<Fruits>>just(fruit.getFruits()));

        listFruitsPresenter.listFruits();

        verify(view).showLoading();
        verify(view).hideLoading();
        verify(view).showResults(fruit.getFruits());
        verify(view, never()).showError(anyString());
    }

    Fruit getDummyUserList() {
        List<Fruits> fruits = new ArrayList<>();
        fruits.add(fruit1Full());
        fruits.add(fruit2Full());
        return new Fruit(fruits);
    }

    Fruits fruit1Full() {
        return new Fruits(NAME_FRUIT_1_BANANA, "banana_url", PRICE_FRUIT_1_BANANA);
    }

    Fruits fruit2Full() {
        return new Fruits(NAME_FRUIT_2_MAMAO, "mamao_url", PRICE_FRUIT_2_MAMAO);
    }


    @Test
    public void listFruits_ListFruitRepositoryError_ErrorMsg() {
        String errorMsg = "No internet";
        when(fruitsRepository.listFruits()).thenReturn(Observable.error(new IOException(errorMsg)));

        listFruitsPresenter.listFruits();

        verify(view).showLoading();
        verify(view).hideLoading();
        verify(view, never()).showResults(anyList());
        verify(view).showError(errorMsg);
    }

    @Test(expected = BasePresenter.MvpViewNotAttachedException.class)
    public void search_NotAttached_ThrowsMvpException() {
        listFruitsPresenter.detachView();

        listFruitsPresenter.listFruits();

        verify(view, never()).showLoading();
        verify(view, never()).showResults(anyList());
    }

}