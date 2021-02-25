package com.ibdata.eventsourcing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.ibdata.eventsourcing.acc.resolution.mapper"})
public class EventsourcingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsourcingApplication.class, args);
    }

}
