package TaskPlanner;

import TaskPlanner.Sprint.Sprint;
import enums.TaskStatus;
import enums.TaskType;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Task {
    private String title;
    private String creator;
    private String assignee;
    private TaskStatus status;
    private TaskType type;
    private LocalDate dueDate;
    private Sprint sprint;

    // Constructor for creating a new task
    public Task(String title,
                String creator,
                TaskType type,
                LocalDate dueDate) {
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.creator = Objects.requireNonNull(creator, "Creator cannot be null");
        this.type = Objects.requireNonNull(type, "Task type cannot be null");
        this.dueDate = Objects.requireNonNull(dueDate, "Due date cannot be null");
        this.status = TaskStatus.OPEN; // Default initial status
    }

    // Abstract method to validate status transitions
    public abstract boolean isValidStatus(TaskStatus newStatus);

    // Method to update task status with validation
    public void updateStatus(TaskStatus newStatus) {
        if (isValidStatus(newStatus)) {
            this.status = newStatus;
        } else {
            throw new IllegalStateException("Invalid status transition for this task type");
        }
    }

    // Getters and setters with basic validation
    public String getTitle() { return title; }
    public String getCreator() { return creator; }
    public String getAssignee() { return assignee; }

    public void setAssignee(String assignee) {
        this.assignee = Objects.requireNonNull(assignee, "Assignee cannot be null");
    }

    public TaskStatus getStatus() { return status; }
    public TaskType getType() { return type; }
    public LocalDate getDueDate() { return dueDate; }
    public Sprint getSprint() { return sprint; }

    public void setSprint(Sprint sprint) {
        this.sprint = Objects.requireNonNull(sprint, "Sprint cannot be null");
    }
}