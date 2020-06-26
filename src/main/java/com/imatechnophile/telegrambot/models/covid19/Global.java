package com.imatechnophile.telegrambot.models.covid19;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Global {
    private Long updated;
    private Long cases;
    private Long todayCases;
    private Long deaths;
    private Long todayDeaths;
    private Long recovered;
    private Long todayRecovered;
    private Long active;
    private Long critical;
    private Long casesPerOneMillion;
    private Long deathsPerOneMillion;
    private Long tests;
    private Long testsPerOneMillion;
    private Long population;
    private Long oneCasePerPeople;
    private Long oneDeathPerPeople;
    private Long oneTestPerPeople;
    private Long activePerOneMillion;
    private Long recoveredPerOneMillion;
    private Long criticalPerOneMillion;
    private Long affectedCountries;
}