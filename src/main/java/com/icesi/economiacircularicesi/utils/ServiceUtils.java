package com.icesi.economiacircularicesi.utils;

import com.icesi.economiacircularicesi.model.BaseEntity;

import javax.persistence.Entity;
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
}
