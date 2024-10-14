package org.cuit.echo.seon.chatroomer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {

    private final MutableLiveData<List<String>> messages = new MutableLiveData<>(new ArrayList<>());

    public MutableLiveData<List<String>> getMessages() {
        return this.messages;
    }

    public void sendMessage(String message) {
        List<String> currentMessages = messages.getValue();
        currentMessages.add(message);
        messages.setValue(currentMessages);

        // todo : 添加逻辑将消息发送到服务器

    }

    public void receiveMessage(String message) {
        List<String> currentMessages = messages.getValue();
        currentMessages.add(message);
        messages.setValue(currentMessages);

        // todo : 处理从服务器接收到的消息
    }
}
