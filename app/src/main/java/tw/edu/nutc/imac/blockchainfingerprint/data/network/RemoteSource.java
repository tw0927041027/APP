package tw.edu.nutc.imac.blockchainfingerprint.data.network;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by 依杰 on 2017/8/14.
 */

public interface RemoteSource {
    @POST("/api/signup")
    @FormUrlEncoded
    Observable<Result> register(@Field("name") String name,
                                @Field("email") String email,
                                @Field("password") String password);

    @POST("/api/login")
    @FormUrlEncoded
    Observable<Result> login(@Field("email") String account,
                             @Field("、password") String phone);
}
