package com.imatechnophile.telegrambot;

import com.imatechnophile.telegrambot.models.covid19.District;
import com.imatechnophile.telegrambot.models.covid19.TnDistricts;
import com.imatechnophile.telegrambot.models.covid19.TotalIndia;
import com.imatechnophile.telegrambot.models.spring.SpringQuote;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {

    private final Logger LOG = LoggerFactory.getLogger(MyBot.class);
    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.username}")
    private String botUsername;


    OkHttpClient okHttpClient;
    Request request;
    Response response=null;
    JSONObject jsonObject;

    String sendCoronaDataNumbers="";
    static String welcomemessage =
            "Thank you for using ImATechnophile Bot \uD83D\uDE09.\n\n" +
            "This bot will show you \n\n1.Covid19 Global data,\n2.Covid19 India data,\n" +
            "3.Covid19 Tamil Nadu data,\n4.Covid19 all districts of Tamil Nadu data,\n" +
            "5.Spring Boot Quotes,\n" +
            "more features are coming soon \uD83D\uDD25.\n" +
            "\n\n" +
            "Developer \uD83D\uDC68\u200D\uD83D\uDCBB : https://www.linkedin.com/in/saravana-prakash/";

    JSONParser parser =  new JSONParser();
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();

        if(update.getMessage().getText().equals("/start") || update.getMessage().getText().equals("Back") || update.getMessage().getText().equals("/start@ImATechnophile"))
        {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            List <KeyboardRow> keyboardRowList = new ArrayList<>();
            KeyboardRow row;

            row=new KeyboardRow();
            row.add("COVID \uD83E\uDDA0 GLOBAL DATA \uD83D\uDCCA");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("COVID \uD83E\uDDA0 INDIA DATA \uD83D\uDCCA");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("COVID \uD83E\uDDA0 Tamil Nadu DATA \uD83D\uDCCA");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("COVID \uD83E\uDDA0 Tamil Nadu Districts DATA \uD83D\uDCCA");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("Spring Boot Quote");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("About me");
            keyboardRowList.add(row);

            replyKeyboardMarkup.setKeyboard(keyboardRowList);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            sendMessage.setText("Hi "+ update.getMessage().getFrom().getFirstName() + " \uD83D\uDE4B\u200D♂️,\n\n" +welcomemessage);
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("COVID \uD83E\uDDA0 GLOBAL DATA \uD83D\uDCCA"))
        {
            try {
                okHttpClient = new OkHttpClient();
                request = new Request.Builder()
                        .url("https://disease.sh/v2/all")
                        .get()
                        .build();
                Response response = okHttpClient.newCall(request).execute();
                String data = response.body().string();
                JSONParser jsonParser=new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(data);

                sendMessage.setText("COVID 19 GLOBAL DATA\n\nTotal cases : "+jsonObject.get("cases")+
                                    "\nRecovered : "+jsonObject.get("recovered")+
                                    "\nActive : "+jsonObject.get("active")+
                                    "\nCritical : "+jsonObject.get("critical")+
                                    "\nTotal Deaths : "+jsonObject.get("deaths")+
                                    "\nToday Cases : "+jsonObject.get("todayCases")+
                                    "\nToday Recovered : "+jsonObject.get("todayRecovered")
                                    // "\nToday Deaths : "+jsonObject.get("todayDeaths")
                                    );
                sendMessage.setChatId(update.getMessage().getChatId());
                execute(sendMessage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("COVID \uD83E\uDDA0 INDIA DATA \uD83D\uDCCA"))
        {
            try {
                
                final String uri = "https://api.covidindiatracker.com/total.json";
                TotalIndia result = restTemplate.getForObject(uri, TotalIndia.class);
                
               // System.out.println(result);

                sendMessage.setText("COVID 19 INDIA DATA\n\nTotal cases : "+result.getConfirmed()+
                                    "\nRecovered : "+result.getRecovered()+
                                    "\nActive : "+result.getActive()+  
                                    "\nTotal Deaths : "+result.getDeaths()
                                    // "\nDaily changes in Confirmed Cases : "+result.getCChanges()+
                                    // "\nDaily changes in Active Cases : "+result.getAChanges()+
                                    // "\nDaily changes in Recovery Cases : "+result.getRChanges()+
                                    // "\nDaily changes in Death Cases : "+result.getDChanges()
                                    );
                sendMessage.setChatId(update.getMessage().getChatId());
                execute(sendMessage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("COVID \uD83E\uDDA0 Tamil Nadu DATA \uD83D\uDCCA"))
        {
            try {
                
                final String uri = "https://api.covidindiatracker.com/state_data.json";
                TotalIndia[] result = restTemplate.getForObject(uri, TotalIndia[].class);

                // System.out.println(result);

                for(int i=0; i < result.length; i++) {
                    if(result[i].getState().equals("Tamil Nadu")) {
                        sendMessage.setText("COVID 19 Tamil Nadu DATA\n\nTotal cases : "+result[i].getConfirmed()+
                        "\nRecovered : "+result[i].getRecovered()+
                        "\nActive : "+result[i].getActive()+
                        "\nTotal Deaths : "+result[i].getDeaths()
                        // "\nDaily changes in Confirmed Cases : "+result[i].getCChanges()+
                        // "\nDaily changes in Active Cases : "+result[i].getAChanges()+
                        // "\nDaily changes in Recovery Cases : "+result[i].getRChanges()+
                        // "\nDaily changes in Death Cases : "+result[i].getDChanges()
                        );
                    }
                }
 
                sendMessage.setChatId(update.getMessage().getChatId());
                execute(sendMessage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if (update.getMessage().getText().equals("COVID \uD83E\uDDA0 Tamil Nadu Districts DATA \uD83D\uDCCA"))
        {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            List <KeyboardRow> keyboardRowList = new ArrayList<>();
            KeyboardRow row;
            row=new KeyboardRow();
            row.add("Back");
            keyboardRowList.add(row);

            final String uri = "https://api.covidindiatracker.com/state_data.json";
            TotalIndia[] result = restTemplate.getForObject(uri, TotalIndia[].class);

            // System.out.println(result);

            for(int i=0; i < result.length; i++) {
                if(result[i].getState().equals("Tamil Nadu")) {
                    List<District> allDistrict = result[i].getDistrictData();
                    for (int j = 0; j < allDistrict.size(); j+=2) {
                        row = new KeyboardRow();
                        row.add(allDistrict.get(j).getId());
                        if (j != allDistrict.size() - 1) {
                            row.add(allDistrict.get(j + 1).getId());
                        }
                        keyboardRowList.add(row);
                    }
                }
            }

            replyKeyboardMarkup.setKeyboard(keyboardRowList);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);

            try
            {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText(
                        " Please select the name of the district to get the data" +
                        "\n\n");
                execute(sendMessage);

            }
            catch (Exception e){ e.printStackTrace();}
        }

        else if(update.getMessage().getText().equals("Airport-Quarantine"))
        {
            try {
                
                final String uri = "https://api.covidindiatracker.com/state_data.json";
                TotalIndia[] result = restTemplate.getForObject(uri, TotalIndia[].class);

                // System.out.println(result);

                for(int i=0; i < result.length; i++) {
                    if(result[i].getState().equals("Tamil Nadu")) {
                        List<District> allDistrict = result[i].getDistrictData();
                        for (int j = 0; j < allDistrict.size(); j++) { 
                            if(allDistrict.get(j).getId().equals("Airport-Quarantine")) {
                                showDistrictData(allDistrict.get(j), sendMessage, update);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("Railway-Quarantine"))
        {
            try {
                
                final String uri = "https://api.covidindiatracker.com/state_data.json";
                TotalIndia[] result = restTemplate.getForObject(uri, TotalIndia[].class);

                // System.out.println(result);
                
                for(int i=0; i < result.length; i++) {
                    if(result[i].getState().equals("Tamil Nadu")) {
                        List<District> allDistrict = result[i].getDistrictData();
                        for (int j = 0; j < allDistrict.size(); j++) { 
                            if(allDistrict.get(j).getId().equals("Railway-Quarantine")) {
                                showDistrictData(allDistrict.get(j), sendMessage, update);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("The-Nilgiris"))
        {
            try {
                
                final String uri = "https://api.covidindiatracker.com/state_data.json";
                TotalIndia[] result = restTemplate.getForObject(uri, TotalIndia[].class);

                // System.out.println(result);
                
                for(int i=0; i < result.length; i++) {
                    if(result[i].getState().equals("Tamil Nadu")) {
                        List<District> allDistrict = result[i].getDistrictData();
                        for (int j = 0; j < allDistrict.size(); j++) { 
                            if(allDistrict.get(j).getId().equals("The-Nilgiris")) {
                                showDistrictData(allDistrict.get(j), sendMessage, update);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if (update.getMessage().getText().equals("Spring Boot Quote"))
        {
            try
            {

                final String uri = "https://gturnquist-quoters.cfapps.io/api/random";
                SpringQuote result = restTemplate.getForObject(uri, SpringQuote.class);
                String springQuote = result.getValue().getQuote();
                
                // System.out.println(springQuote);

                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Quote : "+ springQuote);
                execute(sendMessage);
            }
            catch (Exception e){ e.printStackTrace();}
        }


        else if (update.getMessage().getText().equals("About me"))
        {
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            List <KeyboardRow> keyboardRowList = new ArrayList<>();
            KeyboardRow row;

            row=new KeyboardRow();
            row.add("LinkedIn");
            row.add("Instagram");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("Facebook");
            row.add("Twitter");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("Medium");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("GitHub");
            keyboardRowList.add(row);

            row=new KeyboardRow();
            row.add("Back");
            keyboardRowList.add(row);

            replyKeyboardMarkup.setKeyboard(keyboardRowList);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);

            try
            {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Hello "+update.getMessage().getFrom().getFirstName()+", \n\nImATechnophile is Telegram Bot developed by " +
                        "https://www.instagram.com/imatechnophile, startup programmers community on the instagram" +
                        " where I am providing useful tips-tricks ,projects and tech content." +
                        "\n\n");
                execute(sendMessage);

            }
            catch (Exception e){ e.printStackTrace();}
        }


        //All Social media and Contact Links

        else if(update.getMessage().getText().equals("LinkedIn"))
        {
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Follow me On LinkedIn");
                execute(sendMessage);
                sendMessage.setText("\n https://www.linkedin.com/in/saravana-prakash");
                execute(sendMessage);

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        

        else if(update.getMessage().getText().equals("Instagram"))
        {
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Follow me On Instagram");
                execute(sendMessage);
                sendMessage.setText("\n https://www.instagram.com/imatechnophile");
                execute(sendMessage);

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


        else if(update.getMessage().getText().equals("Facebook"))
        {
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Like my Facebook page");
                execute(sendMessage);
                sendMessage.setText("\n https://www.facebook.com/ImATechnophile");
                execute(sendMessage);

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("Twitter"))
        {
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Follow me On Twitter");
                execute(sendMessage);
                sendMessage.setText("\n https://twitter.com/cjsaravana95");
                execute(sendMessage);

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("Medium"))
        {
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Follow me On Medium");
                execute(sendMessage);
                sendMessage.setText("\n https://medium.com/@cjsaravana95");
                execute(sendMessage);

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else if(update.getMessage().getText().equals("GitHub"))
        {
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                sendMessage.setText("Follow me On  GitHub");
                execute(sendMessage);
                sendMessage.setText("\n https://github.com/ImATechnophile");
                execute(sendMessage);

            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else
        {

            String matchedDistrict = checkDistrict(update.getMessage().getText());
            if(matchedDistrict.equals("Null")) {
                sendMessage.setText("Hii "+ update.getMessage().getFrom().getFirstName() + " \uD83D\uDE4B\u200D♂️,\n\n" +welcomemessage);
            try {
                sendMessage.setChatId(update.getMessage().getChatId());
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            } else {
                try {
                
                    final String uri = "https://api.covidindiatracker.com/state_data.json";
                    TotalIndia[] result = restTemplate.getForObject(uri, TotalIndia[].class);
    
                    // System.out.println(result);
                    
                    for(int i=0; i < result.length; i++) {
                        if(result[i].getState().equals("Tamil Nadu")) {
                            List<District> allDistrict = result[i].getDistrictData();
                            for (int j = 0; j < allDistrict.size(); j++) { 
                                if(allDistrict.get(j).getId().equals(matchedDistrict)) {
                                    showDistrictData(allDistrict.get(j), sendMessage, update);
                                }
                            }
                        }
                    }
    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showDistrictData(District district, SendMessage sendMessage,Update update ) {
        sendMessage.setText("COVID 19 " + district.getId() + " DATA\n\nTotal cases : "+district.getConfirmed()+
                            // "\nRecovered : "+district.getRecovered()+  
                            // "\nTotal Deaths : "+district.getDeaths()+
                            "\nZone : "+district.getZone());
        try {
            sendMessage.setChatId(update.getMessage().getChatId());
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String checkDistrict(String str) {
        TnDistricts[] allDistricts = TnDistricts.values();
        for(TnDistricts d : allDistricts){
           //Comparing
           if(d.toString().equals(str)){
            // System.out.println("District name - " + d.name());
            return  d.name();
           }
        }
        return "Null";
    }

    @Override
    public String getBotUsername() {
        return "ImATechnophile";
    }
    @Override
    public String getBotToken() {
        return "1267392138:AAHMp0pcqcrvcL2n_w06TsZZfOBHW24-jWA";
    }
}