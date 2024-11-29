package TaskPlanner;

import TaskPlanner.Tasks.BugTask;
import TaskPlanner.Tasks.FeatureTask;
import TaskPlanner.Tasks.StoryTask;
import TaskPlanner.Tasks.SubTask;
import enums.Impact;
import enums.Severity;
import enums.TaskType;

import java.time.LocalDate;
import java.util.Objects;

// Task Factory to centralize task creation
public class TaskFactory {
    // Static factory method for Feature Tasks
    public static FeatureTask createFeatureTask(
            String title,
            String creator,
            LocalDate dueDate,
            String featureSummary,
            Impact impact
    ) {
        validateCommonTaskParams(title, creator, dueDate);

        return new FeatureTask(
                title,
                creator,
                dueDate,
                featureSummary,
                impact
        );
    }

    // Static factory method for Bug Tasks
    public static BugTask createBugTask(
            String title,
            String creator,
            LocalDate dueDate,
            Severity severity,
            String bugDescription
    ) {
        validateCommonTaskParams(title, creator, dueDate);

        return new BugTask(
                title,
                creator,
                dueDate,
                severity,
                bugDescription
        );
    }

    // Static factory method for Story Tasks
    public static StoryTask createStoryTask(
            String title,
            String creator,
            LocalDate dueDate,
            String storySummary
    ) {
        validateCommonTaskParams(title, creator, dueDate);

        return new StoryTask(
                title,
                creator,
                dueDate,
                storySummary
        );
    }

    // Static factory method for SubTasks
    public static SubTask createSubTask(
            String title,
            StoryTask parentStory
    ) {
        // Validate title and parent story
        Objects.requireNonNull(title, "Subtask title cannot be null");
        Objects.requireNonNull(parentStory, "Parent story cannot be null");

        return new SubTask(title, parentStory);
    }

    // Common validation for task creation
    private static void validateCommonTaskParams(
            String title,
            String creator,
            LocalDate dueDate
    ) {
        // Null checks
        Objects.requireNonNull(title, "Task title cannot be null");
        Objects.requireNonNull(creator, "Task creator cannot be null");
        Objects.requireNonNull(dueDate, "Task due date cannot be null");

        // Additional validation rules can be added here
        if (title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }

        if (creator.trim().isEmpty()) {
            throw new IllegalArgumentException("Task creator cannot be empty");
        }

        // Ensure due date is in the future
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Due date must be in the future");
        }
    }

    // Builder pattern for more complex task creation
    public static class TaskBuilder {
        private String title;
        private String creator;
        private LocalDate dueDate;
        private TaskType taskType;

        // Fluent interface for builder
        public TaskBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder creator(String creator) {
            this.creator = creator;
            return this;
        }

        public TaskBuilder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public TaskBuilder type(TaskType taskType) {
            this.taskType = taskType;
            return this;
        }

        // Build method with type-specific creation
        public Task build() {
            validateCommonTaskParams(title, creator, dueDate);

            switch (taskType) {
                case FEATURE:
                    return new FeatureTask(
                            title,
                            creator,
                            dueDate,
                            "Default Feature",
                            Impact.LOW
                    );
                case BUG:
                    return new BugTask(
                            title,
                            creator,
                            dueDate,
                            Severity.P2,
                            "Default Bug Description"
                    );
                case STORY:
                    return new StoryTask(
                            title,
                            creator,
                            dueDate,
                            "Default Story"
                    );
                default:
                    throw new IllegalArgumentException("Unsupported task type");
            }
        }
    }

    // Static method to get a new builder
    public static TaskBuilder builder() {
        return new TaskBuilder();
    }
}