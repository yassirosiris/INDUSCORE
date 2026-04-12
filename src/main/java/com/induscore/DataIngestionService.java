package com.induscore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ConcurrentHashMap;
@SpringBootApplication
@RestController
@RequestMapping("/api/sensors")
public class DataIngestionService {
    private ConcurrentHashMap<String, Double> sensorData = new
    ConcurrentHashMap<>();
    public static void main(String[] args) {
        SpringApplication.run(DataIngestionService.class, args);
    }
    @PostMapping("/{sensorId}")
    public void receiveSensorData(@PathVariable String sensorId,
        @RequestBody Double value) {
        sensorData.put(sensorId, value);
    System.out.println("Received data from " + sensorId + ": " + value);
    }
    @GetMapping("/{sensorId}")
    public Double getSensorData(@PathVariable String sensorId) {
        return sensorData.get(sensorId);
    }
    
}
