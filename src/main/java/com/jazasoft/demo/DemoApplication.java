package com.jazasoft.demo;

import com.jazasoft.demo.model.Task;
import com.jazasoft.demo.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

//        GreetingWebClient gwc = new GreetingWebClient();
//        System.out.println(gwc.getResult());
    }


//    @Bean
//    CommandLineRunner init(ReactiveMongoOperations operations, TaskRepository taskRepository) {
//        return args -> {
//            Flux<Task> productFlux = Flux.just(
//                    new Task(null, "Go Shopping", "Desc 1"),
//                    new Task(null, "Pay Electricity Bill", "Desc 2")
//            )
//                    .flatMap(taskRepository::save);
//
//            productFlux
//                    .thenMany(taskRepository.findAll())
//                    .subscribe(System.out::println);
//        };
//    }


}
