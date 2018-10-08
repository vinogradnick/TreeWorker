import java.io.IOException;
        import java.util.ArrayList;

public class Program {
    public static void main(String[] args) throws IOException {
        ArrayList<JobLink> jobLinks = FileReader.readFile();
        int workers = FileReader.workers;
        ;
        ArrayList<PrioritizedWork> prioritizedWorks = Prioritizer.Prioritize(jobLinks);
        System.out.println("a");
        ArrayList<GanttEntity> diag = GanttTool.getGanttDiagram(prioritizedWorks, 2);
        ;
    }
}
