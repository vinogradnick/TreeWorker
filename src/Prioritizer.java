import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;

public class Prioritizer {
    public static ArrayList<PrioritizedWork> Prioritize(ArrayList<JobLink> links) {
        ArrayList<PrioritizedWork> prioritizedWorks = new ArrayList<>(); // массив с приоритезированными работами
        int currentPriority = 1; // приоритет текущей работы
        ArrayDeque<JobLink> linksQueue = new ArrayDeque<JobLink>(); // очередь связей работ
        /* первыми в очередь обработки попадут листья дерева, не имеющие детей -- стоки */
        for (JobLink link:links) {
            if (link.Next == null){
                linksQueue.addLast(link);
            }
        }
        /* пока в очереди еще остались связи работ */
        while (!linksQueue.isEmpty()){
            /* извлечем следующую связь */
            JobLink currentLink = linksQueue.pop();
            PrioritizedWork prioritizedWork;
            /* если это сток, то просто создаем для него приоритизированную работу */
            if (currentLink.Next == null) {
                prioritizedWork = new PrioritizedWork(currentLink.Name, currentPriority++);
                prioritizedWorks.add(prioritizedWork);
            } else {
                /* если работа с таким именем была ранее приоритезирована */
                if (isAlreadyAdded(currentLink.Name, prioritizedWorks)) {
                    /* то найдем ее */
                    prioritizedWork = findWork(currentLink.Name, prioritizedWorks);
                }
                /* если работа с таким именем не была приоритезирована до этого */
                else {
                    /* Создаем новую приоритизированную работу */
                    prioritizedWork = new PrioritizedWork(currentLink.Name, currentPriority++);
                    prioritizedWorks.add(prioritizedWork);
                }
            }
            /* ищем родителей */
            for (JobLink parent:findParents(prioritizedWork.Name, links)) {
                prioritizedWork.addParent(parent.Name);
                linksQueue.addLast(parent);
            }

        }
        /* Выполняем сортировку работ */
        prioritizedWorks.sort(new Comparator<PrioritizedWork>() {
            @Override
            public int compare(PrioritizedWork o1, PrioritizedWork o2) {
                return o1.priority < o2.priority ? 1 : -1;
            }
        });

        return prioritizedWorks;
    }

    /**
     * Находит родительские работы по отношению к текущей.
     */
    private static ArrayList<JobLink> findParents(String childName, ArrayList<JobLink> links) {
        ArrayList<JobLink> parents = new ArrayList<JobLink>();
        for (JobLink link : links) {
            if (childName.equals(link.Next)) {
                parents.add(link);
            }
        }
        return parents;
    }

    /**
     * Поиск приоритезированной работы по имени.
     * @param name Имя для поиска.
     * @return Работа с заданным именем либо null, если работу найти не удалось.
     */
    private static PrioritizedWork findWork(String name, ArrayList<PrioritizedWork> prioritizedWorks){
        for(PrioritizedWork work: prioritizedWorks){
            if(work.Name.equals(name)){
                return work;
            }
        }
        return null;
    }

    /**
     * Проверка нахождения работы в списке приоритезированных работ
     */
    private static boolean isAlreadyAdded(String name, ArrayList<PrioritizedWork> prioritizedWorks){
        if (findWork(name, prioritizedWorks) != null) {
            return true;
        }
        else {
            return false;
        }
    }
}
