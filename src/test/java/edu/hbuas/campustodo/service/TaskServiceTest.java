package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Priority;
import edu.hbuas.campustodo.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TaskService 测试：覆盖新增任务、空标题校验以及优先级筛选功能。
 */
class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService();
    }

    @Test
    @DisplayName("新增任务后可以查询到，编号自动递增")
    void addTask_shouldAssignIncrementingIds() {
        Task first = service.addTask("写实验报告");
        Task second = service.addTask("复习 Git 命令");

        assertEquals(1, first.getId());
        assertEquals(2, second.getId());
        assertEquals(List.of(first, second), service.listAll());
    }

    @Test
    @DisplayName("标题为 null 或空白时应拒绝新增")
    void addTask_shouldRejectBlankTitle() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask(null));
        assertThrows(IllegalArgumentException.class, () -> service.addTask("   "));
        assertTrue(service.listAll().isEmpty());
    }

    @Test
    @DisplayName("初始状态下任务列表为空")
    void listAll_shouldBeEmptyInitially() {
        assertTrue(service.listAll().isEmpty());
    }

}
@Test
void completeTask_success_normalTask(){
    long tid = taskService.addTask("软件工程作业");
    taskService.completeTask(tid);
    var t = taskService.listAll().stream().filter(x->x.getId()==tid).findFirst().get();
    assertTrue(t.isCompleted());
}

@Test
void completeTask_throwWhenIdNotExist(){
    assertThrows(IllegalArgumentException.class, ()->{
        taskService.completeTask(9999L);
    });
}

@Test
void completeTask_throwWhenAlreadyCompleted(){
    long tid = taskService.addTask("报告");
    taskService.completeTask(tid);
    assertThrows(IllegalArgumentException.class, ()->{
        taskService.completeTask(tid);
    });
}


    @Test
    @DisplayName("新增任务默认优先级为 MEDIUM")
    void addTask_shouldDefaultToMediumPriority() {
        Task task = service.addTask("默认优先级任务");

        assertEquals(Priority.MEDIUM, task.getPriority());
        assertEquals(List.of(task), service.filterByPriority(Priority.MEDIUM));
    }

    @Test
    @DisplayName("按优先级筛选只返回命中的任务")
    void filterByPriority_shouldReturnMatchingTasks() {
        Task high = service.addTask("高优先级任务");
        high.setPriority(Priority.HIGH);
        Task medium = service.addTask("中优先级任务");
        Task low = service.addTask("低优先级任务");
        low.setPriority(Priority.LOW);

        assertEquals(List.of(high), service.filterByPriority(Priority.HIGH));
        assertEquals(List.of(medium), service.filterByPriority(Priority.MEDIUM));
        assertEquals(List.of(low), service.filterByPriority(Priority.LOW));
    }

    @Test
    @DisplayName("没有命中任务时返回空列表")
    void filterByPriority_shouldReturnEmptyListWhenNoMatch() {
        service.addTask("中优先级任务");

        assertTrue(service.filterByPriority(Priority.HIGH).isEmpty());
    }

    @Test
    @DisplayName("筛选条件为 null 时应明确拒绝，而不是抛出空指针")
    void filterByPriority_shouldRejectNull() {
        service.addTask("任意任务");

        assertThrows(IllegalArgumentException.class, () -> service.filterByPriority(null));
    }

    @Test
    @DisplayName("任务调整优先级后应出现在新的筛选结果中")
    void filterByPriority_shouldReflectPriorityChanges() {
        Task task = service.addTask("临时任务");
        task.setPriority(Priority.LOW);

        assertTrue(service.filterByPriority(Priority.MEDIUM).isEmpty());
        assertEquals(List.of(task), service.filterByPriority(Priority.LOW));

        task.setPriority(Priority.HIGH);

        assertEquals(List.of(task), service.filterByPriority(Priority.HIGH));
    }

    @Test
    @DisplayName("设置优先级为 null 时应明确拒绝")
    void setPriority_shouldRejectNull() {
        Task task = service.addTask("任意任务");

        assertThrows(IllegalArgumentException.class, () -> task.setPriority(null));
        assertEquals(Priority.MEDIUM, task.getPriority());
    }
}

