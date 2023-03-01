package org.anwang.safe.server.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"org.anwang.safe.server.framework","org.anwang.safe.server.monitor"})
@ServletComponentScan("org.anwang.safe.server.monitor")
@EnableScheduling
@EnableAsync
public class MonitorStartUp {

    public static void main(String[] args) {
        SpringApplication.run(MonitorStartUp.class, args);
    }

}
