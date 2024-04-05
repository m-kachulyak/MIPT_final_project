package ru.netology.mipt.project.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope ("prototype")
public class StorageService<T> {


    private List<T> dataList = new ArrayList<T>();


    public void addData(T data) {
        dataList.add(data);
    }

    public List<T> getAllData() {
        return dataList;
    }

    public void deleteData(T data) {
        dataList.remove(data);
    }


}
