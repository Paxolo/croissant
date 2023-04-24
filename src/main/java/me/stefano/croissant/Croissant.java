package me.stefano.croissant;

import com.google.gson.Gson;
import me.stefano.croissant.callback.CallbackHandler;
import me.stefano.croissant.command.CommandExecutor;
import me.stefano.croissant.command.CommandHandler;
import me.stefano.croissant.message.Messenger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Croissant {

    private static Croissant instance;

    public final Messenger MESSENGER;
    public final Gson GSON;

    private final TelegramLongPollingBot BOT;

    private CallbackHandler callbackHandler;
    private CommandHandler commandHandler;

    public Croissant(TelegramLongPollingBot bot) {
        instance = this;

        this.BOT = bot;

        this.MESSENGER = new Messenger();
        this.GSON = new Gson();

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
        return this.BOT;
    }

}