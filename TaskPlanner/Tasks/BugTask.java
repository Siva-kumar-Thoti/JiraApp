package TaskPlanner.Tasks;

import TaskPlanner.Task;
import enums.Severity;
import enums.TaskStatus;
import enums.TaskType;

import java.time.LocalDate;
import java.util.Objects;

// Specific implementation for Bug tasks
public class BugTask extends Task {
    private Severity severity;
    private String bugDescription;

    // Constructor with comprehensive initialization
    public BugTask(String title,
                   String creator,
                   LocalDate dueDate,
                   Severity severity,
                   String bugDescription) {
        super(title, creator, TaskType.BUG, dueDate);

        this.severity = Objects.requireNonNull(severity, "Bug severity cannot be null");
        this.bugDescription = Objects.requireNonNull(bugDescription, "Bug description cannot be null");
    }

    // Specific status transition rules for Bug tasks
    @Override
    public boolean isValidStatus(TaskStatus newStatus) {
        TaskStatus currentStatus = getStatus();

        return (currentStatus == TaskStatus.OPEN && newStatus == TaskStatus.IN_PROGRESS) ||
                (currentStatus == TaskStatus.IN_PROGRESS && newStatus == TaskStatus.FIXED);
    }

    // Getters for bug-specific attributes
    public Severity getSeverity() { return severity; }
    public String getBugDescription() { return bugDescription; }
}