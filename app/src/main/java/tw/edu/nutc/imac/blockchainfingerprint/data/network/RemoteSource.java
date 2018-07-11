package tw.edu.nutc.imac.blockchainfingerprint.data.network;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by 依杰 on 2017/8/14.
 */

public interface RemoteSource {
    @POST("/api/v1/register")
    @FormUrlEncoded
    Observable<Result> register(@Field("email") String email,
                                @Field("password") String password,
                                @Field("country") String country,
                                @Field("name") String name,
                                @Field("phone") String phone,
                                @Field("birthday") String birthday);

    @POST("/api/v1/login")
    @FormUrlEncoded
    Observable<LoginResult> login(@Field("account") String account,
                                  @Field("password") String phone);
}
