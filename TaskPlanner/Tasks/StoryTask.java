package TaskPlanner.Tasks;

import TaskPlanner.Task;
import enums.TaskStatus;
import enums.TaskType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// Specific implementation for Story tasks
public class StoryTask extends Task {
    private String storySummary;
    private List<SubTask> subTasks;

    // Constructor with comprehensive initialization
    public StoryTask(String title,
                     String creator,
                     LocalDate dueDate,
                     String storySummary) {
        super(title, creator, TaskType.STORY, dueDate);

        this.storySummary = Objects.requireNonNull(storySummary, "Story summary cannot be null");
        this.subTasks = new ArrayList<>();
    }

    // Specific status transition rules for Story tasks
    @Override
    public boolean isValidStatus(TaskStatus newStatus) {
        TaskStatus currentStatus = getStatus();

        return (currentStatus == TaskStatus.OPEN && newStatus == TaskStatus.IN_PROGRESS) ||
                (currentStatus == TaskStatus.IN_PROGRESS && newStatus == TaskStatus.COMPLETED);
    }

    // Method to add a subtask with validation
    public void addSubTask(SubTask subTask) {
        // Ensure subtask is not added to a completed story
        if (getStatus() == TaskStatus.COMPLETED) {
            throw new IllegalStateException("Cannot add subtask to a completed story");
        }

        Objects.requireNonNull(subTask, "Subtask cannot be null");
        subTasks.add(subTask);
    }

    // Getter for story summary
    public String getStorySummary() { return storySummary; }

    // Immutable list of subtasks
    public List<SubTask> getSubTasks() {
        return Collections.unmodifiableList(subTasks);
    }

    // Check if all subtasks are completed
    public boolean areAllSubTasksCompleted() {
        return subTasks.stream()
                .allMatch(subTask -> subTask.getStatus() == TaskStatus.COMPLETED);
    }
}