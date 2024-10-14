package org.cuit.echo.seon.chatroomer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<Boolean> loginResult = new MutableLiveData<>(false);

    public LiveData<Boolean> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // todo : 调用接口进行认证
        loginResult.postValue(true);
    }
}
