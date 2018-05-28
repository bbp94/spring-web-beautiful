package com.bxp.web.springweb.train;

/**
 * @Author: bbp(xiuping bao) on 2018/5/24.
 * @Date: 2018/5/24 9:32
 */
public class Train {
    private int ID;
    private String origin_station;
    private String terminal_station;
    private String intermediate_station;
    private String distance_to_origin;
    private String tickets_left;
    private String date;
    private String number;
    private String time;
    private String origin_time;

    public String getOrigin_time() {
        return origin_time;
    }

    public void setOrigin_time(String origin_time) {
        this.origin_time = origin_time;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrigin_station() {
        return origin_station;
    }

    public void setOrigin_station(String origin) {
        this.origin_station = origin;
    }

    public String getTerminal_station() {
        return terminal_station;
    }

    public void setTerminal_station(String terminal) {
        this.terminal_station = terminal;
    }

    public String getIntermediate_station() {
        return intermediate_station;
    }

    public void setIntermediate_station(String intermediate) {
        this.intermediate_station = intermediate;
    }

    public String getDistance_to_origin() {
        return distance_to_origin;
    }

    public void setDistance_to_origin(String distance) {
        this.distance_to_origin = distance;
    }

    public String getTickets_left() {
        return tickets_left;
    }

    public void setTickets_left(String ticket) {
        this.tickets_left = ticket;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
