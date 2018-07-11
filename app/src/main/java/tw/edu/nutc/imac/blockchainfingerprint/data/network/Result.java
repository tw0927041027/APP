package tw.edu.nutc.imac.blockchainfingerprint.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 依杰 on 2018/7/3.
 */

public class Result {
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("message")
    @Expose
    private List<String> message = null;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
