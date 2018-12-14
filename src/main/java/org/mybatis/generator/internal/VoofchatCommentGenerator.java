/**
 *    Copyright 2006-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.internal;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * The Class DefaultCommentGenerator.
 *
 * @author Lizhihao
 */
public class VoofchatCommentGenerator extends DefaultCommentGenerator {


    /**
     * 文件作者
     */
    private String author ;

    /**
     * 邮箱
     */
    private String email ;

    public VoofchatCommentGenerator(){
        this.author = "";
        this.email = "";
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);

        this.author = properties.getProperty("author");
        if (StringUtility.stringContainsSpace(this.author)){
            this.author = "";
        }

        this.email = properties.getProperty("email");
        if (StringUtility.stringContainsSpace(this.email)){
            this.email = "";
        }
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        // 生成方法注释
        method.addJavaDocLine("/**");
        String method_name = method.getName();

        if ("deleteByPrimaryKey".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键删除数据库的记录");
        } else if ("insert".equals(method_name)) {
            method.addJavaDocLine(" * 插入数据库记录");
        } else if ("selectByPrimaryKey".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键获取一条数据库记录");
        } else if ("updateByPrimaryKey".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键来更新数据库记录");
        } else if ("selectAll".equals(method_name)) {
            method.addJavaDocLine(" * 获取全部数据库记录");
        } else if ("updateByPrimaryKeySelective".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键来更新数据库记录");
        } else if ("insertSelective".equals(method_name)) {
            method.addJavaDocLine(" * 插入数据库记录");
        }
        method.addJavaDocLine(" *");
        List<Parameter> parameterList = method.getParameters();
        String paramterName;
        for (Parameter parameter : parameterList) {
            paramterName = parameter.getName();
            method.addJavaDocLine(" * @param " + paramterName);
        }
        // addJavadocTag(method, false);

        method.addJavaDocLine(" * @return ");
        method.addJavaDocLine(" */");
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        // 类注释，不管用
        String shortName = innerClass.getType().getShortName();
        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine(" * 类注释");
        innerClass.addJavaDocLine(" * " + shortName);
        innerClass.addJavaDocLine(" * 数据库表：" + introspectedTable.getFullyQualifiedTable());
         addJavadocTag(innerClass, false);
        innerClass.addJavaDocLine(" */");
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        // 类注释，不管用
        String shortName = innerClass.getType().getShortName();
        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine(" * 类注释");
        innerClass.addJavaDocLine(" * " + shortName);
        innerClass.addJavaDocLine(" * 数据库表：" + introspectedTable.getFullyQualifiedTable());
//         addJavadocTag(innerClass, false);
        innerClass.addJavaDocLine(" */");
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        String shortName = topLevelClass.getType().getShortName();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd HH:mm");
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * 实体" + shortName + "对应数据库表" + introspectedTable.getFullyQualifiedTable());
        topLevelClass.addJavaDocLine(" * ");
        topLevelClass.addJavaDocLine(" * @author: " + this.author);
        topLevelClass.addJavaDocLine(" * @email: " + this.email);
        topLevelClass.addJavaDocLine(" * @date: " + sf.format(new Date()));
        topLevelClass.addJavaDocLine(" * @description: ");
        topLevelClass.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 添加字段注释
        StringBuffer sb = new StringBuffer();
        field.addJavaDocLine("/**");
        if (introspectedColumn.getRemarks() != null) {
            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
        }
//        sb.append(introspectedColumn.getActualColumnName());
//        field.addJavaDocLine(sb.toString());
        // addJavadocTag(field, false);
        field.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // get方法注释
//        StringBuffer sb = new StringBuffer();
//        method.addJavaDocLine("/**");
//        method.addJavaDocLine(" * <pre>");
//        method.addJavaDocLine(" * 获取：" + introspectedColumn.getRemarks());
//        sb.append(" * 表字段：");
//        sb.append(introspectedTable.getFullyQualifiedTable());
//        sb.append('.');
//        sb.append(introspectedColumn.getActualColumnName());
//        method.addJavaDocLine(sb.toString());
//        method.addJavaDocLine(" * </pre>");
//        method.addJavaDocLine(" *");
//        sb = new StringBuffer();
//        sb.append(" * @return ");
//        sb.append(introspectedTable.getFullyQualifiedTable());
//        sb.append('.');
//        sb.append(introspectedColumn.getActualColumnName());
//        sb.append("：");
//        sb.append(introspectedColumn.getRemarks());
//        method.addJavaDocLine(sb.toString());
//        // addJavadocTag(method, false);
//        method.addJavaDocLine(" */");
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // set方法注释
//        StringBuffer sb = new StringBuffer();
//        method.addJavaDocLine("/**");
//        method.addJavaDocLine(" * <pre>");
//        method.addJavaDocLine(" * 设置：" + introspectedColumn.getRemarks());
//        sb.append(" * 表字段：");
//        sb.append(introspectedTable.getFullyQualifiedTable());
//        sb.append('.');
//        sb.append(introspectedColumn.getActualColumnName());
//        method.addJavaDocLine(sb.toString());
//        method.addJavaDocLine(" * </pre>");
//        method.addJavaDocLine(" *");
//        Parameter parm = method.getParameters().get(0);
//        method.addJavaDocLine(" * @param " + parm.getName());
//        sb = new StringBuffer();
//        sb.append(" *            ");
//        sb.append(introspectedTable.getFullyQualifiedTable());
//        sb.append('.');
//        sb.append(introspectedColumn.getActualColumnName());
//        sb.append("：");
//        sb.append(introspectedColumn.getRemarks());
//        method.addJavaDocLine(sb.toString());
//        // addJavadocTag(method, false);
//        method.addJavaDocLine(" */");
    }

    @Override
    public void addComment(XmlElement xmlElement) {
        /*
        xmlElement.addElement(new TextElement("<!--")); 

        StringBuilder sb = new StringBuilder();
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        xmlElement.addElement(new TextElement(sb.toString()));
        */
//        xmlElement
//                .addElement(new TextElement(
//                        "  This element is automatically generated by MyBatis Generator, do not modify.")); 

//        String s = getDateString();
//        if (s != null) {
//            sb.setLength(0);
//            sb.append("  This element was generated on "); 
//            sb.append(s);
//            sb.append('.');
//            xmlElement.addElement(new TextElement(sb.toString()));
//        }

        /*xmlElement.addElement(new TextElement("-->")); */
    }

}
