package TaskPlanner.Tasks;

import enums.TaskStatus;

import java.util.Objects;

// Specific implementation for SubTasks
public class SubTask {
    private String title;
    private TaskStatus status;
    private StoryTask parentStory;

    // Constructor with comprehensive initialization
    public SubTask(String title, StoryTask parentStory) {
        this.title = Objects.requireNonNull(title, "Subtask title cannot be null");
        this.parentStory = Objects.requireNonNull(parentStory, "Parent story cannot be null");
        this.status = TaskStatus.OPEN; // Default initial status

        // Add this subtask to the parent story
        parentStory.addSubTask(this);
    }

    // Method to update status with validation
    public void updateStatus(TaskStatus newStatus) {
        // Validate status transition
        if (isValidStatus(newStatus)) {
            this.status = newStatus;

            // If all subtasks are completed, potentially mark parent story as completed
            if (newStatus == TaskStatus.COMPLETED &&
                    parentStory.areAllSubTasksCompleted()) {
                parentStory.updateStatus(TaskStatus.COMPLETED);
            }
        } else {
            throw new IllegalStateException("Invalid status transition for subtask");
        }
    }

    // Validate status transitions
    private boolean isValidStatus(TaskStatus newStatus) {
        TaskStatus currentStatus = this.status;

        return (currentStatus == TaskStatus.OPEN && newStatus == TaskStatus.IN_PROGRESS) ||
                (currentStatus == TaskStatus.IN_PROGRESS && newStatus == TaskStatus.COMPLETED);
    }

    // Getters
    public String getTitle() { return title; }
    public TaskStatus getStatus() { return status; }
    public StoryTask getParentStory() { return parentStory; }
}