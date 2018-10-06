import java.util.ArrayList;

/**
 * Работа, ее предшественники и приоритет.
 */
public class PrioritizedWork {
    public final String Name;
    public final int priority;
    public ArrayList<String> children;

    public PrioritizedWork(String name, int priority){
        this.Name = name;
        this.priority = priority;
        children = new ArrayList<>();
    }

    public void addChild(String name){
        children.add(name);
    }

}
