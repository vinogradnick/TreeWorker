import java.util.ArrayList;

/**
 * @class Работа
 */
class JobLink {
    String Name;
    String Next_work;


    /**
     * Конструктор с параметрами для чтения
     * @param name Название работы
     * @param next Следующая работа
\     */
    public JobLink(String name, String next, int priority){
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
