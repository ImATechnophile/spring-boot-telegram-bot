package com.imatechnophile.telegrambot.models.covid19;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class District {
    private String id;
    private String state;
    private String name;
    private Long confirmed;
    private Long recovered;
    private Long deaths;
    private Long oldConfirmed;
    private Long oldRecovered;
    private Long oldDeaths;
    private String zone;

    public District(){}

    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getState() {
        return state;
    }
    public void setState(String state){
        this.state = state;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Long getConfirmed() {
        return confirmed;
    }
    public void setConfirmed(Long confirmed){
        this.confirmed = confirmed;
    }

    public Long getRecovered() {
        return recovered;
    }
    public void setRecovered(Long recovered){
        this.recovered = recovered;
    }

    public Long getDeaths() {
        return deaths;
    }
    public void setDeaths(Long deaths){
        this.deaths = deaths;
    }

    public Long getOldConfirmed() {
        return oldConfirmed;
    }
    public void setOldConfirmed(Long oldConfirmed){
        this.oldConfirmed = oldConfirmed;
    }

    public Long getOldRecovered() {
        return oldRecovered;
    }
    public void setoldRecovered(Long oldRecovered){
        this.oldRecovered = oldRecovered;
    }

    public Long getOldDeaths() {
        return oldDeaths;
    }
    public void setOldDeaths(Long oldDeaths){
        this.oldDeaths = oldDeaths;
    }

    public String getZone() {
        return zone;
    }
    public void setZone(String zone){
        this.zone = zone;
    }
}