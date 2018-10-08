/**
 * Объект, хранящий информацию о том, какая работа выполняется в заданный момент времени заданным рабочим.
 */
public class GanttEntity {
    public String name;
    public int worker;
    public int time;

    public GanttEntity(String name, int worker, int time){
        this.name = name;
        this.worker = worker;
        this.time = time;
    }
}
