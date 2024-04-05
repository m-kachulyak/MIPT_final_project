package ru.netology.mipt.project.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.mipt.project.config.OperationProperties;
import ru.netology.mipt.project.model.Operation;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
@Service
public class AsyncOperationService {

    private final OperationProperties properties;
    private final OperationService operationService;
    private final Queue<Operation> queue = new LinkedList<>();

    public AsyncOperationService(OperationService operationService, OperationProperties properties) {
        this.operationService = operationService;
        this.properties = properties;
    }

    @PostConstruct
    public void init(){
        Thread t = new Thread() {
            @Override
            public void run() {
                processQueue();
            }
        };
        t.start();
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();
            if (operation == null) {
                try {
                    Thread.sleep(properties.getSleepMilliSeconds());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                operationService.addNewOperation(operation);
            }
        }
    }
}
