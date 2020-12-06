package com.example.nugget.controller;

import com.example.nugget.model.PodcastTrimTaskRequest;
import com.example.nugget.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin
public class TaskController {
    @Autowired
    TaskService taskInfoViewService;

    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public @ResponseBody ResponseEntity<?> getTaskList(){
        List<PodcastTrimTaskRequest> response = this.taskInfoViewService.getAllTasks();
        return ResponseEntity.ok(response) ;

    }
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{task_id}")
    public @ResponseBody ResponseEntity<?> getTask(@PathVariable final UUID task_id) {
        PodcastTrimTaskRequest response = this.taskInfoViewService.getTaskByUUID(task_id);
        return ResponseEntity.ok(response) ;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/tasks/{task_id}", params = { "action=changeTaskStatus" })

    public @ResponseBody ResponseEntity<?> changeTaskStatus(@PathVariable final UUID task_id,
    @RequestParam(name = "taskStatus", required = true) String taskStatus) {
        PodcastTrimTaskRequest response = this.taskInfoViewService.changeTaskStatus(task_id, taskStatus);
        return ResponseEntity.ok(response) ;
    }
}
