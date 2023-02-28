package org.anwang.safe.server.syncprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"org.anwang.safe.server.framework","org.anwang.safe.server.syncprice"})
@ServletComponentScan("org.anwang.safe.server.syncprice")
@EnableScheduling
@EnableAsync
public class SyncPriceStartUp {

    public static void main(String[] args) {
        SpringApplication.run(SyncPriceStartUp.class, args);
    }

}
