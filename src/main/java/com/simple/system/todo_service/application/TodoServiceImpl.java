package com.simple.system.todo_service.application;

import com.simple.system.todo_service.domain.Status;
import com.simple.system.todo_service.domain.TodoItem;
import com.simple.system.todo_service.infrastructure.persistence.TodoItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Override
    public TodoItem addItem(TodoItem item) {
        return todoItemRepository.save(item);
    }

    @Override
    public TodoItem updateItem(Integer itemId, String description) {
        Optional<TodoItem> itemResponse = getItemsById(itemId);
        TodoItem item = null;
        if(itemResponse.isPresent()){
            item = itemResponse.get();
            item.setDescription(description);
        }
        return addItem(item);
    }

    @Override
    public TodoItem markItem(Integer itemId, boolean isDone, String status) {
        Optional<TodoItem> itemResponse = getItemsById(itemId);
        TodoItem item = null;
        if(itemResponse.isPresent()){
            item = itemResponse.get();
            item.setStatus(status != null && status.equals(Status.past_due.getStatus())? Status.past_due.getStatus() : isDone ? Status.done.getStatus() : Status.not_done.getStatus());
        }
        return todoItemRepository.save(item);
    }

    @Override
    public Iterable<TodoItem> getAllItems(Optional<String> status) {
        if(status.isPresent()) {
            return todoItemRepository.findByItemStatus(status);
        } else {
            return todoItemRepository.findAll();
        }
    }

    @Override
    public Optional<TodoItem> getItemsById(Integer itemId) {
        return todoItemRepository.findByItemId(itemId);
    }

    @Override
    public void changeItemStatusToPastDue() {
        Iterable<TodoItem> items = getAllItems(Optional.of(Status.not_done.getStatus()));
        Iterator<TodoItem> iterator = items.iterator();
        Date today = new Date();
        TodoItem item = null;
        while(iterator.hasNext()) {
           item = iterator.next();
           if(item.getDueDate().before(today)) {
               markItem(item.getId(), false, Status.past_due.getStatus());
           }
        }
    }
}
