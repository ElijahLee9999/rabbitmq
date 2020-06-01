package com.example.rabbitmq;

/**
 * 代码生成器
 * @author Elijah
 * @create 2020-05-27 10:56
 */
public class CodeGeneratorTest {
    private final static String AURHOR = "Elijah";

    private final static String DATASOURCE_URL = "jdbc:mysql://localhost:3306/demo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8";

    private final static String DATASOURCE_USERNAME = "root";

    private final static String DATASOURCE_PASSWORD = "mysql";

    private final static String PARENT_PACKAGE = "com.example.redis";

    private final static String MODULE_PATH = "/";

    private final static String PACKAGE_NAME = "demo";

    private final static String[] TABLE_NAMES = new String[]{"student"};

    public static void main(String[] args) {
        com.example.rabbitmq.generator.CodeGenerator codeGenerator = new com.example.rabbitmq.generator.CodeGenerator();
        codeGenerator.setAuthor(AURHOR);
        codeGenerator.setDatasourceUrl(DATASOURCE_URL);
        codeGenerator.setDatasourceUsername(DATASOURCE_USERNAME);
        codeGenerator.setDatasourcePassword(DATASOURCE_PASSWORD);
        codeGenerator.setParentPackage(PARENT_PACKAGE);
        codeGenerator.setPackageName(PACKAGE_NAME);
        codeGenerator.setModulePath(MODULE_PATH);
        codeGenerator.setTableNames(TABLE_NAMES);
        codeGenerator.generator();
    }
}
