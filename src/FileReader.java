import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

class FileReader {
    /**
     * Чтение всех работ и добавление в список
     * @throws FileNotFoundException
     */
    public static int workers = 0;
    static ArrayList<JobLink> readFile() throws IOException {
        ArrayList<JobLink> jobLinks = new ArrayList<JobLink>();
        BufferedReader reader = new BufferedReader(new java.io.FileReader("input.txt"));
        String line;
        workers = Integer.parseInt(reader.readLine());
        /* Перебираем все работы */
        while((line = reader.readLine())!=null){
            jobLinks.add(readLine(line));
        }
        addJobsWithNoChildren(jobLinks);
        return jobLinks;
    }

    /**
     * Выполняем чтение строки и формируем работу
     * @param line Входная строка с работами
     */
    static JobLink readLine(String line){
        String[] jobs = line.split("-");
        return new JobLink(jobs[0],jobs[1]);
    }

    static void addJobsWithNoChildren(ArrayList<JobLink> jobLinks){
        ArrayList<String> parents = new ArrayList<>();
        ArrayList<String> children = new ArrayList<>();
        for (JobLink jobLink : jobLinks) {
            parents.add(jobLink.Name);
            children.add(jobLink.Next);
        }
        for (String job:children) {
            if(!parents.contains(job)){
                jobLinks.add(new JobLink(job, null));
            }
        }
    }

}
