package com.ibdata.eventsourcing.acc.resolution.controller;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.TimeZone;

public class DemoTests {

    @Test
    public void noting() {


        LocalDateTime now = LocalDateTime.now();

        System.out.println(now.getYear());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getMonthValue());

        System.out.println(LocalDateTime.now().toString());



        String str = "0000123";
        String result = "";

        System.out.println(StringUtils.leftPad(Integer.parseInt(str) + "", 6, '0'));
    }
}
