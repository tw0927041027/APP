package tw.edu.nutc.imac.blockchainfingerprint.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import tw.edu.nutc.imac.blockchainfingerprint.data.network.LoginResult;
import tw.edu.nutc.imac.blockchainfingerprint.data.network.RemoteSource;
import tw.edu.nutc.imac.blockchainfingerprint.data.network.Result;
import tw.edu.nutc.imac.blockchainfingerprint.data.prefs.PreferencesHelper;
import tw.edu.nutc.imac.blockchainfingerprint.data.prefs.PreferencesHelperImp;

/**
 * Created by 依杰 on 2017/8/14.
 */
@Module
public class RemoteModule {

    @Provides
    @Singleton
    public String provideBaseUrl() {
        return "http://10.0.0.59:8080/";
    }

    @Provides
    @Singleton
    public RemoteSource providesRemoteSource(Retrofit retrofit) {
        return retrofit.create(RemoteSource.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(String baseUrl, OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
                                            Interceptor interceptor,
                                            okhttp3.Authenticator authenticator) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(interceptor)
                .authenticator(authenticator)
                .retryOnConnectionFailure(true)
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .build();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    public Interceptor provideNetworkInterceptor(final PreferencesHelperImp preferencesHelperImp) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder()
                        .header("Authorization", String.format("Bearer %s", preferencesHelperImp.getToken()))
                        .header("Accept", "application/json")
                        .build());
            }
        };
    }

    @Provides
    @Singleton
    public okhttp3.Authenticator provideTokenAuthenticator(final String baseUrl,
                                                           final Gson gson,
                                                           final PreferencesHelper preferencesHelper) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        return new okhttp3.Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

                retrofit.create(RemoteSource.class).login(
                        preferencesHelper.getAccount(),
                        preferencesHelper.getPassword()).
                        toBlocking().subscribe(new Subscriber<LoginResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginResult result) {
                    }
                });
                return response.request().newBuilder()
                        .header("Authorization", String.format("Bearer %s", preferencesHelper.getToken()))
                        .header("Accept", "application/json")
                        .build();
            }
        };
    }
}
