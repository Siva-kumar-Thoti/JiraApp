package TaskPlanner.Sprint;

import TaskPlanner.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sprint {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Task> tasks;

    // Constructor
    public Sprint(String name, LocalDate startDate, LocalDate endDate) {
        this.name = Objects.requireNonNull(name, "Sprint name cannot be null");
        this.startDate = Objects.requireNonNull(startDate, "Start date cannot be null");
        this.endDate = Objects.requireNonNull(endDate, "End date cannot be null");

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        this.tasks = new ArrayList<>();
    }

    // Add a task to the sprint
    public void addTask(Task task) {
        Objects.requireNonNull(task, "Task cannot be null");

        // Ensure task is not already in another sprint
        if (task.getSprint() != null) {
            throw new IllegalStateException("Task is already assigned to a sprint");
        }

        tasks.add(task);
        task.setSprint(this);
    }

    // Remove a task from the sprint
    public void removeTask(Task task) {
        Objects.requireNonNull(task, "Task cannot be null");

        if (tasks.remove(task)) {
            task.setSprint(null);
        }
    }

    public String getName() { return name; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public List<Task> getTasks() { return new ArrayList<>(tasks); }
}
