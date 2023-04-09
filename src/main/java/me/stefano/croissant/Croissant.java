package me.stefano.croissant;

import com.google.gson.Gson;
import me.stefano.croissant.callback.CallbackHandler;
import me.stefano.croissant.command.CommandExecutor;
import me.stefano.croissant.command.CommandHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Croissant {


    private static final Gson GSON = new Gson();

    private static Croissant instance;

    private TelegramLongPollingBot bot;
    private CallbackHandler callbackHandler;
    private CommandHandler commandHandler;

    public Croissant(TelegramLongPollingBot bot) {
        instance = this;

        this.bot = bot;

        this.callbackHandler = new CallbackHandler();
        this.commandHandler = new CommandHandler();
    }

    public void registerCommand(CommandExecutor executor) {
        this.commandHandler.register(executor);
    }

    public void handle(Update update) {
        if (update.hasCallbackQuery()) {
            this.callbackHandler.handle(update);
            return;
        }

        if (update.hasMessage()) {
            var message = update.getMessage();
            var text = message.getText();

            if (!text.startsWith("/")) return;
            this.commandHandler.handle(update);
        }

    }

    public static Croissant get() {
        return instance;
    }

    public TelegramLongPollingBot getBot() {
        return this.bot;
    }

}