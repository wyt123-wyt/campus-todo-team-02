package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务管理服务：负责任务的创建与查询。
 */
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    /**
     * 新增一条任务。
     *
     * @param title 任务标题，不能为 null 或空白
     * @return 新创建的任务
     * @throws IllegalArgumentException 当标题为 null 或空白时
     */
    public Task addTask(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("任务标题不能为空");
        }
        Task task = new Task(nextId++, title.trim());
        tasks.add(task);
        return task;
    }

    /**
     * 返回当前全部任务。
     *
     * @return 任务列表的快照副本，修改返回值不影响服务内部状态
     */
    public List<Task> listAll() {
        return new ArrayList<>(tasks);
    }
}