package com.infoshareacademy.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


@Singleton
public class TasksStore {

    private Logger LOG = LoggerFactory.getLogger(TasksStore.class);

    private Map<Integer, TaskModel> base;

    public Map<Integer, TaskModel> getBase() {
        return base;
    }

    public TasksStore() {
        base = new HashMap<>();

        try {
            TaskModel taskModel1 = new TaskModel(findNewId(),
                    new RecipientConfiguration());
            add(taskModel1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
