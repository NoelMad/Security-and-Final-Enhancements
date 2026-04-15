package edu.brooklyn.cisc3130.taskboard.controller;

import edu.brooklyn.cisc3130.taskboard.dto.TaskRequest;
import edu.brooklyn.cisc3130.taskboard.dto.TaskResponse;
import edu.brooklyn.cisc3130.taskboard.model.Task;
import edu.brooklyn.cisc3130.taskboard.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET ALL (DTO)
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks()
                .stream()
                .map(TaskResponse::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(tasks);
    }

    // GET BY ID (DTO + Exception-based)
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Integer id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(TaskResponse.fromEntity(task));
    }

    // CREATE (DTO)
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest taskRequest) {

        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(
                taskRequest.getCompleted() != null ? taskRequest.getCompleted() : false
        );
        task.setPriority(Task.Priority.valueOf(
                taskRequest.getPriority() != null
                        ? taskRequest.getPriority().toUpperCase()
                        : "MEDIUM"
        ));

        Task createdTask = taskService.createTask(task);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TaskResponse.fromEntity(createdTask));
    }

    // UPDATE (DTO)
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Integer id,
            @Valid @RequestBody TaskRequest taskRequest) {

        Task updated = new Task();
        updated.setTitle(taskRequest.getTitle());
        updated.setDescription(taskRequest.getDescription());
        updated.setCompleted(taskRequest.getCompleted());
        updated.setPriority(Task.Priority.valueOf(taskRequest.getPriority().toUpperCase()));

        Task savedTask = taskService.updateTask(id, updated);

        return ResponseEntity.ok(TaskResponse.fromEntity(savedTask));
    }

    // DELETE (no boolean anymore)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id); // throws if not found
        return ResponseEntity.noContent().build();
    }

    // COMPLETED TASKS
    @GetMapping("/completed")
    public ResponseEntity<List<TaskResponse>> getCompletedTasks() {
        List<TaskResponse> tasks = taskService.getCompletedTasks()
                .stream()
                .map(TaskResponse::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(tasks);
    }

    // INCOMPLETE TASKS
    @GetMapping("/incomplete")
    public ResponseEntity<List<TaskResponse>> getIncompleteTasks() {
        List<TaskResponse> tasks = taskService.getIncompleteTasks()
                .stream()
                .map(TaskResponse::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(tasks);
    }

    // PRIORITY FILTER
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponse>> getTasksByPriority(
            @PathVariable String priority) {

        Task.Priority priorityEnum = Task.Priority.valueOf(priority.toUpperCase());

        List<TaskResponse> tasks = taskService.getTasksByPriority(priorityEnum)
                .stream()
                .map(TaskResponse::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(tasks);
    }

    // SEARCH
    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> searchTasks(@RequestParam String keyword) {
        List<TaskResponse> tasks = taskService.searchTasks(keyword)
                .stream()
                .map(TaskResponse::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(tasks);
    }

    // PAGINATION (DTO)
    @GetMapping("/paginated")
    public ResponseEntity<Page<TaskResponse>> getTasksPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<TaskResponse> tasks = taskService.getAllTasks(pageable)
                .map(TaskResponse::fromEntity);

        return ResponseEntity.ok(tasks);
    }

    // VALIDATION ERROR HANDLER
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}