package TaskPlanner.Tasks;

import TaskPlanner.Task;
import enums.Impact;
import enums.TaskStatus;
import enums.TaskType;

import java.time.LocalDate;
import java.util.Objects;

// Specific implementation for Feature tasks
public class FeatureTask extends Task {
    private String featureSummary;
    private Impact impact;

    // Constructor with comprehensive initialization
    public FeatureTask(String title,
                       String creator,
                       LocalDate dueDate,
                       String featureSummary,
                       Impact impact) {
        super(title, creator, TaskType.FEATURE, dueDate);

        this.featureSummary = Objects.requireNonNull(featureSummary, "Feature summary cannot be null");
        this.impact = Objects.requireNonNull(impact, "Impact cannot be null");
    }

    // Specific status transition rules for Feature tasks
    @Override
    public boolean isValidStatus(TaskStatus newStatus) {
        TaskStatus currentStatus = getStatus();

        return (currentStatus == TaskStatus.OPEN && newStatus == TaskStatus.IN_PROGRESS) ||
                (currentStatus == TaskStatus.IN_PROGRESS && newStatus == TaskStatus.TESTING) ||
                (currentStatus == TaskStatus.TESTING && newStatus == TaskStatus.DEPLOYED);
    }

    // Getters for feature-specific attributes
    public String getFeatureSummary() { return featureSummary; }
    public Impact getImpact() { return impact; }
}