package edu.hbuas.campustodo.model;

/**
 * 校园任务管理器中的一条任务。
 *
 * <p>基线版本包含编号、标题与完成状态三个字段；本次迭代新增优先级字段，
 * 未显式设置时默认为 {@link Priority#MEDIUM}。</p>
 */
public class Task {

    private final long id;
    private String title;
    private boolean completed;
    private Priority priority = Priority.MEDIUM;

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

    /**
     * 返回任务优先级。
     *
     * @return 当前优先级；未显式设置过时返回 {@link Priority#MEDIUM}
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * 设置任务优先级。
     *
     * @param priority 目标优先级，不能为 null
     * @throws IllegalArgumentException 当 priority 为 null 时
     */
    public void setPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("任务优先级不能为空");
        }
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", title='" + title + "', priority=" + priority
                + ", completed=" + completed + '}';
    }
}