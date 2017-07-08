package br.com.gabriel.muxixi.data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.gabriel.muxixi.data.remote.FruitsRestService;

import static org.junit.Assert.*;

/**
 * Created by Gabriel on 08/07/2017.
 */
public class FruitsRepositoryImplTest {
    @Mock
    FruitsRestService fruitsRestService;

    private FruitsRepository fruitsRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        fruitsRepository = new FruitsRepositoryImpl(fruitsRestService);
    }

    

}