package me.stefano.croissant.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class CMessage {
    private String text;
    private long chatId;
    private ReplyKeyboardMarkup markup;

    private CMessage(Builder builder) {
        this.text = builder.text;
        this.chatId = builder.chatId;
        this.markup = builder.markup;
    }

    public SendMessage toMessage() {
        var message = new SendMessage();
        message.setText(this.text);
        message.setChatId(this.chatId);
        message.setReplyMarkup(this.markup);

        return message;
    }

    public static class Builder {

        private String text;
        private long chatId;
        private ReplyKeyboardMarkup markup;

        public Builder() {

        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder chatId(long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder markup(ReplyKeyboardMarkup markup) {
            this.markup = markup;
            return this;
        }

        public CMessage build() {
            if (this.chatId == 0)  {
                System.out.println("You must specify a valid ChatID!");
                return null;
            }
            if (this.text.equals("")) {
                System.out.println("Text must not be empty!");
                return null;
            }
            return new CMessage(this);
        }

    }

}
