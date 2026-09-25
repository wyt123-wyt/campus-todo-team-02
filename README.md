# CampusTodo

CampusTodo 是一个控制台版校园任务管理器，用于软件工程综合实践课程的团队协同开发实验（基于 GitHub Flow）。

- 开发语言：Java 17
- 构建工具：Maven
- 协作平台：GitHub

当前版本：支持新增、列出和按优先级筛选任务。

## 功能

- `TaskService#addTask(String title)`：新增任务，自动分配递增编号；标题为空时拒绝。
- `TaskService#listAll()`：列出全部任务。
- `TaskService#filterByPriority(Priority priority)`：按优先级筛选任务；没有命中时返回空列表，筛选条件为 null 时明确拒绝。

## 构建与测试

```bash
mvn -B verify
```

## 目录结构

```text
campus-todo/
├─ pom.xml
├─ README.md
├─ .gitignore
└─ src/
   ├─ main/java/edu/hbuas/campustodo/
   │  ├─ model/Task.java
   │  └─ service/TaskService.java
   └─ test/java/edu/hbuas/campustodo/service/
      └─ TaskServiceTest.java
```