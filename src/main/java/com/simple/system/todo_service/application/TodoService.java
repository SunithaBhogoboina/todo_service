package com.simple.system.todo_service.application;

import com.simple.system.todo_service.domain.TodoItem;

import java.util.Optional;

public interface TodoService {
    public TodoItem addItem(TodoItem item);
    public TodoItem updateItem(Integer itemId, String description);
    public TodoItem markItem(Integer itemId, boolean isDone, String status);
    public Iterable<TodoItem> getAllItems(Optional<String> status);
    public Optional<TodoItem> getItemsById(Integer itemId);
    public void changeItemStatusToPastDue();
}
