package com.simple.system.todo_service.presentation;

import com.simple.system.todo_service.application.TodoService;
import com.simple.system.todo_service.domain.TodoItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@EnableAutoConfiguration
@SpringBootTest(classes = {ServletWebServerFactoryAutoConfiguration.class, TodoController.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerIntegrationTest {

    @LocalServerPort
    private String port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private TodoController todoController;

    private static TodoItem item;

    @BeforeAll
    static void beforeAll() {
        item = new TodoItem();
        item.setDescription("task1");
        item.setDueDate(new Date());
    }

    @Test
    void addItem() {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/todo/add", item, String.class);
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void markItem() {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/todo/markItem?itemId=4&isDone=true", null, String.class);
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void getAllItems() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/todo/getAllItems", String.class);
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void getAllItemsByStatus() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/todo/getAllItems?status=not done", String.class);
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void getItemsById() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/todo/getItemsById?itemId=2", String.class);
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }
}