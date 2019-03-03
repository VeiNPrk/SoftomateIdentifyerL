package com.example.softomateidentifyerl;


public class MessageEvent {

    public final String message;
    public static final String EVENT_UPDATE_HISTORY="update_history";

    public MessageEvent(String message) {
        this.message = message;
    }

}
