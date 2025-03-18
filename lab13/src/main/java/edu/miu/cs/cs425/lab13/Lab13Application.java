package edu.miu.cs.cs425.lab13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Lab13Application {

    public static void main(String[] args) {
        System.out.println("\n=== Starting Lab13Application ===");
        System.out.println("Launch time: " + java.time.LocalDateTime.now());
        System.out.println("Arguments: " + (args.length > 0 ? String.join(", ", args) : "None provided"));

        try {
            ApplicationContext context = SpringApplication.run(Lab13Application.class, args);

            System.out.println("Application started successfully");
            System.out.println("Active profiles: " +
                    String.join(", ", context.getEnvironment().getActiveProfiles()));
            System.out.println("Bean count: " + context.getBeanDefinitionCount());
            System.out.println("Status: RUNNING");
        } catch (Exception e) {
            System.err.println("Application failed to start");
            System.err.println("Error: " + e.getMessage());
            System.err.println("Stack trace: ");
            e.printStackTrace(System.err);
            System.err.println("Status: FAILED");
        }

        System.out.println("=== Lab13Application Startup Complete ===\n");
    }
}