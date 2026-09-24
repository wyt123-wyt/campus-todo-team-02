package edu.hbuas.campustodo.model;

/**
 * 任务优先级。
 *
 * <p>用于区分任务的紧急程度，新增任务在未显式指定时默认使用
 * {@link #MEDIUM}。</p>
 */
public enum Priority {

    /** 高优先级：需要优先处理的任务 */
    HIGH,

    /** 中优先级：默认级别 */
    MEDIUM,

    /** 低优先级：可以稍后处理的任务 */
    LOW
}