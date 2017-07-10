package br.com.gabriel.muxixi.injection;

import br.com.gabriel.muxixi.data.FruitsRepository;
import br.com.gabriel.muxixi.data.FruitsRepositoryImpl;
import br.com.gabriel.muxixi.data.remote.FruitsRestService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gabriel on 08/07/2017.
 */

public class Injection {
    private static final String BASE_URL = "https://raw.githubusercontent.com/muxidev/desafio-android/master/";
    private static OkHttpClient okHttpClient;
    private static FruitsRestService fruitsRestService;
    private static Retrofit retrofitInstance;


    public static FruitsRepository provideListFruits() {
        return new FruitsRepositoryImpl(provideFruitsRepo());
    }

    static FruitsRestService provideFruitsRepo() {
        if (fruitsRestService == null) {
            fruitsRestService = getRetrofitInstance().create(FruitsRestService.class);
        }
        return fruitsRestService;
    }

    static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        }

        return okHttpClient;
    }

    static Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder().client(Injection.getOkHttpClient()).baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            retrofitInstance = retrofit.build();

        }
        return retrofitInstance;
    }
}
