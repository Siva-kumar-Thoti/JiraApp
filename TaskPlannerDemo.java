import TaskPlanner.Sprint.Sprint;
import TaskPlanner.Sprint.SprintSnapshot;
import TaskPlanner.Task;
import TaskPlanner.TaskFactory;
import TaskPlanner.Tasks.BugTask;
import TaskPlanner.Tasks.FeatureTask;
import TaskPlanner.Tasks.StoryTask;
import TaskPlanner.Tasks.SubTask;
import enums.Impact;
import enums.Severity;
import enums.TaskStatus;
import enums.TaskType;

import java.time.LocalDate;

public class TaskPlannerDemo {
    public static void main(String[] args) {
        try {
            // Demonstrate comprehensive task creation and management
            System.out.println("=== Task Planner System Demonstration ===");

            // Create a sprint
            Sprint sprint1 = new Sprint(
                    "Sprint-1",
                    LocalDate.now(),
                    LocalDate.now().plusWeeks(2)
            );

            // Create different types of tasks using TaskFactory
            // 1. Feature Task
            FeatureTask dashboardFeature = TaskFactory.createFeatureTask(
                    "Create Dashboard",
                    "Brad",
                    LocalDate.now().plusDays(14),
                    "Develop a comprehensive analytics dashboard",
                    Impact.HIGH
            );
            sprint1.addTask(dashboardFeature);

            // 2. Bug Task
            BugTask mysqlBug = TaskFactory.createBugTask(
                    "Fix MySQL Connection Issue",
                    "Ryan",
                    LocalDate.now().plusDays(7),
                    Severity.P0,
                    "Intermittent connection drops in production database"
            );
            sprint1.addTask(mysqlBug);

            // 3. Story Task with SubTasks
            StoryTask microserviceStory = TaskFactory.createStoryTask(
                    "Create User Management Microservice",
                    "Amy",
                    LocalDate.now().plusDays(21),
                    "Develop a scalable user management system"
            );
            sprint1.addTask(microserviceStory);

            // Create SubTasks for the Story
            SubTask developmentSubTask = TaskFactory.createSubTask(
                    "Implement User CRUD Operations",
                    microserviceStory
            );
            SubTask testingSubTask = TaskFactory.createSubTask(
                    "Write Comprehensive Unit Tests",
                    microserviceStory
            );

            // Demonstrate Status Transitions
            System.out.println("\n=== Status Transition Demonstration ===");

            // Transition Feature Task
            System.out.println("Feature Task Status Transition:");
            dashboardFeature.updateStatus(TaskStatus.IN_PROGRESS);
            dashboardFeature.updateStatus(TaskStatus.TESTING);
            dashboardFeature.updateStatus(TaskStatus.DEPLOYED);

            // Transition Bug Task
            System.out.println("\nBug Task Status Transition:");
            mysqlBug.updateStatus(TaskStatus.IN_PROGRESS);
            mysqlBug.updateStatus(TaskStatus.FIXED);

            // Transition Story and SubTasks
            System.out.println("\nStory and SubTask Status Transition:");
            microserviceStory.updateStatus(TaskStatus.IN_PROGRESS);

            // Transition SubTasks
            developmentSubTask.updateStatus(TaskStatus.IN_PROGRESS);
            developmentSubTask.updateStatus(TaskStatus.COMPLETED);

            testingSubTask.updateStatus(TaskStatus.IN_PROGRESS);
            testingSubTask.updateStatus(TaskStatus.COMPLETED);

            // Demonstrate Sprint Snapshot
            System.out.println("\n=== Sprint Snapshot ===");
            SprintSnapshot snapshot = new SprintSnapshot().getSpringSnapshot(sprint1);

            System.out.println("On Track Tasks:");
            snapshot.getOnTrackTasks().forEach(task ->
                    System.out.println(task.getTitle() + " - Status: " + task.getStatus())
            );

            System.out.println("\nDelayed Tasks:");
            snapshot.getDelayedTasks().forEach(task ->
                    System.out.println(task.getTitle() + " - Status: " + task.getStatus())
            );

            // Demonstrate Builder Pattern
            System.out.println("\n=== Task Builder Demonstration ===");
            Task dynamicTask = TaskFactory.builder()
                    .title("Refactor Authentication Module")
                    .creator("Sarah")
                    .dueDate(LocalDate.now().plusDays(30))
                    .type(TaskType.STORY)
                    .build();

            System.out.println("Dynamic Task Created: " + dynamicTask.getTitle());

            // Error Handling Demonstration
            System.out.println("\n=== Error Handling Demonstration ===");
            demonstrateErrorHandling();

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to demonstrate error handling scenarios
    private static void demonstrateErrorHandling() {
        try {
            // Attempt invalid status transition
            System.out.println("Attempting Invalid Status Transition:");
            StoryTask invalidTask = TaskFactory.createStoryTask(
                    "Invalid Transition Test",
                    "Test User",
                    LocalDate.now().plusDays(10),
                    "Test Story"
            );

            // This should throw an exception
            invalidTask.updateStatus(TaskStatus.COMPLETED);
        } catch (IllegalStateException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }

        try {
            // Attempt to create task with past due date
            System.out.println("\nAttempting to Create Task with Past Due Date:");
            TaskFactory.createFeatureTask(
                    "Past Due Feature",
                    "Test User",
                    LocalDate.now().minusDays(1),  // Past date
                    "Invalid Feature",
                    Impact.LOW
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }
    }
}