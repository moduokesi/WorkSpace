## 腹部多器官智能分割解析平台

### 资源位置

**数据文件**、**项目脚本文件**、**sql文件**都放在`src\main\resources\`下

### 路径配置

1. 将项目文件treatment放置在文件路径`D:\Workspaces\Project\treattest`\下。
2. 将数据集文件dataset与项目文件也放在同级目录`D:\Workspaces\Project\treattest\`。

3. 将项目脚本文件treatpython放置在下一级文件路径`D:\Workspaces\Project\`。

### 基础配置

sql文件：创建数据库treatment，将文件导入进去即可。

### 登录页面

项目导航页：http://localhost:8080/treatment/treatfront/origin.html

账号和密码在**trt_user**表中

### 使用说明

对于本项目中的所有功能，除功能模块**器官分割**下的模型配置和标签配置功能外，其他功能**基本依赖**于器官分割下的模型推理中的**上传文件**子功能。