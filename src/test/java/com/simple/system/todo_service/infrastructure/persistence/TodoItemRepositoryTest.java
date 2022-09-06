package com.simple.system.todo_service.infrastructure.persistence;

import com.simple.system.todo_service.domain.TodoItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class TodoItemRepositoryTest {

    @Autowired
    private TodoItemRepository todoItemRepository;

    private static TodoItem item;

    @BeforeAll
    void beforeEach() {
        item = new TodoItem();
        item.setId(1);
        item.setDescription("task1");
        item.setDueDate(new Date());
        item.setStatus("not done");
        todoItemRepository.save(item);
    }

    @Test
    void findByItemStatus() {
        Iterable<TodoItem> items = todoItemRepository.findByItemStatus(Optional.of("not done"));
        assertTrue("not done".equals(items.iterator().next().getStatus()));
    }

    @Test
    void findByItemId() {
        Optional<TodoItem> item = todoItemRepository.findByItemId(1);
        assertEquals(1, item.get().getId());
    }
}