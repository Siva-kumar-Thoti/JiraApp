package enums;

// Defines possible task statuses with clear progression
public enum TaskStatus {
    OPEN,           // Initial state of a task
    IN_PROGRESS,    // Task is currently being worked on
    TESTING,        // Task is under testing
    FIXED,          // Bug has been resolved
    COMPLETED,      // Task is fully done
    DEPLOYED        // Feature has been released
}
