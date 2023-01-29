package com.example.SpringDemoBot.service;

import com.example.SpringDemoBot.config.BotConfig;
import com.example.SpringDemoBot.controller.MatchController;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
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
import java.util.Arrays;
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
                    startCommandReceived(chatId);
                    break;
                case "Игрок года":
                    topPlayer(chatId);
                    break;
                case "Топ по категории":
                    topCategory(chatId);
                    break;
                case "Вся статистика":
                    allStats(chatId);
                    break;
                case "Топ клатч":
                    topClutch(chatId);
                    break;
                case "Топ рейтинг":
                    topRating(chatId);
                    break;
                case "Топ энтри":
                    topOpenKill(chatId);
                    break;
                case "Топ флеш":
                    topFlash(chatId);
                    break;
                case "Топ размен":
                    topTrade(chatId);
                    break;
                case "Топ прострел":
                    topWallbang(chatId);
                    break;
                case "Топ 3 kill":
                    topThreeKill(chatId);
                    break;
                case "Топ 4 kill":
                    topFourKill(chatId);
                    break;
                case "Топ ace":
                    topAce(chatId);
                    break;
                case "18 год":
                    topYear(chatId,2018);
                    break;
                case "19 год":
                    topYear(chatId,2019);
                    break;
                case "20 год":
                    topYear(chatId,2020);
                    break;
                case "21 год":
                    topYear(chatId,2021);
                    break;
                case "22 год":
                    topYear(chatId,2022);
                    break;
                case "Матчи Desmond":
                    allMatchesByPlayer(chatId,"desmond");
                    break;
                case "Матчи BlackVision":
                    allMatchesByPlayer(chatId,"BlackVision");
                    break;
                case "Матчи Tilt":
                    allMatchesByPlayer(chatId,"Tilt");
                    break;
                case "Матчи Lakich":
                    allMatchesByPlayer(chatId,"Lakich");
                    break;
                case "Матчи 221w33":
                    allMatchesByPlayer(chatId,"221w33");
                    break;
                case "Топ со всей статой":
                    allStatsTop(chatId);
                default:
                    sendMessage(chatId,"Sorry, nope!");
            }
        }
    }

    private void startCommandReceived(long chatId){
        String answer = "Hi, Cheboksar!";
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

    private void allStats(long chatId){
        String answer = "Нажми на кнопку";
        sendMessage(chatId,answer);
    }
    private void topClutch(long chatId){
        RestTemplate restTemplate = new RestTemplate();

        String http = restTemplate.getForObject("http://localhost:8080/getclutches", String.class);
        http = http.replaceAll("[\\[-\\]\"]","");
        String[] arr = http.split(",");

        String answer = "Топ по клатчам: " +
                "\nИгрок: "+arr[0]+
                "\nВсего игр: "+arr[1]+
                "\nОбщее количество клатчей: "+arr[7]+
                "\n1vs1: "+arr[2]+
                "\n1vs2: "+arr[3]+
                "\n1vs3: "+arr[4]+
                "\n1vs4: "+arr[5]+
                "\n1vs5: "+arr[6];
        sendMessage(chatId,answer);
    }

    private void topYear(long chatId,int year){
        RestTemplate restTemplate = new RestTemplate();

        String http = restTemplate.getForObject("http://localhost:8080/top/getbyyear/"+year, String.class);
        http = http.replaceAll("[\\[-\\]\"]","");
        String[] arr = http.split(",");

        StringBuilder answerb = new StringBuilder("Топ "+year+" года: ") ;

        for (int i = 1; i < arr.length; i+=3) {
            answerb.append("\nИгрок: ").append(arr[i])
                    .append("\nМесто: ").append(arr[i+1])
                    .append("\nОбщий рейтинг: ").append(arr[i+2]);
            answerb.append("\n------------------");
        }
        String answer = String.valueOf(answerb);
        sendMessage(chatId,answer);
    }

    private void allStatsTop(long chatId){
        RestTemplate restTemplate = new RestTemplate();

        String http = restTemplate.getForObject("http://localhost:8080/top/getbyyear/", String.class);
        http = http.replaceAll("[\\[-\\]\"]","");
        String[] arr = http.split(",");

        StringBuilder answerb = new StringBuilder("Топ  года: ") ;

        for (int i = 1; i < arr.length; i+=3) {
            answerb.append("\nИгрок: ").append(arr[i])
                    .append("\nМесто: ").append(arr[i+1])
                    .append("\nОбщий рейтинг: ").append(arr[i+2]);
            answerb.append("\n------------------");
        }
        String answer = String.valueOf(answerb);
        sendMessage(chatId,answer);
    }

    private void allMatchesByPlayer(long chatId, String name){
        RestTemplate restTemplate = new RestTemplate();

        String http = restTemplate.getForObject("http://localhost:8080/getbyname/"+name, String.class);
        http = http.replaceAll("[\\[-\\]\"{}]","");
        String[] arr = http.split(",");

        StringBuilder answerb = new StringBuilder("Последние 7 матчей " +name+":\n") ;
        System.out.println(arr.length);
        for (int i = 0; i < arr.length; i+=18) {
            answerb.append("\n").append(arr[i+2])
                    .append("\n").append(arr[i+3])
                    .append("\n").append(arr[i+4])
                    .append("\n").append(arr[i+5])
                    .append("\n").append(arr[i+6])
                    .append("\n").append(arr[i+7])
                    .append("\n").append(arr[i+8])
                    .append("\n").append(arr[i+9])
                    .append("\n").append(arr[i+10])
                    .append("\n").append(arr[i+11])
                    .append("\n").append(arr[i+12])
                    .append("\n").append(arr[i+13])
                    .append("\n").append(arr[i+14])
                    .append("\n").append(arr[i+15])
                    .append("\n").append(arr[i+16])
                    .append("\n").append(arr[i+17]);
            answerb.append("\n------------------");
        }

        String answer = String.valueOf(answerb);
        sendMessage(chatId,answer);
    }

    private void topRating(long chatId){
        RestTemplate restTemplate = new RestTemplate();

        String http = restTemplate.getForObject("http://localhost:8080/getrating", String.class);
        http = http.replaceAll("[\\[-\\]\"]","");
        String[] arr = http.split(",");

        String answer = "Топ по рейтингу: " +
                "\nИгрок: "+arr[0]+
                "\nВсего игр: "+arr[1]+
                "\nОбщий рейтинг: "+arr[2];
        sendMessage(chatId,answer);
    }

    private void topOpenKill(long chatId){
        RestTemplate restTemplate = new RestTemplate();

        String http = restTemplate.getForObject("http://localhost:8080/getopenkill", String.class);
        http = http.replaceAll("[\\[-\\]\"]","");
        String[] arr = http.split(",");
        String answer = "Топ по энтри: " +
                "\nИгрок: "+arr[0]+
                "\nВсего игр: "+arr[1]+
                "\nЭнтри за матч: "+arr[2];
        sendMessage(chatId,answer);
    }

    private void topFlash(long chatId){
        RestTemplate restTemplate = new RestTemplate();

        String http = restTemplate.getForObject("http://localhost:8080/getflash", String.class);
        http = http.replaceAll("[\\[-\\]\"]","");
        String[] arr = http.split(",");
        String answer = "Топ по кол-ву флешек: " +
                "\nИгрок: "+arr[0]+
                "\nВсего игр: "+arr[1]+
                "\nФлешек за матч: "+arr[2];
        sendMessage(chatId,answer);
    }

    private void topTrade(long chatId){
        RestTemplate restTemplate = new RestTemplate();

        String http = restTemplate.getForObject("http://localhost:8080/gettrade", String.class);
        http = http.replaceAll("[\\[-\\]\"]","");
        String[] arr = http.split(",");
        String answer = "Топ по кол-ву разменов: " +
                "\nИгрок: "+arr[0]+
                "\nВсего игр: "+arr[1]+
                "\nРазменов за матч: "+arr[2];
        sendMessage(chatId,answer);
    }

    private void topWallbang(long chatId){
        RestTemplate restTemplate = new RestTemplate();

        String http = restTemplate.getForObject("http://localhost:8080/getwallbang", String.class);
        http = http.replaceAll("[\\[-\\]\"]","");
        String[] arr = http.split(",");
        String answer = "Топ по кол-ву прострелов: " +
                "\nИгрок: "+arr[0]+
                "\nВсего игр: "+arr[1]+
                "\nПрострелов за матч: "+arr[2];
        sendMessage(chatId,answer);
    }
    private void topThreeKill(long chatId){
        RestTemplate restTemplate = new RestTemplate();

        String http = restTemplate.getForObject("http://localhost:8080/getthreekill", String.class);
        http = http.replaceAll("[\\[-\\]\"]","");
        String[] arr = http.split(",");
        String answer = "Топ по кол-ву трипл киллов: " +
                "\nИгрок: "+arr[0]+
                "\nВсего игр: "+arr[1]+
                "\nТрипл киллов за матч: "+arr[2];
        sendMessage(chatId,answer);
    }

    private void topFourKill(long chatId){
        RestTemplate restTemplate = new RestTemplate();

        String http = restTemplate.getForObject("http://localhost:8080/getfourkill", String.class);
        http = http.replaceAll("[\\[-\\]\"]","");
        String[] arr = http.split(",");
        String answer = "Топ по кол-ву квадро киллов: " +
                "\nИгрок: "+arr[0]+
                "\nВсего игр: "+arr[1]+
                "\nКвадро киллов за матч: "+arr[2];
        sendMessage(chatId,answer);
    }

    private void topAce(long chatId){
        RestTemplate restTemplate = new RestTemplate();

        String http = restTemplate.getForObject("http://localhost:8080/getace", String.class);
        http = http.replaceAll("[\\[-\\]\"]","");
        String[] arr = http.split(",");
        String answer = "Топ по кол-ву эйсов: " +
                "\nИгрок: "+arr[0]+
                "\nВсего игр: "+arr[1]+
                "\nэйсов за матч: "+arr[2];
        sendMessage(chatId,answer);
    }

    private void startButton(SendMessage message){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Игрок года");
        row.add("Топ по категории");
        row.add("Вся статистика");

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

    private void allStatsButton(SendMessage message){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Матчи Desmond");
        row.add("Матчи BlackVision");
        row.add("Матчи Tilt");

        keyboardRows.add(row);

        row = new KeyboardRow();

        row.add("Матчи Lakich");
        row.add("Матчи 221w33");
        row.add("Топ со всей статой");

        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);

    }

    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        switch(textToSend){
            case "Hi, Cheboksar!":
                startButton(message);
                break;
            case "Выберите год":
                topPlayerButton(message);
                break;
            case "Выберите категорию":
                topCategoryButton(message);
                break;
            case "Нажми на кнопку":
                allStatsButton(message);
        }

        try{
            execute(message);
        }
        catch (TelegramApiException e){
        }
    }
}