package com.simple.system.todo_service.domain;

public enum Status {
    not_done("not done"),
    done("done"),
    past_due("past due");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
