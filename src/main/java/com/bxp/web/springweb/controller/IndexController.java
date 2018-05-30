package com.bxp.web.springweb.controller;
import com.alibaba.fastjson.JSON;
import com.bxp.web.springweb.dao.TicketDao;
import com.bxp.web.springweb.dao.UserDao;
import com.bxp.web.springweb.train.MyDate;
import com.bxp.web.springweb.train.Train;
import com.bxp.web.springweb.train.UserInfo;
import com.google.common.collect.Lists;

import org.apache.catalina.connector.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.collections4.CollectionUtils;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;


@Controller
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private UserDao userDao;

    public IndexController() {
    }

    @PostConstruct
    private void init(){
        if(ticketDao == null){
            LOGGER.info("TickDao is null");
        }
    }
    @RequestMapping("/hello")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/show")
    public String show(String name){
        return "show";
    }

    @RequestMapping(value = "/load")
    @ResponseBody
    public String load(String id,String password) {
//        String value = LocalDateTime.now().toString();
        UserInfo userInfo = userDao.getUserInfo(id);
        LOGGER.info("Check user info id:"+ id);
        if(userInfo==null || id==null){
            return "id wrong";
        }else if(!password.equals(userInfo.getPassword()) || password==null){
            return "password wrong";
        }else{
            System.out.println(userInfo.getname());
            String temp = "";
            try {
                temp = URLEncoder.encode(URLEncoder.encode(userInfo.getname(),"UTF-8"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }finally {
                return temp;
            }

//            return userInfo.getname();
        }

    }


    @RequestMapping(value = "/ticket/all")
    @ResponseBody
    public String getAllTickets(){
        List<Train> tickets = ticketDao.getAllTrain();
        if(CollectionUtils.isEmpty(tickets)){
            tickets = Lists.newArrayList();
        }
        LOGGER.info("Get all train info {}", StringUtils.join(tickets));
        return JSON.toJSONString(tickets);
    }

    @RequestMapping(value = "/ticket/some")
    @ResponseBody
    public String getSomeTickets(String date,String origin,String terminal){
        float pricePer = 0.2f;

//        List<Train> tick = ticketDao.getAllTrain();
        date = date.split("/")[2]+date.split("/")[1]+date.split("/")[0];
        System.out.println(date);
        List<Train> allTrain = ticketDao.getAllTrainByDate(date);
        String intermediate = "";
        ArrayList<List> tickets_info = new ArrayList<List>();
        for(Train trainDate : allTrain){
            intermediate = trainDate.getIntermediate_station();
            System.out.println(intermediate.contains(origin)+" " + intermediate.contains(terminal) + "  "+intermediate.indexOf(origin)+"  "+intermediate.indexOf(terminal));
            if (intermediate.indexOf(origin)!=-1 && intermediate.indexOf(terminal)!=-1
                    && intermediate.indexOf(origin)<intermediate.indexOf(terminal)){
                LOGGER.info("Get select train info {}", StringUtils.join(trainDate));

                ArrayList<String> temp = new ArrayList<String>();

                List<String> intermediateArray = Arrays.asList(intermediate.split(";"));
                List<String> ticketLeftTemp = Arrays.asList(trainDate.getTickets_left().split(";"));
                List<String> timeTemp = Arrays.asList(trainDate.getTime().split(";"));
                List<String> distanceTemp = Arrays.asList(trainDate.getDistance_to_origin().split(";"));
                int startIndex = intermediateArray.indexOf(origin);
                int endIndex = intermediateArray.indexOf(terminal);
                String ticketLeft = ticketLeftTemp.get(endIndex);
                int endTime = Integer.valueOf(timeTemp.get(endIndex));
                int distance = Integer.valueOf(distanceTemp.get(endIndex)) - Integer.valueOf(distanceTemp.get(startIndex));
                int price = (int)(distance * pricePer);
                int startTime = Integer.valueOf(timeTemp.get(startIndex));
                MyDate arrive = new MyDate();

                String time = date+trainDate.getOrigin_time();
                System.out.println("time: " + time);
                String start = arrive.arriveDate(time,startTime);
                System.out.println("start: " + start);
                temp.add(start);

                temp.add(trainDate.getNumber());
                try {
                    temp.add(URLEncoder.encode(origin,"UTF-8"));
                    temp.add(URLEncoder.encode(terminal,"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

//                MyDate arrive = new MyDate();
                System.out.println("time: " +time +"  endtime:"+endTime);
                temp.add(arrive.arriveDate(time,endTime));
                temp.add(ticketLeft);
                temp.add(String.valueOf(price));
//            temp.add(String.valueOf(train.getID()));
                tickets_info.add(temp);
            }
        }

        return JSON.toJSONString(tickets_info);
    }

    @RequestMapping(value = "/buy")
    public String buy(){

        return "buy";
    }
    @RequestMapping("/buy/check")
    @ResponseBody
    public String check(String date,String number,String origin,String terminal,String n,String name){
        String tempDate = date.split(" ")[0].replace(".","");
        Train train = ticketDao.getATrain(tempDate,number);
        System.out.println(train.getIntermediate_station());
        System.out.println(train.getIntermediate_station().split(";")[8]);
        int index = Arrays.asList(train.getIntermediate_station().split(";")).indexOf(terminal);
        String[] temp = train.getTickets_left().split(";");
        int newLeft = Integer.valueOf(temp[index])-Integer.valueOf(n);
        temp[index] = String.valueOf(newLeft);
        String left="";
        for(int i=0;i<temp.length;i++){
            if(i==0){
                left = left+temp[i];
            }else{
                left = left+";"+temp[i];
            }

        }
        ticketDao.updateLeft(left,tempDate,number);

        return JSON.toJSONString("");
    }
}
