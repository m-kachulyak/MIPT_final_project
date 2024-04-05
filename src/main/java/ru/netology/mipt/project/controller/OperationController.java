package ru.netology.mipt.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.mipt.project.model.OperationDTO;
import ru.netology.mipt.project.model.Operation;
import ru.netology.mipt.project.service.OperationService;
import ru.netology.mipt.project.service.StatementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "mipt/api/v1/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    private final StatementService statementService;


    /**
     * Операции клиента
     *
     * @param clientId ID клиента
     * @return список операций клиента
     */
    @GetMapping("{id}")
    private List<OperationDTO> getClientOperations(@PathVariable("id") int clientId) {
        return statementService
                .getOperations(clientId)
                .stream()
                .map(this::operationToDto)
                .collect(Collectors.toList());
    }


    /**
     * Добавить операцию клиенту
     *
     * @param clientId     - ID клиента
     * @param operationDTO - операция
     * @return подтверждение операции
     */
    @PutMapping
    public OperationDTO addNewOperation(@RequestParam int clientId, @RequestBody OperationDTO operationDTO) {
        Operation operation = dtoToOperation(operationDTO, clientId);
        return operationService.addNewOperation(operation);
    }

    /**
     * удалить операцию
     *
     * @param operationId - ID операции
     */
    @DeleteMapping
    public void deleteOperation(@RequestBody String operationId) {
        operationService.deleteOperation(Integer.parseInt(operationId));
    }

    private OperationDTO operationToDto(Operation operation){
        return new OperationDTO(
                operation.getId(),
                operation.getValue(),
                operation.getTime());
    }

    private Operation dtoToOperation(OperationDTO dto, int clientId){
        return new Operation(0,
                dto.getValue(),
                dto.getTime(),
                clientId);
    }
}
