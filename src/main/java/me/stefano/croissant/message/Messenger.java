package me.stefano.croissant.message;

import me.stefano.croissant.Croissant;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Messenger {

    private final TelegramLongPollingBot BOT;

    public Messenger() {
        this.BOT = Croissant.get().getBot();
    }

    public boolean send(CMessage message) {
        try {
            BOT.execute(message.toMessage());
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean send(CReply message) {
        try {
            BOT.execute(message.toMessage());
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
