package com.deutsche.transaction.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class utils {

    public static ZoneId zoneId = ZoneId.of("America/Lima");
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static String getFormatDate(LocalDateTime ldt){
        //LocalDateTime ldt = LocalDateTime.now();
        return ldt.atZone(zoneId).format(formatter);
    }

    public static Long getMilliSeconds(LocalDateTime ldt){
        return ldt.atZone(zoneId).toInstant().toEpochMilli();
    }

    public static Long getMilliMinimun(){
        Date dateNow = new Date();
        long milliNow = dateNow.getTime();
        return milliNow - 86400000;  //substract; 86400000 (milliseconds) = 24 (hours)
    }

    public static Long getSecondsPassed(String dateTrx) {
        Date ahora = new Date();
        long miliAhora = ahora.getTime();
        //String transactionTime = getDate();

        try {
            Date parsedDate = dateFormat.parse(dateTrx);
            long miliTrx = parsedDate.getTime();
            /*System.out.println("emg120: " + dateFormat.format(parsedDate));
            System.out.println("emg130: " + miliAhora);
            System.out.println("emg140: " + miliTrx);*/
            long resta = miliAhora - miliTrx;
            //System.out.println("emg150: " + TimeUnit.MILLISECONDS.toHours(resta));
            //return TimeUnit.MILLISECONDS.toHours(resta);
            return TimeUnit.MILLISECONDS.toSeconds(resta);
        } catch (ParseException e) {
            System.out.println("Error parsing the date: " + e.getMessage());
        }
        return null;
    }


}
