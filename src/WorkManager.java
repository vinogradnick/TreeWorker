import java.util.*;

public class WorkManager {
    static FileReader reader = new FileReader();

    static void getGant(ArrayList<JobLink> priorizedJobLinks, int countWorkers){
        ArrayList<JobLink> gantDiagram=new ArrayList<>();
        int time=1,
                worker=1;
        ArrayList<JobLink> doneJobLinks = new ArrayList<>();
        while(priorizedJobLinks.size()>0){

            for (int i = 0; i < priorizedJobLinks.size() ; i++) {
                boolean allDone=true;

                for (int j = 0; j < priorizedJobLinks.get(i).children.size() ; j++) {
                    if(!doneJobLinks.contains(priorizedJobLinks.get(i).children.get(j))){
                        allDone=false;
                    }

                }


            }
        }
    }

}

