package edu.hbuas.campustodo.model;

/**
 * 校园任务管理器中的一条任务。
 *
 * <p>基线版本包含编号、标题与完成状态三个字段。</p>
 */
public class Task {

    private final long id;
    private String title;
    private boolean completed;

    public Task(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", title='" + title + "', completed=" + completed + '}';
    }
}