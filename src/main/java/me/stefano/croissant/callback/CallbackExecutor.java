package me.stefano.croissant.callback;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallbackExecutor {

    void execute(Update update);

}
