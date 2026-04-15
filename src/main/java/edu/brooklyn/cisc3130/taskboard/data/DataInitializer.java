package edu.brooklyn.cisc3130.taskboard.data;

import edu.brooklyn.cisc3130.taskboard.model.Task;
import edu.brooklyn.cisc3130.taskboard.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final TaskRepository taskRepository;
    
    public DataInitializer(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    @Override
    public void run(String... args) {
        if (taskRepository.count() == 0) {

            Task task1 = new Task();
            task1.setTitle("Complete Homework 6");
            task1.setDescription("Finish Spring Data JPA assignment");
            task1.setCompleted(false);
            task1.setPriority(Task.Priority.HIGH);
            task1.setDeleted(false);

            Task task2 = new Task();
            task2.setTitle("Study for Midterm");
            task2.setDescription("Review chapters 1-5");
            task2.setCompleted(false);
            task2.setPriority(Task.Priority.HIGH);
            task2.setDeleted(false);

            Task task3 = new Task();
            task3.setTitle("Buy groceries");
            task3.setDescription("Milk, eggs, bread");
            task3.setCompleted(true);
            task3.setPriority(Task.Priority.LOW);
            task3.setDeleted(false);

            Task task4 = new Task();
            task4.setTitle("Workout");
            task4.setDescription("Go to the gym for 1 hour");
            task4.setCompleted(false);
            task4.setPriority(Task.Priority.MEDIUM);
            task4.setDeleted(false);

            Task task5 = new Task();
            task5.setTitle("Read book");
            task5.setDescription("Read 30 pages of a novel");
            task5.setCompleted(false);
            task5.setPriority(Task.Priority.LOW);
            task5.setDeleted(false);

            taskRepository.save(task1);
            taskRepository.save(task2);
            taskRepository.save(task3);
            taskRepository.save(task4);
            taskRepository.save(task5);
        }
    }
}