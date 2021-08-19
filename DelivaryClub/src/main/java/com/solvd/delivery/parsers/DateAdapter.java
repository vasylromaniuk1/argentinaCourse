package com.solvd.delivery.parsers;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateAdapter extends XmlAdapter<String, LocalDate> {
    private final static Logger LOGGER = LogManager.getLogger(DateAdapter.class);

    @Override
    public LocalDate unmarshal(String s) throws Exception {
        Calendar calendar = null;
        try {

            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(s);
            calendar = new GregorianCalendar();
            calendar.setTime(date);
        } catch (Exception e) {
            throw new Exception("can't parse date format ", e);
        }


        return LocalDate.ofInstant(calendar.getTime().toInstant(), ZoneId.systemDefault());
    }
    @Override
    public String marshal(LocalDate localDate) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString()));
        return new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime());
    }


}
