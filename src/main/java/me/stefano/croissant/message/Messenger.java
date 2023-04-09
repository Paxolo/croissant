package me.stefano.croissant.message;

import me.stefano.croissant.Croissant;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class Messenger {

    private final TelegramLongPollingBot BOT;

    public Messenger() {
        this.BOT = Croissant.get().getBot();
    }

    public boolean send(CMessage message) {
        return true;
    }

    public boolean send(CReply message) {
        return true;
    }

}
