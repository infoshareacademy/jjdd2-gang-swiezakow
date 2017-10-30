package com.infoshareacademy;

import com.infoshareacademy.service.timerEngineService.TaskGuard;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.text.ParseException;
import java.time.Instant;

@ApplicationPath("/")
public class App extends Application {
//    private static final ThreadLocal<TaskGuard> guard = ThreadLocal.withInitial(() -> {
//        try {
//            return new TaskGuard();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    });

    @Inject
    TaskGuard guard;

    public App() throws ParseException {
        System.out.println("teraz jest konstrukcja app" + Instant.now());
//        new TaskGuard();
    }
}
