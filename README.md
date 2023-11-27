## 腹部多器官智能分割解析平台

### 项目简介

#### 系统架构

> 本项目系统由表现层、应用层、领域层、数据访问层和基础设施层等五大层级组成。

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251310150.png)

#### 业务流程

> 当客户端发送请求时，首先由拦截器（interceptor）对于除用户登录注册外的请求设置请求拦截。对于初次注册的账号由JWT进行token令牌签发，转发到客户端，当客户端后续访问服务端时需要携带token令牌，由JWT进行身份校验成功后方可通过请求。请求通过后，经由controller层进行请求转发并协调service层处理业务逻辑并返回数据到客户端，当请求需要使用py脚本或分割模型时，service层将调用模型或脚本完成业务逻辑。Service层处理业务逻辑时，需要与repository的数据持久化层交互以从数据库中存储或取出数据，从而完成整个业务逻辑的流程。

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251313246.png)

### 系统界面

#### 基础功能界面

1. 项目导航页

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251317476.png)

2. 系统主页

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251320892.png)

#### 个人信息管理

1. 系统登录界面

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251317734.png)

2. 用户更改密码界面

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251319336.png)

3. 用户更改密保界面

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251319783.png)

#### 器官分割界面

1. 模型配置

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251324057.png)

2. 标签列表

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251325419.png)

3. 三视图预览

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251326563.png)

4. 分割结果预览

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251326846.png)

#### 标签标注界面

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251328836.png)

#### 3D可视化界面

1. 分割结果渲染

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251329026.png)

2. 单器官可视化

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251330067.png)

#### 数据分析界面

1. 数据测量

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251331440.png)

2. 诊断报告

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251330919.png)

#### AIGC智能问诊界面

![](https://modox.oss-cn-hangzhou.aliyuncs.com/img/202311251332574.png)

### 项目配置

#### 资源位置

**数据文件**、**项目脚本文件**、**sql文件**都放在`src\main\resources\`下

#### 路径配置

1. 将项目文件treatment放置在文件路径`D:\Workspaces\Project\treattest`\下。
2. 将数据集文件dataset与项目文件也放在同级目录`D:\Workspaces\Project\treattest\`。

3. 将项目脚本文件treatpython放置在下一级文件路径`D:\Workspaces\Project\`。

#### 基础配置

sql文件：创建数据库treatment，将文件导入进去即可。

#### 登录页面

项目导航页：http://localhost:8080/treatment/treatfront/origin.html

账号和密码在**trt_user**表中

#### 使用说明

对于本项目中的所有功能，除功能模块**器官分割**下的模型配置和标签配置功能外，其他功能**基本依赖**于器官分割下的模型推理中的**上传文件**子功能。

### 开发人员名单

**主要贡献者**
* 湖南工业大学 人工智能 丁佳铄
* 湖南工业大学 人工智能 李鑫
* 湖南工业大学 软件工程 李慧聪
* 湖南工业大学 人工智能 王糠

**前期贡献者**
* 湖南工业大学 计算机科学与技术 蔡国滨 
* 湖南工业大学 计算机科学与技术 毛阳

**其他贡献者**
* 湖南工业大学  智能科学与技术 杨康庆