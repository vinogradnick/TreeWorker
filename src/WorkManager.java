import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WorkManager {
    static WorkReader reader = new WorkReader();

    static void getGant(ArrayList<Work> priorizedWorks,int countWorkers){
        ArrayList<Work> gantDiagram=new ArrayList<>();
        int time=1,
                worker=1;
        ArrayList<Work> doneWorks= new ArrayList<>();
        while(priorizedWorks.size()>0){

            for (int i = 0; i <priorizedWorks.size() ; i++) {
                boolean allDone=true;

                for (int j = 0; j <priorizedWorks.get(i).children.size() ; j++) {
                    if(!doneWorks.contains(priorizedWorks.get(i).children.get(j))){
                        allDone=false;
                    }

                }


            }
        }
    }

}

class WorkReader{
    /* Чтение файла */
    static BufferedReader reader;
    /* Список работ после  */
    static List<Character> afterList= new ArrayList<Character>();
    /* Список работ до */
    static List<Character> beforeList= new ArrayList<Character>();
    /* Список всех работ */
    static ArrayList<Work> works = new ArrayList<>();
    /* Список приоретизированных работ */
    static ArrayList<Work> priorizedWorks = new ArrayList<>();


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
            works.add(new Work(lines[0],lines[1],0));
    }
    private static void Prioritize(){
        /* Глобальное значение приоритета */
        int g_priority=1;

        ArrayDeque<Work> queueWorks=getQueque();

        while (!queueWorks.isEmpty()){
            Work currentWork =queueWorks.pop();

        boolean Contains =workContains(currentWork);
            if(Contains){
                /* Ищем соответствующую работу по имени */
                Work foundedWork= FindWork(currentWork);
                /* Если работа не пустая, добавляем детей */
                workAddChildren(foundedWork);

            }
            else{
                /* Создаем новую работу */
                Work newWork = new Work(currentWork.Name,null,g_priority++);

                /* Если текущая работа не пуста, добавляем в список детей */
                newWork.addChildren(currentWork.Name);
                /* Добавляем работу в список приоритезированных */
                priorizedWorks.add(newWork);
            }
        }
        /* Выполняем сортировку работ */
        priorizedWorks.sort(new Comparator<Work>() {
            @Override
            public int compare(Work o1, Work o2) {
                return o1.priority > o2.priority ? 1 : -1;
            }
        });
    }

    /**
     * Добавляем дочерние работы для текущей
     * @param work работа которую необходимо добавить в список дочерних
     */
    private static void workAddChildren(Work work){
        if(work!=null)
            for(Work priorized:priorizedWorks){
                if(priorized.Name.equals(work.Name)){
                    priorized.addChildren(work.Name);
                }
            }
    }

    /**
     * Поиск работы соотвествующей параметрами
     * @param work Работа по которой нужно проверить
     * @return Работа которая соотвествует требованиям
     */
    private static Work FindWork(Work work){
        for(Work priorized:priorizedWorks){
            if(priorized.Name.equals(work.Name)){
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
    private static boolean workContains(Work current){
        for (Work work:priorizedWorks){
            if(work.Name.equals(current.Name)){
                return true;
            }
        }
        return false;

    }
    /* Получить очередь работ */
    private static ArrayDeque<Work> getQueque(){
        ArrayDeque<Work> queue = new ArrayDeque<>();
        for (Work job:works) {
            if(job.Next_work.equals("")){
                ((ArrayDeque<Work>) queue).addLast(job);
            }
        }
        return queue;
    }
}

/**
 * @class Работа
 */
class Work{
    String Name;
    String Next_work;
    int priority;
    ArrayList<String> children;

    /**
     * Конструктор с параметрами для чтения
     * @param name Название работы
     * @param next Следующая работа
     * @param prio Приоритет работы
     */
    public Work(String name,String next,int priority){
        Name=name;
        Next_work=next;
        this.priority=priority;
        children= new ArrayList<>();
    }

    /**
     * Добавление дочерней работы в список
     * @param work Название работы для добавления
     */
    public void addChildren(String work){
        children.add(work);
    }

    /**
     * Установка приоритета на работу
     * @param value значение приоритета
     */
    public void setPriority(int value){
        priority=value;
    }

}
