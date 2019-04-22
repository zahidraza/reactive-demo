package com.jazasoft.demo.restcontroller;

import com.jazasoft.demo.model.Task;
import com.jazasoft.demo.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {

    private TaskRepository taskRepository;

    public TaskRestController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public Flux<Task> getTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Task>> getTask(@PathVariable String id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Task>> saveTask(@RequestBody Task task) {
        return taskRepository.save(task).map(t -> new ResponseEntity<>(t, HttpStatus.CREATED));
    }

    @PutMapping("/{taskId}")
    public Mono<ResponseEntity<Task>> updateTask(@PathVariable(value = "taskId") String id, @RequestBody Task task) {
        return taskRepository.findById(id)
                .flatMap(existingTask -> {
                    existingTask.setName(task.getName());
                    existingTask.setDesc(task.getDesc());
                    return taskRepository.save(existingTask);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{taskId}")
    public Mono<ResponseEntity<Void>> deleteTask(@PathVariable(value = "taskId") String id) {
        return taskRepository.findById(id)
                .flatMap(existingTask ->
                        taskRepository.delete(existingTask)
                                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
