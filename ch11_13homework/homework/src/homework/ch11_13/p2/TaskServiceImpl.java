package homework.ch11_13.p2;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskServiceImpl implements TaskService{
    private ArrayList<Task> tasksList = new ArrayList<Task>();
    @Override
    public void exeuteTasks(){
        if(!tasksList.isEmpty()){
            for(Task T:tasksList){
                T.execute();
            }
        }
        else{
            System.out.println("Error: The Task array is empty!");
        }
    }
    @Override
    public void addTask(Task T){
        tasksList.add(T);
    }
}
