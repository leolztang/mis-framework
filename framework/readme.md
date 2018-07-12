* 集成的组件

1. springboot web starter
2. springboot内置状态监控端点
3. apache commons通用工具包、文件处理包
4. 前端参数验证框架hibernate validator
5. json序列、反序列化工具包
6. Restful API文档工具swagger
7. mybatis、对象映射和分页查询工具
8. mysql或oracle数据库驱动（可支持其他驱动）
9. java bean代码生成工具包lombok

默认提供了swagger，文件上传，hibernate validator的配置，在业务项目中可以根据实际情况进行覆盖或扩展。


* framework的集成
1. 可以将framework作为独立项目(需要调整pom.xml的parent部分)， 也可以作为业务项目的module（即现在看到的项目结构）。然后在业务module依赖framework即可

```
<dependency>
    <groupId>com.infogath</groupId>
    <artifactId>framework</artifactId>
    <version>0.1</version>
</dependency>
```

2. Lombok在IDE中不能被直接处理，还需要IDE的lombok插件支持。以idea为例：File->settings->Plugins->Browse repositories->Lombok 


