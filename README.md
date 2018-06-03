## 自定义mybatis-generator-1.3.2 生成器

使用方法如下：

### 1.使用maven的MyBatis-Generator。

### 2.定制自己的mybtis-generator-core.jar
> 2.1 git clone https://github.com/VoofChat/mybatis-generator-1.3.2 到本地

> 2.2 安装这个jar到本地仓库

```java
mvn install -Dmaven.test.skip=true
```

> 2.3 修改pom.xml文件
```xml
<build>
    <finalName>webapp</finalName>
      <plugins>
          <plugin>
              <groupId>org.mybatis.generator</groupId>
              <artifactId>mybatis-generator-maven-plugin</artifactId>
              <version>1.3.2</version>
              <configuration>
                  <configurationFile>src/main/resources/generatorConfig.xml</configurationFile>
                  <verbose>true</verbose>
                  <overwrite>true</overwrite>
              </configuration>
              <dependencies>
                  <dependency>
                      <groupId>com.voofchat.mybatis.generator</groupId>
                      <artifactId>mybatis-generator-core</artifactId>
                      <version>1.0.0</version>
                  </dependency>
              </dependencies>
          </plugin>
      </plugins>
  </build>
```
> 2.4 修改generatorConfig.xml文件
```xml
<commentGenerator type="org.mybatis.generator.internal.VoofchatCommentGenerator">
    <property name="javaFileEncoding" value="UTF-8"/>
    <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
    <property name="suppressAllComments" value="false" />
    <property name="suppressDate" value="true" />
</commentGenerator>
```
> 2.5 执行 mvn mybatis-generator:generate

-------------------


主要自定义配置在org.mybaitis.context.AppContext配置文件中

```Java
/**
 * 项目名称
 */
public static final String PROJECT_NAME = "MiniappShop";

/**
 * 数据库名称
 */
public static final String DATABASE_NAME = "um";

/**
 * dao 文件前缀
 */
public static final String DAO_FILE_PREFIX = PROJECT_NAME;

/**
 * dao 文件后缀
 */
public static final String DAO_FILE_SUFFIX = "Dao";

/**
 * 实体 文件前缀
 */
public static final String ENTITY_FILE_PREFIX = PROJECT_NAME;

/**
 * 实体 文件后缀
 */
public static final String ENTITY_FILE_SUFFIX = "Po";


/**
 * mapper 文件前缀
 */
public static final String MAPPER_FILE_PREFIX = PROJECT_NAME;

/**
 * mapper 文件后缀
 */
public static final String MAPPER_FILE_SUFFIX = "Mapper";
```

效果如下：
