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
                  <configurationFile>src/main/resources/generator-config.xml</configurationFile>
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
> 2.4 generator-config.xml配置信息
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE generatorConfiguration PUBLIC
        "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" >
<generatorConfiguration>

    <!--<classPathEntry location="/Users/bjhl/.m2/repository/mysql/mysql-connector-java/5.1.21/mysql-connector-java-5.1.21.jar"/>-->

    <context id="context" targetRuntime="MyBatis3">

        <!-- 生成的Java文件的编码 -->
        <property name="javaFileEncoding" value="UTF-8"/>
        <!-- 格式化java代码 -->
        <property name="javaFormatter" value="org.mybatis.generator.api.dom.DefaultJavaFormatter"/>
        <!-- 格式化XML代码 -->
        <property name="xmlFormatter" value="org.mybatis.generator.api.dom.DefaultXmlFormatter"/>
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>

        <!-- lombok 配置信息 -->
        <plugin type="org.mybatis.generator.plugins.LombokPlugin" >
            <property name="hasLombok" value="true"/>
        </plugin>

        <!-- 增加Models ToStirng方法 -->
        <!--<plugin type="org.mybatis.generator.plugins.ToStringPlugin" />-->
        <!-- 增加爱Models Serializable实现 -->
        <!--<plugin type="org.mybatis.generator.plugins.SerializablePlugin" />-->

        <!-- 分页插件 -->
        <!-- 在example类中增 page 属性，并在mapper.xml的查询中加入page !=null 时的查询 -->
        <!--<plugin type="org.mybatis.generator.plugins.MySQLLimitPlugin" />-->
        <!--<plugin type="org.mybatis.generator.plugins.MySQLPagerPlugin" />-->
        <!--<plugin type="org.mybatis.generator.plugins.MySQLPaginationPlugin" />-->

        <!--<plugin type="com.xxg.mybatis.plugins.MySQLLimitPlugin" />-->
        <!--<plugin type="org.mybatis.generator.plugins.RowBoundsPlugin" />-->

        <!-- 注释配置信息 -->
        <commentGenerator type="org.mybatis.generator.internal.VoofchatCommentGenerator">
            <property name="javaFileEncoding" value="UTF-8"/>
            <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
            <property name="suppressAllComments" value="false" />
            <property name="suppressDate" value="true" />
        </commentGenerator>

        <!-- jdbc连接信息 -->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://127.0.0.1:3306/jeecg" userId="root" password="123456"/>

        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <!-- 生成bean和example对象 -->
        <javaModelGenerator targetPackage="com.baijia.growthbase.dal.microweb.po" targetProject="src/main/java">
            <property name="constructorBased" value="false"/>
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

        <!-- 生成mapper.xml类 -->
        <sqlMapGenerator targetPackage="microweb" targetProject="src/main/resources/mapper">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>

        <javaClientGenerator targetPackage="com.baijia.growthbase.dal.microweb.dao" targetProject="src/main/java" type="XMLMAPPER">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>


        <table tableName="rp_microweb_module" enableCountByExample="false" enableDeleteByExample="false" enableSelectByExample="false"
               enableUpdateByExample="false">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        
    </context>
</generatorConfiguration>
```
> 2.5 执行 mvn mybatis-generator:generate

-------------------


主要自定义配置在org.mybaitis.context.AppContext配置文件中

```Java
    /**
     * 关键词替换成空字符串
     */
    public static final String TABLE_REPLACE_KEYWORDS = "Rp";

    /**
     * 项目名称
     */
    public static final String PROJECT_NAME = "";

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
    public static final String DAO_FILE_SUFFIX = "Mapper";

    /**
     * 实体 文件前缀
     */
    public static final String ENTITY_FILE_PREFIX = PROJECT_NAME;

    /**
     * 实体 文件后缀
     */
    public static final String ENTITY_FILE_SUFFIX = "PO";


    /**
     * mapper 文件前缀
     */
    public static final String MAPPER_FILE_PREFIX = PROJECT_NAME;

    /**
     * mapper 文件后缀
     */
    public static final String MAPPER_FILE_SUFFIX = "Mapper";
```

