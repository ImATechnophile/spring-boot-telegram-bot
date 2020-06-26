package com.imatechnophile.telegrambot.models.covid19;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalIndia {
    private String id;
    private String state;
    private Long active;
    private Long confirmed;
    private Long recovered;
    private Long deaths;
    private Long aChanges;
    private Long cChanges;
    private Long rChanges;
    private Long dChanges;
    private List<District> districtData;
    // private Long cchanges;
    // private Long dchanges;
    // private Long rchanges;
    // private Long achanges;

    public TotalIndia() {}

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

    public Long getActive() {
        return active;
    }
    public void setActive(Long active){
        this.active = active;
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

    public Long getAChanges() {
        return aChanges;
    }
    public void setAChanges(Long aChanges){
        this.aChanges = aChanges;
    }

    public Long getCChanges() {
        return cChanges;
    }
    public void setCChanges(Long cChanges){
        this.cChanges = cChanges;
    }

    public Long getRChanges() {
        return rChanges;
    }
    public void setRChanges(Long rChanges){
        this.rChanges = rChanges;
    }

    public Long getDChanges() {
        return dChanges;
    }
    public void setDChanges(Long dChanges){
        this.dChanges = dChanges;
    }

    public List<District> getDistrictData() {
        return districtData;
    }
    public void setDistrictData(List<District> districtData){
        this.districtData = districtData;
    }

    // public Long getCchanges() {
    //     return cchanges;
    // }
    // public void setCchanges(Long cchanges){
    //     this.cchanges = cchanges;
    // }

    // public Long getDchanges() {
    //     return dchanges;
    // }
    // public void setDchanges(Long dchanges){
    //     this.dchanges = dchanges;
    // }

    // public Long getRchanges() {
    //     return rchanges;
    // }
    // public void setRchanges(Long rchanges){
    //     this.rchanges = rchanges;
    // }

    // public Long getAchanges() {
    //     return achanges;
    // }
    // public void setAchanges(Long achanges){
    //     this.achanges = achanges;
    // }
       
}