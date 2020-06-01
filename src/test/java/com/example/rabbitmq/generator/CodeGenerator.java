package com.example.rabbitmq.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Code Generator
 *
 * @author surh
 */
@Data
public class CodeGenerator {

  /** 作者 */
  private String author;

  /** 数据源URL */
  private String datasourceUrl;

  /** 数据源用户名 */
  private String datasourceUsername;

  /** 数据源密码 */
  private String datasourcePassword;

  /** 父包名 */
  private String parentPackage;

  /** 模块路径 */
  private String modulePath;

  /** 包名 */
  private String packageName;

  /** 表名数组 */
  private String[] tableNames;

  public void generator() {
    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    final String projectPath = System.getProperty("user.dir") + modulePath;
    gc.setOutputDir(projectPath + "/src/main/java");
    // 作者
    gc.setAuthor(author);
    // 打开输出目录
    gc.setOpen(false);
    // 实体属性 Swagger2 注解
    gc.setSwagger2(true);
    // 日期类型
    gc.setDateType(DateType.ONLY_DATE);
    // 主键类型
    gc.setIdType(IdType.ASSIGN_ID);
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl(this.datasourceUrl);
    // dsc.setSchemaName("public");
    dsc.setDriverName("com.mysql.jdbc.Driver");
    dsc.setUsername(this.datasourceUsername);
    dsc.setPassword(this.datasourcePassword);
    mpg.setDataSource(dsc);

    // 包配置
    final PackageConfig pc = new PackageConfig();
    pc.setModuleName(packageName);
    pc.setParent(this.parentPackage);
    mpg.setPackageInfo(pc);

    // 自定义配置
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        // to do nothing
      }
    };

    // 如果模板引擎是 freemarker
    String templatePath = "/templates/mapper.xml.vm";

    // 自定义输出配置
    List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
    // 自定义配置会被优先输出
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
      }
    });

    cfg.setFileOutConfigList(focList);
    mpg.setCfg(cfg);

    // 配置模板
    TemplateConfig templateConfig = new TemplateConfig();

    // 配置自定义输出模板
    //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
    // templateConfig.setEntity("templates/entity2.java");
    // templateConfig.setService();
    // templateConfig.setController();

    templateConfig.setXml(null);
    mpg.setTemplate(templateConfig);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//    strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
    strategy.setEntityLombokModel(true);
    strategy.setRestControllerStyle(true);
    strategy.setEntitySerialVersionUID(true);
    strategy.setEntityBuilderModel(false);
    // 公共父类
//    strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
    // 写于父类中的公共字段
//    strategy.setSuperEntityColumns("id");
    strategy.setInclude(tableNames);
    strategy.setControllerMappingHyphenStyle(true);
    strategy.setTablePrefix(pc.getModuleName() + "_");
    strategy.setLogicDeleteFieldName("Deleted");
    // 填充字段
    final List<TableFill> tableFillList = new ArrayList<>();
    for (String tableName : this.tableNames) {
      tableFillList.add(new TableFill(tableName + "_deleted", FieldFill.INSERT));
      tableFillList.add(new TableFill(tableName + "_enabled", FieldFill.INSERT));
      tableFillList.add(new TableFill(tableName + "_creator", FieldFill.INSERT));
      tableFillList.add(new TableFill(tableName + "_created_time", FieldFill.INSERT));
      tableFillList.add(new TableFill(tableName + "_updater", FieldFill.INSERT_UPDATE));
      tableFillList.add(new TableFill(tableName + "_updated_time", FieldFill.INSERT_UPDATE));

      tableFillList.add(new TableFill("deleted", FieldFill.INSERT));
      tableFillList.add(new TableFill("enabled", FieldFill.INSERT));
      tableFillList.add(new TableFill("creator", FieldFill.INSERT));
      tableFillList.add(new TableFill("created_time", FieldFill.INSERT));
      tableFillList.add(new TableFill("updater", FieldFill.INSERT_UPDATE));
      tableFillList.add(new TableFill("updated_time", FieldFill.INSERT_UPDATE));
    }
    strategy.setTableFillList(tableFillList);
    mpg.setStrategy(strategy);
    mpg.execute();
  }
}
