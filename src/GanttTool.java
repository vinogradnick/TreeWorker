import java.util.ArrayList;

public class GanttTool {
    public static ArrayList<GanttEntity> getGanttDiagram(ArrayList<PrioritizedWork> jobs, int workers){
        int index = 0;
        int time = 1;
        int worker = 1;
        ArrayList<GanttEntity> diagram = new ArrayList<>();
        ArrayList<String> done = new ArrayList<>();
        while(jobs.size() > 0){
            if (worker > workers){
                worker = 1;
                time++;
            }

            if(areAllParentsDone(jobs.get(index).parents, done)){
                if (isTimeOk(jobs.get(index).parents, diagram, time)){
                    diagram.add(new GanttEntity(jobs.get(index).Name, time, worker++));
                    done.add(jobs.get(index).Name);
                    jobs.remove(index);
                    index = 0;
                }

            }
            else {
                index += 1;
                if (index >= jobs.size()){
                    index = 0;
                }
            }
        }
        return diagram;
    }

    /**
     * Проверяет, все ли родители были выполнены.
     */
    private static boolean areAllParentsDone(ArrayList<String> parents, ArrayList<String> done){
        for (String parent:parents) {
            if(!done.contains(parent)){
                return false;
            }
        }
        return true;
    }

    /**
     * Проверяет, все ли родители были выполнены.
     */
    private static boolean isTimeOk(ArrayList<String> parents, ArrayList<GanttEntity> diag, int currentTime){
        for (String parent:parents) {
            for (GanttEntity entity:diag){
                if (parent.equals(entity.name)){
                    if(entity.time == currentTime){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
