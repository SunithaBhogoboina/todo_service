package com.simple.system.todo_service.infrastructure.persistence;

import com.simple.system.todo_service.domain.TodoItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoItemRepository extends CrudRepository<TodoItem, String> {

    @Query(value = "SELECT t from TodoItem t where t.status = ?1")
    Iterable<TodoItem> findByItemStatus(Optional<String> status);

    @Query(value = "SELECT t from TodoItem t where t.id = ?1")
    Optional<TodoItem> findByItemId(Integer itemId);
}
