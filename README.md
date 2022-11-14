<h1 style="text-align: center">基础框架</h1>

#### 项目简介
基于 Spring Boot 、 Spring Boot Jpa、Spring Security的门户后台管理平台

[//]: # (**体验地址：**  [http://192.168.179.71:8000/portal/#/]&#40;http://192.168.179.71:8000/portal/#/&#41;)

[//]: # ()
[//]: # (**账号密码：** `sysadmin / 123`)
[//]: # ()
[//]: # (#### 项目源码)

[//]: # ()
[//]: # (|     |   后端源码  |   前端源码  |)

[//]: # (|---  |--- | --- |)

[//]: # (|  svn   |  https://192.168.179.16:1234/svn/work/code/develop/ArcGis_YFZ/LandSpacePortal/giscloud   |  https://192.168.179.16:1234/svn/work/code/develop/ArcGis_YFZ/LandSpacePortal/giscloud-management-client2   |)

技术架构：
-----------------------------------


- 语言：Java 8

- 依赖管理：Maven

- 数据库：MySQL5.7x

- 缓存：Redis

- 基础框架：Spring Boot 2.7.5

- 持久层框架：Spring Data Jpa 2.1.6.RELEASE

- 安全框架：Oauth2

- 数据库连接池：HikariCP 3.2.0

- 缓存框架：redis

- 日志打印：logback

- 其他：fastjson，Swagger-ui，xxl-job, lombok（简化代码）等。


后台开发环境和依赖
----
- JDK: 1.8
- Maven: 3.5+
- MySQL: 5.7+
- Redis: 3.2+
- Lombok Plugin （必装）
- 后端使用 IntelliJ IDEA

开发环境搭建

[//]: # (----)

[//]: # (- 检出代码 svn checkout https://192.168.179.16:1234/svn/work/code/develop/ArcGis_YFZ/LandSpacePortal/giscloud)

[//]: # ()
[//]: # (- 进入到代码目录 mvn clean install)
#### 项目结构
项目采用按功能分模块的开发方式，结构如下

[//]: # ()
[//]: # (- `giscloud-common` 公共类)

[//]: # ()
[//]: # (- `giscloud-config` 微服务配置中心)

[//]: # ()
[//]: # (- `giscloud-elasticsearch` elasticsearch操作类)

[//]: # ()
[//]: # (- `giscloud-eureka` 微服务注册中心)

[//]: # ()
[//]: # (- `giscloud-filemanagement` 文件上传下载微服务)

[//]: # ()
[//]: # (- `giscloud-gateway` 网关微服务)

[//]: # ()
[//]: # (- `giscloud-gisfilemanagement` arcgis文件下载微服务)

[//]: # ()
[//]: # (- `giscloud-job` 任务调度微服务)

[//]: # ()
[//]: # (- `giscloud-logger` 日志微服务)

[//]: # ()
[//]: # (- `giscloud-management` 平台管理微服务)

[//]: # ()
[//]: # (- `giscloud-model` 平台数据模型)

[//]: # ()
[//]: # (- `giscloud-monitor` 平台监控微服务)

[//]: # ()
[//]: # (- `giscloud-portal` 门户微服务)

[//]: # ()
[//]: # (- `giscloud-proxy` 代理转发微服务)

[//]: # ()
[//]: # (- `giscloud-resources` 资源服务微服务)