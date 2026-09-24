package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TaskService 基线测试：覆盖新增任务与空标题校验。
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