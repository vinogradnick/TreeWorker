import java.util.ArrayList;

/**
 * Работа, ее предшественники и приоритет.
 */
public class PrioritizedWork {
    public final String Name;
    public final int priority;
    public ArrayList<String> parents;

    public PrioritizedWork(String name, int priority){
        this.Name = name;
        this.priority = priority;
        parents = new ArrayList<>();
    }

    public void addParent(String name){
        parents.add(name);
    }

}
