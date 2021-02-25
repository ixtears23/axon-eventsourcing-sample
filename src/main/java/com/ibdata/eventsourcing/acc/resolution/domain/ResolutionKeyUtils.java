package com.ibdata.eventsourcing.acc.resolution.domain;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

public class ResolutionKeyUtils {

    public static String generateResolutionNumber(String maxId) {

        String resolutionNumber = "000001";

        if (StringUtils.isNotBlank(maxId)) {
            resolutionNumber = StringUtils.leftPad(Integer.parseInt(maxId) + "", 6, '0');
        }
        return resolutionNumber;
    }

    public static String generateResolutionId(String maxId) {
        return getToday() + generateResolutionNumber(maxId);
    }

    public static String getToday() {
        LocalDateTime now = LocalDateTime.now();
        String year = now.getYear() + "";
        String month = now.getMonthValue() + "";
        String day = now.getDayOfMonth() + "";
        return year + month + day;
    }
}
