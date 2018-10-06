import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class WorkReader{
    /* Чтение файла */
    static BufferedReader reader;
    /* Список работ после  */
    static List<Character> afterList= new ArrayList<Character>();
    /* Список работ до */
    static List<Character> beforeList= new ArrayList<Character>();
    /* Список всех работ */
    static ArrayList<JobLink> jobLinks = new ArrayList<>();
    /* Список приоретизированных работ */
    static ArrayList<JobLink> priorizedJobLinks = new ArrayList<>();


    /**
     * Чтение всех работ и добавление в список
     * @throws FileNotFoundException
     */
    static void ReadWorks() throws IOException {
        reader = new BufferedReader(new FileReader("input.txt"));
        String line;
        /* Перебираем все виды работ */
        while((line = reader.readLine())!=null){
            ReadWorkLine(line);
        }
    }

    /**
     * Выполняем чтение строки и формируем работу
     * @param line Входная строка с работами
     */
    static void ReadWorkLine(String line){
        String[] lines = line.split("-");
        if(lines[0]!=lines[1])
            jobLinks.add(new JobLink(lines[0],lines[1],0));
    }
    private static void Prioritize(){
        /* Глобальное значение приоритета */
        int g_priority=1;

        ArrayDeque<JobLink> queueJobLinks =getQueque();

        while (!queueJobLinks.isEmpty()){
            JobLink currentJobLink = queueJobLinks.pop();

        boolean Contains =workContains(currentJobLink);
            if(Contains){
                /* Ищем соответствующую работу по имени */
                JobLink foundedJobLink = FindWork(currentJobLink);
                /* Если работа не пустая, добавляем детей */
                workAddChildren(foundedJobLink);

            }
            else{
                /* Создаем новую работу */
                JobLink newJobLink = new JobLink(currentJobLink.Name,null,g_priority++);

                /* Если текущая работа не пуста, добавляем в список детей */
                newJobLink.addChildren(currentJobLink.Name);
                /* Добавляем работу в список приоритезированных */
                priorizedJobLinks.add(newJobLink);
            }
        }
        /* Выполняем сортировку работ */
        priorizedJobLinks.sort(new Comparator<JobLink>() {
            @Override
            public int compare(JobLink o1, JobLink o2) {
                return o1.priority > o2.priority ? 1 : -1;
            }
        });
    }

    /**
     * Добавляем дочерние работы для текущей
     * @param jobLink работа которую необходимо добавить в список дочерних
     */
    private static void workAddChildren(JobLink jobLink){
        if(jobLink !=null)
            for(JobLink priorized: priorizedJobLinks){
                if(priorized.Name.equals(jobLink.Name)){
                    priorized.addChildren(jobLink.Name);
                }
            }
    }

    /**
     * Поиск работы соотвествующей параметрами
     * @param jobLink Работа по которой нужно проверить
     * @return Работа которая соотвествует требованиям
     */
    private static JobLink FindWork(JobLink jobLink){
        for(JobLink priorized: priorizedJobLinks){
            if(priorized.Name.equals(jobLink.Name)){
              return priorized;
            }
        }
        return null;
    }

    /**
     * Проверка нахождения работы в списке приоритезированных работ
     * @param current Текущая работа которую нужно проверить есть ли она в списке
     * @return значение вхождения работы в список
     */
    private static boolean workContains(JobLink current){
        for (JobLink jobLink : priorizedJobLinks){
            if(jobLink.Name.equals(current.Name)){
                return true;
            }
        }
        return false;

    }
    /* Получить очередь работ */
    private static ArrayDeque<JobLink> getQueque(){
        ArrayDeque<JobLink> queue = new ArrayDeque<>();
        for (JobLink job: jobLinks) {
            if(job.Next_work.equals("")){
                ((ArrayDeque<JobLink>) queue).addLast(job);
            }
        }
        return queue;
    }
}
