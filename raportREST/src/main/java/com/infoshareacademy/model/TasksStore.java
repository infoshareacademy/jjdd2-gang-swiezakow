package com.infoshareacademy.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TasksStore {

    private Logger LOG = LoggerFactory.getLogger(TasksStore.class);

    private Map<Integer, TaskModel> base;

    public Map<Integer, TaskModel> getBase() {
        return base;
    }

    public TasksStore() {
        base = new HashMap<Integer, TaskModel>();
    }

    public int findNewId() {
        if (base.isEmpty()) {
            return 1;
        }

        return base.keySet().stream().sorted().reduce((first, second) -> second).get() + 1;
//        return base.keySet().stream().max((a, b) -> a - b).get() + 1;
    }

    public void add(TaskModel task) {
        LOG.info("Adding to do: " + task.toString());
        base.put(task.getId(), task);
    }

}
