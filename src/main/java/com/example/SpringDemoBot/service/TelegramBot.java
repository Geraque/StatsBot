package com.example.SpringDemoBot.service;

import com.example.SpringDemoBot.config.BotConfig;
import com.example.SpringDemoBot.controller.MatchController;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {


    final BotConfig config;

    public TelegramBot(BotConfig config){
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start","Вернуться в главное меню"));
        try{
            this.execute(new SetMyCommands(listOfCommands,new BotCommandScopeDefault(),null));
        }
        catch (TelegramApiException e){

        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch(messageText){
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "Игрок года":
                    topPlayer(chatId);
                    break;
                case "Топ по категории":
                    topCategory(chatId);
                    break;
                case"Топ клатч":

                default:
                    sendMessage(chatId,"Sorry, nope!");
            }
        }
    }

    private void startCommandReceived(long chatId, String name){
        String answer = "Hi, "+name+", nice to meet you!";
        sendMessage(chatId,answer);
    }

    private void topPlayer(long chatId){
        String answer = "Выберите год";
        sendMessage(chatId,answer);
    }

    private void topCategory(long chatId){
        String answer = "Выберите категорию";
        sendMessage(chatId,answer);
    }
    private void topClutch(long chatId){
        String answer = "Выберите категорию";
        sendMessage(chatId,answer);
    }

    private void startButton(SendMessage message){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Игрок года");
        row.add("Топ по категории");

        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);
    }

    private void topPlayerButton(SendMessage message){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("18 год");
        row.add("19 год");
        row.add("20 год");

        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("21 год");
        row.add("22 год");
        row.add("23 год");

        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

    }

    private void topCategoryButton(SendMessage message){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Топ рейтинг");
        row.add("Топ клатч");
        row.add("Топ энтри");

        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("Топ флеш");
        row.add("Топ размен");
        row.add("Топ прострел");

        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("Топ 3 kill");
        row.add("Топ 4 kill");
        row.add("Топ ace");

        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

    }

    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        switch(textToSend){
            case "Hi, Alexander, nice to meet you!":
                startButton(message);
                break;
            case "Выберите год":
                topPlayerButton(message);
                break;
            case "Выберите категорию":
                topCategoryButton(message);
                break;
        }

        try{
            execute(message);
        }
        catch (TelegramApiException e){
        }
    }

    private void sendMessage2(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("18 год");
        row.add("19 год");
        row.add("20 год");

        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("21 год");
        row.add("22 год");
        row.add("23 год");

        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

        try{
            execute(message);
        }
        catch (TelegramApiException e){
        }
    }
}