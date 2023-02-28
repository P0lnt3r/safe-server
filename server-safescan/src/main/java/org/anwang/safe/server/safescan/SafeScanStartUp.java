package org.anwang.safe.server.safescan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"org.anwang.safe.server.framework","org.anwang.safe.server.safescan"})
@ServletComponentScan("org.anwang.safe.server.safescan")
@EnableScheduling
@EnableAsync
public class SafeScanStartUp {

    public static void main(String[] args) {
        SpringApplication.run(SafeScanStartUp.class, args);
    }

}
