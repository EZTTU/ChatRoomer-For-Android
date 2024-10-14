package org.cuit.echo.seon.chatroomer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.cuit.echo.seon.chatroomer.R;
import org.cuit.echo.seon.chatroomer.adapter.ChatAdapter;
import org.cuit.echo.seon.chatroomer.viewmodel.ChatViewModel;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    private ChatAdapter chatAdapter;
    private ChatViewModel chatViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init viewmodel
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        // init RecycleView and adapter
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        chatAdapter = new ChatAdapter(new ArrayList<>());
        recyclerView.setAdapter(chatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // get input area and button of message send
        EditText messageInput = view.findViewById(R.id.message_input);
        Button sendButton = view.findViewById(R.id.send_button);
        sendButton.setOnClickListener(v -> {
            String message = messageInput.getText().toString();
            if (!message.isEmpty()) {
                chatViewModel.sendMessage(message);
                messageInput.setText("");
            }
        });

        // observe message list
        chatViewModel.getMessages().observe(getViewLifecycleOwner(), messages -> {
            chatAdapter.updateMessages(messages);
            if (!messages.isEmpty()) {
                recyclerView.smoothScrollToPosition(messages.size() - 1);
            }
        });
    }
}
