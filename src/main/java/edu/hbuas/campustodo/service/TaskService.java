package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Priority;
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
     * 新增一条任务，默认优先级为 {@link Priority#MEDIUM}。
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

    /**
     * 按优先级筛选任务。
     *
     * @param priority 目标优先级，不能为 null
     * @return 命中该优先级的任务列表；没有命中任务时返回空列表，不会返回 null
     * @throws IllegalArgumentException 当 priority 为 null 时
     */
    public List<Task> filterByPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("筛选优先级不能为空");
        }
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (priority == task.getPriority()) {
                result.add(task);
            }
        }
        return result;
    }

public void completeTask(long taskId) {
    Task task = listAll().stream()
            .filter(t -> t.getId() == taskId)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("任务不存在"));

    if (task.isCompleted()) {
        throw new IllegalArgumentException("任务已经完成，不能重复完成");
    }

    task.setCompleted(true);
  }
}
