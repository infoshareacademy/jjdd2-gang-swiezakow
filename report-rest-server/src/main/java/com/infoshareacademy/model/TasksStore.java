package com.infoshareacademy.model;

import com.infoshareacademy.api.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Startup
@Singleton
public class TasksStore {

    private static final Logger LOG = LoggerFactory.getLogger(TasksStore.class);

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
                "2017-11-03 00:48",
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
        LOG.debug("Add to do: " + configuration.toString());
        base.put(findNewId(), configuration);
    }

    @Override
    public String toString() {
        return "TasksStore{" +
                "base=" + base +
                '}';
    }
}
