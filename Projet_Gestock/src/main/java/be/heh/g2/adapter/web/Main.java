package be.heh.g2.adapter.web;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        SpringApplication.run(Main.class, args);
    }
}