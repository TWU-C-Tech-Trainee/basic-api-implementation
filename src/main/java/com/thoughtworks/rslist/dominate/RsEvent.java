package com.thoughtworks.rslist.dominate;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



import javax.validation.Valid;

public class RsEvent {
    private String eventName;
    private String keyWord;

    @Valid
    private UserDetiles user;

    public  RsEvent(String eventName, String keyWord,UserDetiles user){
        this.eventName = eventName;
        this.keyWord = keyWord;
        this.user = user;
    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @JsonIgnore
    public UserDetiles getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(UserDetiles user) {
        this.user = user;
    }
}
