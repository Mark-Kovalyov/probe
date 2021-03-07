package org.example;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

@Measurement(name = "temperature")
public class Temperature {

    @Column(tag = true)
    String location;

    @Column
    Double value;

    @Column(timestamp = true)
    Instant time;

}
