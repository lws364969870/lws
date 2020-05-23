package com.lws.gencode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.lws.gencode.enums.ModulEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成
 *
 * @author wilsonlee
 */
@Slf4j
public class CodeGenerator {

    /**
     * 构建生成器
     */
    public static AutoGenerator autoGenerator = new AutoGenerator();

    public static void main(String[] args) {

        //1、选择模块
        ModulEnum module = ModulEnum.AUTH;

        //2、项目路径
        String projectPath = "D:/workspace/lws" + File.separator + module.getUrl();

        /** 全局配置 */
        GlobalConfig globalConfig = new GlobalConfig();
        //1、作者
        globalConfig.setAuthor("WilsonLee");
        //2、覆盖生成文件
        globalConfig.setFileOverride(true);
        //3、生成文件路径
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        //4、主键类型
        globalConfig.setIdType(IdType.AUTO);
        //5、生成swagger2
        globalConfig.setSwagger2(true);

        /** 策略配置 */
        StrategyConfig strategyConfig = new StrategyConfig();
        //1、需要生成的表
        strategyConfig.setInclude("sys_user");
        //2、实体命名策略 - 驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //3、字段命名策略 - 驼峰
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //4、lombok
        strategyConfig.setEntityLombokModel(true);
        //5、过滤表前缀
//        strategyConfig.setTablePrefix("gov_");

        /** 包配置 */
        PackageConfig packageConfig = new PackageConfig();
        //1、模块名称
        packageConfig.setModuleName(module.getModuleName());
        //2、包名
        packageConfig.setParent(module.getPageageName());

        /** 数据库配置 */
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/lws?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("admin");

        /** 模板配置 */
        TemplateConfig templateConfig = new TemplateConfig();
        //1、关闭xml默认模板
        templateConfig.setXml(null);

        /** 自定义输出模板配置 */
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        //1、自定义xml模板
        fileOutConfigList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper"
                        + StringPool.DOT_XML;
            }
        });

        /** 自定义属性配置 */
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                // TODO add something
                this.setMap(map);
            }
        };
        injectionConfig.setFileOutConfigList(fileOutConfigList);

        /** 代码生成 */
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.setCfg(injectionConfig);
        //Freemarker模板引擎
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }

}
