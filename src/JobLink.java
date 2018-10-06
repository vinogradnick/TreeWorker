import java.util.ArrayList;

/**
 * @class Связь работ РАБОТА ДО-РАБОТА ПОСЛЕ
 */
class JobLink {
    String Name;
    String Next;

    /**
     * Конструктор с параметрами для чтения
     * @param name Название работы
     * @param next Следующая работа
\     */
    public JobLink(String name, String next){
        Name=name;
        Next=next;
    }
}
