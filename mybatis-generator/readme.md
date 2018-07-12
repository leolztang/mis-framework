* 功能说明
mybatis-generator-maven-plugin是用于生成mybatis的xml、entity、mapper文件的工具。

* 使用说明
1. 修改generatorConfig.xml文件，设置数据库信息和指定要生成的表名，也可以使用%表示所有表
2. 数据库中需要先创建表
3. 运行mvn mybatis-generator:generate即可生成需要的文件。注意idea的Maven Projects->Execute Maven Goal貌似有BUG，不能使用项目指定的maven配置文件。
在idea中运行则请使用"Run/Debug Configuration"工具配置Maven任务
4. 将生成的文件拷贝到目标项目中即可，生成的已经集成了Mapper