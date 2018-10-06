//import java.util.ArrayDeque;
//import java.util.Comparator;
//
//public class Prioritizer {
//    private static void Prioritize(){
//        /* Глобальное значение приоритета */
//        int g_priority=1;
//
//        ArrayDeque<JobLink> queueJobLinks =getQueque();
//
//        while (!queueJobLinks.isEmpty()){
//            JobLink currentJobLink = queueJobLinks.pop();
//
//            boolean Contains =workContains(currentJobLink);
//            if(Contains){
//                /* Ищем соответствующую работу по имени */
//                JobLink foundedJobLink = FindWork(currentJobLink);
//                /* Если работа не пустая, добавляем детей */
//                workAddChildren(foundedJobLink);
//
//            }
//            else{
//                /* Создаем новую работу */
//                JobLink newJobLink = new JobLink(currentJobLink.Name,null,g_priority++);
//
//                /* Если текущая работа не пуста, добавляем в список детей */
//                newJobLink.addChildren(currentJobLink.Name);
//                /* Добавляем работу в список приоритезированных */
//                priorizedJobLinks.add(newJobLink);
//            }
//        }
//        /* Выполняем сортировку работ */
//        priorizedJobLinks.sort(new Comparator<JobLink>() {
//            @Override
//            public int compare(JobLink o1, JobLink o2) {
//                return o1.priority > o2.priority ? 1 : -1;
//            }
//        });
//    }
//
//    /**
//     * Добавляем дочерние работы для текущей
//     * @param jobLink работа которую необходимо добавить в список дочерних
//     */
//    private static void workAddChildren(JobLink jobLink){
//        if(jobLink !=null)
//            for(JobLink priorized: priorizedJobLinks){
//                if(priorized.Name.equals(jobLink.Name)){
//                    priorized.addChildren(jobLink.Name);
//                }
//            }
//    }
//
//    /**
//     * Поиск работы соотвествующей параметрами
//     * @param jobLink Работа по которой нужно проверить
//     * @return Работа которая соотвествует требованиям
//     */
//    private static JobLink FindWork(JobLink jobLink){
//        for(JobLink priorized: priorizedJobLinks){
//            if(priorized.Name.equals(jobLink.Name)){
//                return priorized;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Проверка нахождения работы в списке приоритезированных работ
//     * @param current Текущая работа которую нужно проверить есть ли она в списке
//     * @return значение вхождения работы в список
//     */
//    private static boolean workContains(JobLink current){
//        for (JobLink jobLink : priorizedJobLinks){
//            if(jobLink.Name.equals(current.Name)){
//                return true;
//            }
//        }
//        return false;
//
//    }
//    /* Получить очередь работ */
//    private static ArrayDeque<JobLink> getQueque(){
//        ArrayDeque<JobLink> queue = new ArrayDeque<>();
//        for (JobLink job: jobLinks) {
//            if(job.Next_work.equals("")){
//                ((ArrayDeque<JobLink>) queue).addLast(job);
//            }
//        }
//        return queue;
//    }
//}
