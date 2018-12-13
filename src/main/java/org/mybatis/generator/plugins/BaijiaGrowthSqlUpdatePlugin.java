package org.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.Iterator;
import java.util.List;
/**
 * @author: zhengzhixiong@baijiahulian.com
 * @date: 2018/12/13
 * @time: 12:10 PM
 * Description:
 */
public class BaijiaGrowthSqlUpdatePlugin extends PluginAdapter {

    /**
     * 数据库对应字段
     */
    private String [] removeColumns;

    /**
     * po 对应属性
     */
    private String [] removeFields;

    /**
     * 是否做自定义更新
     */
    private Boolean isCustemUpdate = false;

    @Override
    public boolean validate(List<String> list) {

        if (properties.getProperty("removeColumns") != null ) {

            removeColumns = properties.getProperty("removeColumns").split(",");

            if (removeColumns != null && removeColumns.length > 0){
                isCustemUpdate = true;
                removeFields = new String[removeColumns.length];
                for (int i = 0; i < removeFields.length ; i++ ) {
                    removeFields[i] = JavaBeansUtil.getCamelCaseString(removeColumns[i],false);
                }
            }
        }

//        for (String str : removeColumns){
//            System.out.println(str);
//        }
//
//        for (String str : removeFields){
//            System.out.println(str);
//        }

        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        System.out.println("sqlMapUpdateByPrimaryKeySelectiveElementGenerated:");
//        System.out.println("before update:");
//        System.out.println(element.getFormattedContent(1));

        if (isCustemUpdate) {
            commonSqlUpdateElementGenerated(element);
        }

//        System.out.println("after update:");
//        System.out.println(element.getFormattedContent(1));
//        System.out.println("----------------------------------------");
        return true;
    }
    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
//        System.out.println("sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated:");
//        System.out.println("before update:");
//        System.out.println(element.getFormattedContent(1));
//
        if (isCustemUpdate) {
            commonSqlUpdateElementGenerated(element);
            checkoutUpdateOuput(element);
        }

//        System.out.println("after update:");
//        System.out.println(element.getFormattedContent(1));
//        System.out.println("----------------------------------------");
        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
//        System.out.println("sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated:");
//        System.out.println(element.getFormattedContent(1));

//        System.out.println("----------------------------------------");

        if (isCustemUpdate) {
            commonSqlUpdateElementGenerated(element);
            checkoutUpdateOuput(element);
        }

//        System.out.println(element.getFormattedContent(1));
//        System.out.println("----------------------------------------");
        return true;
    }

    /**
     * 调整update Sql 语句输出
     * @param xmlElement
     */
    private void commonSqlUpdateElementGenerated(XmlElement xmlElement){
        List<Element> elements = xmlElement.getElements();
        Iterator<Element> iterator = elements.iterator();
        while(iterator.hasNext()){
            Element element = iterator.next();

            if (element instanceof XmlElement){
                XmlElement xml = (XmlElement) element;
                List<Attribute> attributes = xml.getAttributes();
                for (Attribute attr : attributes ) {
                    String value = attr.getValue();
//                    if (value != null && (value.contains("createTime") || value.contains("updateTime"))) {
                    if (value != null && isContainsRemoveColums(this.removeFields, value)) {
                        System.out.println("remove:" + value);
                        iterator.remove();
                    }
                }

                commonSqlUpdateElementGenerated(xml);
            }

            if (element instanceof TextElement) {
                TextElement te = (TextElement) element;
                String content = te.getContent();
//                if (content != null && (content.contains("create_time") || content.contains("update_time"))) {
                if (content != null && isContainsRemoveColums(this.removeColumns, content)) {
                    System.out.println("remove:" + content);
                    iterator.remove();
                }

            }
        }
    }

    /**
     * 校验 sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated , sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated 输出，需要去掉最后一个"," 符号输出
     * @param xmlElement
     */
    private void checkoutUpdateOuput(XmlElement xmlElement) {
        List<Element> elements = xmlElement.getElements();
        if (elements !=null && elements.size() > 0 ){
            Element e = elements.get(elements.size() - 2);
            if (e instanceof TextElement){
                TextElement te = (TextElement) e;
                if ( te.getContent().contains(",") ){
//                    System.out.println("replace:" + te.getContent().replace(",", ""));
                    te.setContent(te.getContent().replace(",", ""));
                }
            }
        }
    }

    private Boolean isContainsRemoveColums(String [] checkArr, String content) {
        for (String str: checkArr) {
            if (content.contains(str)) {
                return true;
            }
        }
        return false;
    }

}
