package com.induscore;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class DataProcessingService {
    @KafkaListener(topics = "sensor-data", groupId = "data-processors")
    public void processData(String sensorData) {
        System.out.println("Processing sensor data: " + sensorData);
// Implement data analysis logic here
    }
}