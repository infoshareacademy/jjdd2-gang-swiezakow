package com.infoshareacademy.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Singleton
public class TasksStore {

    private Logger LOG = LoggerFactory.getLogger(TasksStore.class);

    private Map<Integer, RecipientModel> base;

    public Map<Integer, RecipientModel> getBase() {
        return base;
    }

    public TasksStore() {
        base = new HashMap<>();

        List<String> emailList = new ArrayList<>();
        emailList.add("zakupyapp@gmail.com");
        RecipientModel newConfiguration = new RecipientModel(
                emailList,
                "2017-10-30 11:30",
                10,
                1);
        add(newConfiguration);
    }

    public int findNewId() {
        if (base.isEmpty()) {
            return 1;
        }

        return base.keySet().stream().sorted().reduce((first, second) -> second).get() + 1;
//        return base.keySet().stream().max((a, b) -> a - b).get() + 1;
    }

    public void add(RecipientModel configuration) {
        LOG.info("Adding to do: " + configuration.toString());
        base.put(findNewId(), configuration);
    }


}
