package org.mybatis.context;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: zhengzhixiong@baijiahulian.com
 * \* @date: 2018/6/3
 * \* @time: 上午10:57
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class AppConfig {

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

}
