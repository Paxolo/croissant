package me.stefano.croissant.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class CReply {
    private String text;
    private long chatId;
    private long replyId;
    private ReplyKeyboardMarkup markup;

    private CReply(Builder builder) {
        this.text = builder.text;
        this.chatId = builder.chatId;
        this.replyId = builder.replyId;
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
        private long replyId;
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

        public Builder replyId(long replyId) {
            this.replyId = replyId;
            return this;
        }

        public Builder markup(ReplyKeyboardMarkup markup) {
            this.markup = markup;
            return this;
        }

        public CReply build() {
            if (this.chatId == 0)  {
                System.out.println("You must specify a valid ChatID!");
                return null;
            }
            if (this.replyId == 0) {
                System.out.println("You must specify the Id of the message you want to reply to");
                return null;
            }
            if (this.text.equals("")) {
                System.out.println("Text must not be empty!");
                return null;
            }
            return new CReply(this);
        }

    }

}
