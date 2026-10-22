# todo.py
class TodoTask:
    def __init__(self, title, priority):
        self.title = title
        # 优先级：high高，medium中，low低
        self.priority = priority

# 按优先级筛选任务
def filter_by_priority(task_list, target_priority):
    res = []
    for task in task_list:
        if task.priority == target_priority:
            res.append(task)
    return res

# 测试
if __name__ == "__main__":
    tasks = [
        TodoTask("完成实验报告", "high"),
        TodoTask("买文具", "medium"),
        TodoTask("整理桌面", "low"),
        TodoTask("Git实验", "high")
    ]
    high_tasks = filter_by_priority(tasks, "high")
    print("高优先级任务：")
    for t in high_tasks:
        print(t.title)