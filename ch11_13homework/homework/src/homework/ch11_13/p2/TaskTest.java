package homework.ch11_13.p2;

public class TaskTest {
    public static void main(String[] argv)
    {
        TaskServiceImpl taskQ = new TaskServiceImpl();
        taskQ.exeuteTasks(); //Test using an empty array whether output an error.
        AchieveTask1 task1 = new AchieveTask1();
        AchieveTask2 task2 = new AchieveTask2();
        AchieveTask3 task3 = new AchieveTask3();
        taskQ.addTask(task1);
        taskQ.addTask(task2);
        taskQ.addTask(task3);
        taskQ.exeuteTasks();
    }
}
