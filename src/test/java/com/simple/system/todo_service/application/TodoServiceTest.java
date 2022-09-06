package com.simple.system.todo_service.application;

import com.simple.system.todo_service.domain.TodoItem;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    private static TodoItem item;

    @BeforeAll
    static void beforeAll() {
        item = new TodoItem();
        item.setDescription("task1");
        item.setDueDate(new Date());
    }

    @Order(1)
    @Test
    void addItem() {
        TodoItem itemResponse = todoService.addItem(item);
        assertEquals(1, itemResponse.getId());
        assertTrue("not done".equals(itemResponse.getStatus()));
    }

    @Test
    void updateItem() {
        TodoItem itemResponse = todoService.updateItem(1, "Task1");
        assertFalse("task1".equals(itemResponse.getDescription()));
    }

    @Test
    void markItem() {
        TodoItem itemResponse = todoService.markItem(1, true, null);
        assertTrue("done".equals(itemResponse.getStatus()));
    }

    @Test
    void getAllItems() {
        Iterable<TodoItem> items = todoService.getAllItems(Optional.of("not done"));
        assertTrue(items.iterator().hasNext());
    }

    @Test
    void getItemsById() {
        Optional<TodoItem> itemResponse = todoService.getItemsById(1);
        assertEquals(1, itemResponse.get().getId());
    }

    @Test
    void changeItemStatusToPastDue() {
        todoService.changeItemStatusToPastDue();
        Optional<TodoItem> itemResponse = todoService.getItemsById(1);
        assertNotEquals("past due", itemResponse.get().getStatus());
    }

}