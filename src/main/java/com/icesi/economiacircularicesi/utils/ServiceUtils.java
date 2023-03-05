package com.icesi.economiacircularicesi.utils;

import com.icesi.economiacircularicesi.model.BaseEntity;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ServiceUtils {

    public static <T extends BaseEntity> int  getIndexOf(List<T> list, UUID id){

        for(int i=0; i<list.size(); i++){
            if(list.get(i).getId().equals(id)){
                return i;
            }
        }

        return -1;
    }

    public static <T extends BaseEntity> HashMap<UUID, T> toMapById(){
        return null;
    }

    /*
        private HashMap<UUID, List<ResponseOption>> getResponseOptionsByActivity(List<ResponseOption> selectedOptions, List<Activity> activities){

        HashMap<UUID, List<ResponseOption>> responseOptsMap = new HashMap<>();

        // Setting all the keys
        for(Activity activity : activities){
            responseOptsMap.put(activity.getActivityId(), new ArrayList<>());
        }

        // Classifying the options by activities
        for(ResponseOption currentOpt : selectedOptions){

            UUID activityId = questionService.getQuestion(currentOpt.getQuestionIdReference()).getActivityId();
            List<ResponseOption> tempList = responseOptsMap.get(activityId);
            tempList.add(currentOpt);
            responseOptsMap.put(activityId, tempList);

        }

        return responseOptsMap;
    }
     */
}
