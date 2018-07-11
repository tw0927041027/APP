package tw.edu.nutc.imac.blockchainfingerprint.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 依杰 on 2018/7/11.
 */

public class LoginResult {
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("message")
    @Expose
    private LoginMessage message;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public LoginMessage getMessage() {
        return message;
    }

    public void setMessage(LoginMessage message) {
        this.message = message;
    }
}
