package edu.brooklyn.cisc3130.taskboard.service;

import edu.brooklyn.cisc3130.taskboard.exception.TaskNotFoundException;
import edu.brooklyn.cisc3130.taskboard.model.Task;
import edu.brooklyn.cisc3130.taskboard.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Get all non-deleted tasks
    public List<Task> getAllTasks() {
        return taskRepository.findByDeletedFalse();
    }

    // Paginated tasks (non-deleted)
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findByDeletedFalse(pageable);
    }

    // Get task by ID (throws exception if not found)
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    // Create task with defaults
    public Task createTask(Task task) {
        if (task.getCompleted() == null) {
            task.setCompleted(false);
        }
        if (task.getPriority() == null) {
            task.setPriority(Task.Priority.MEDIUM);
        }
        task.setDeleted(false);
        return taskRepository.save(task);
    }

    // Update task
    public Task updateTask(Integer id, Task updatedTask) {
        Task task = getTaskById(id);

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.getCompleted());
        task.setPriority(updatedTask.getPriority());

        return taskRepository.save(task);
    }

    // Soft delete
    public void deleteTask(Integer id) {
        Task task = getTaskById(id);
        task.setDeleted(true);
        taskRepository.save(task);
    }

    // Restore soft-deleted task
    public void restoreTask(Integer id) {
        Task task = getTaskById(id);
        task.setDeleted(false);
        taskRepository.save(task);
    }

    // Completed tasks
    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompletedTrue();
    }

    // Incomplete tasks
    public List<Task> getIncompleteTasks() {
        return taskRepository.findByCompletedFalse();
    }

    // Filter by priority
    public List<Task> getTasksByPriority(Task.Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    // Search tasks
    public List<Task> searchTasks(String keyword) {
        return taskRepository.searchTasks(keyword);
    }

    // Paginated completed tasks
    public Page<Task> getCompletedTasks(Pageable pageable) {
        return taskRepository.findByCompletedTrue(pageable);
    }
}