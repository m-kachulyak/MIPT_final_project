package ru.netology.mipt.project.service;


import org.springframework.stereotype.Service;
import ru.netology.mipt.project.model.OperationDTO;
import ru.netology.mipt.project.model.Operation;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с операциями
 */

@Service
public class OperationService {

    private final StorageService<Operation> operationStorageService;

    private final StatementService statementService;

    public OperationService(StorageService<Operation> operationStorageService, StatementService statementService) {
        this.operationStorageService = operationStorageService;
        this.statementService = statementService;
    }


    public OperationDTO addNewOperation(Operation operation) {
        if (operation.getId() == 0) {
            int maxId = findAll().stream()
                    .map(Operation::getId)
                    .max(Integer::compare).orElse(0);
            operation.setId(maxId + 1);

        }
        operationStorageService.addData(operation);
        statementService.addOperationToClientStatement(operation.getClientId(),operation);

        return new OperationDTO(operation.getId(), operation.getValue(), operation.getTime());
    }

    public Optional<Operation> findOperation (int operationId) {
        return operationStorageService.getAllData().stream().filter(operation -> operation.getId() == operationId)
                .findFirst();
    }

    public List<Operation> findAll() {
        return operationStorageService.getAllData();
    }

    public void deleteOperation (int operationId) {
        Optional <Operation> operationOpt = findOperation(operationId);
        if (operationOpt.isPresent()) {
            Operation operation = operationOpt.get();
            operationStorageService.deleteData(operation);
            statementService.deleteOperationFromClientStatement(operation.getClientId(), operation);
        }
    }



}
