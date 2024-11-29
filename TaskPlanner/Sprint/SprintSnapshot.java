package TaskPlanner.Sprint;

import TaskPlanner.Task;
import enums.TaskStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SprintSnapshot {
    private List<Task> onTrackTasks;
    private List<Task> delayedTasks;

    public SprintSnapshot getSpringSnapshot(Sprint sprint) {
        LocalDate now = LocalDate.now();
        List<Task> tasks=sprint.getTasks();
        this.onTrackTasks = tasks.stream()
                .filter(task -> task.getDueDate().isAfter(now) ||
                        task.getDueDate().isEqual(now))
                .collect(Collectors.toList());

        this.delayedTasks = tasks.stream()
                .filter(task -> task.getDueDate().isBefore(now) &&
                        task.getStatus() != TaskStatus.COMPLETED)
                .collect(Collectors.toList());
        return this;
    }

    // Getters for on-track and delayed tasks
    public List<Task> getOnTrackTasks() { return onTrackTasks; }
    public List<Task> getDelayedTasks() { return delayedTasks; }
}
