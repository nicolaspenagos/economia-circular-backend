package com.icesi.economiacircularicesi.utils;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class CRUDRepositoryUtils {

    public static <T, R extends CrudRepository> void deleteItems(List<T> items, R repository){

        for(T current: items){
            System.out.println("DELETERING TESTTTT");
            repository.delete(current);
        }
    }
}
