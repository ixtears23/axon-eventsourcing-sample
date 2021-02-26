package com.ibdata.eventsourcing.acc.resolution.aggregate;

import org.springframework.stereotype.Service;

@Service
public class DummyService {

    public String printMyName() {
        return this.toString();
    }
}
