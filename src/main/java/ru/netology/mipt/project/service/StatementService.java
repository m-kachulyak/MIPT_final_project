package ru.netology.mipt.project.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.netology.mipt.project.model.Operation;

import java.util.*;

/**
 * Сервис для работы с выпиской
 */

@Service
public class StatementService {

    @Getter
    private final Map<Integer, List<Operation>> storage = new HashMap<>();


    /**
     * Добавление операции в выписку клиента
     *
     */
    public void addOperationToClientStatement(int customerId, Operation operation) {
        if (storage.get(customerId) != null) {
            storage.get(customerId).add(operation);
        } else {
            storage.put(customerId, new ArrayList<>(List.of(operation)));
        }
    }

    public void addAllStatement(Map <Integer, List <Operation>> statements) {
        storage.putAll(statements);
    }


    public List <Operation> getOperations(int customerId) {
        if (storage.get(customerId) == null) {
            return Collections.emptyList();
        }
        return storage.get(customerId);
    }

    public void deleteOperationFromClientStatement (int clientId, Operation operation) {
        if (storage.get(clientId) != null) {
            storage.get(clientId).remove(operation);
        }
    }


}
