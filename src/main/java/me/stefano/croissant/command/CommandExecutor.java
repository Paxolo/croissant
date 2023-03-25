package me.stefano.croissant.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandExecutor {

    void execute(Update update);

}
