package com.example.frontend.api;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Jacob Burns
 */

/**
 * Callback class
 * @param <T>
 */
public class SlimCallBack<T> implements Callback<T> {
    LambdaInterface<T> lambdaInterface;

    String logTag;

    public SlimCallBack(LambdaInterface<T> lambdaInterface){
        this.lambdaInterface = lambdaInterface;
    }

    public SlimCallBack(LambdaInterface<T> lambdaInterface, String customTag){
        this.logTag = customTag;
        this.lambdaInterface = lambdaInterface;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()){
            lambdaInterface.doSomething(response.body());
        }
        else{
            Log.d(logTag, "Code: "+response.code() + "    Msg:  "+response.message());
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(logTag, "Thrown: "+t.getMessage());
    }
}
