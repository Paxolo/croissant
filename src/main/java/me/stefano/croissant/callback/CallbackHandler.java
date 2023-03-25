package me.stefano.croissant.callback;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;

public class CallbackHandler {

    private HashMap<String, CallbackExecutor> callbackMap;

    public CallbackHandler() {
        this.callbackMap = new HashMap<>();
    }

    public void register(String callback, CallbackExecutor executor) {
        this.callbackMap.put(callback, executor);
    }

    public void handle(Update update) {
        var callbackData = update.getCallbackQuery().getData();
        this.callbackMap.getOrDefault(callbackData, null).execute(update);
    }

}
