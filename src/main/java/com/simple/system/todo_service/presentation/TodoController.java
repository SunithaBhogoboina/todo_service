package com.simple.system.todo_service.presentation;

import com.simple.system.todo_service.application.TodoService;
import com.simple.system.todo_service.domain.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoItem> addItem(@RequestBody TodoItem item) throws Exception {
        return ResponseEntity.ok(todoService.addItem(item));
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoItem> updateItem(@RequestParam Integer itemId, String description){;
        return ResponseEntity.ok(todoService.updateItem(itemId, description));
    }

    @PostMapping(value = "/markItem", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoItem> markItem(@RequestParam Integer itemId, boolean isDone){
        return ResponseEntity.ok(todoService.markItem(itemId, isDone, null));
    }

    @GetMapping(value = "/getAllItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<TodoItem>> getAllItems(@RequestParam Optional<String> status) {
        return ResponseEntity.ok(todoService.getAllItems(status));
    }

    @GetMapping(value = "/getItemsById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<TodoItem>> getItemsById(@RequestParam Integer itemId) {
        return ResponseEntity.ok(todoService.getItemsById(itemId));
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void changeItemStatusToPastDue(){
        todoService.changeItemStatusToPastDue();

    }
}
