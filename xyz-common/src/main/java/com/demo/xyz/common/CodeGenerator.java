package com.demo.xyz.common;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class CodeGenerator {
    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://10.14.32.245:3306/x_auth", "root", "crss123456")
            .build();

    /**
     * 策略配置
     */
    private static StrategyConfig.Builder strategyConfig(String table) {

        return new StrategyConfig.Builder().addInclude(table); // 设置需要生成的表名
    }

    /**
     * 全局配置
     */
    private static GlobalConfig.Builder globalConfig() {
        return new GlobalConfig.Builder().fileOverride();
    }

    /**
     * 包配置
     */
    private static PackageConfig.Builder packageConfig() {
        return new PackageConfig.Builder();
    }

    /**
     * 模板配置
     */
    private static TemplateConfig.Builder templateConfig() {
        return new TemplateConfig.Builder();
    }

    /**
     * 注入配置
     */
    private static InjectionConfig.Builder injectionConfig() {
        // 测试自定义输出文件之前注入操作，该操作再执行生成代码前 debug 查看
        return new InjectionConfig.Builder();
    }

    public static void main(String[] args) {
//        FastAutoGenerator.create("jdbc:mysql://10.14.32.245:3306/x_auth", "root", "crss123456")
//                .globalConfig(builder -> {
//                    builder.author("jiahong.li") // 设置作者
//                            //.enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("D://demo//xyz-auth//src//main//java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.demo.xyz") // 设置父包名
//                            .moduleName("auth") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("t_staff") // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//
//        GlobalConfig baomidou = new GlobalConfig.Builder()
//                .fileOverride()
//                .outputDir("/opt/baomidou")
//                .author("baomidou")
//                .enableKotlin()
//                .enableSwagger()
//                .dateType(DateType.TIME_PACK)
//                .commentDate("yyyy-MM-dd")
//                .build();

        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        String table = scanner("表名");
        Map<String, String> customFile = new HashMap<>();
        String className = table.substring(0,1).toUpperCase() + table.substring(1);
        //customFile.put("Staff.java", "/template/Test.java");
        customFile.put(className + "QueryVo.java", "/template/MyQueryVo.java");
//        customFile.put(className + "AddReqVo.java", "/template/MyAddReqVo.java.vm");
        generator.injection(injectionConfig().customFile(customFile).build());


        generator.strategy(strategyConfig(table).entityBuilder().versionColumnName("version").logicDeleteColumnName("status").build());
        generator.template(templateConfig()
                .service("/template/MyService.java")
                .serviceImpl("/template/MyServiceImpl.java")
                .controller("/template/MyController.java")
                .entity("/template/MyEntity.java")
                .build());
        generator.global(globalConfig().author("jiahong.li").build());

        // 设置自定义路径
        Map<OutputFile, String> pathInfo = new HashMap<>();
        String module = scanner("模块名");
        String classPack = "D:\\demo\\" + module + "\\src\\main\\java\\com\\demo\\xyz\\" + module.substring(module.indexOf("-")+1,module.length());
        String xmlPack = "D:\\demo\\" + module + "\\src\\main\\resources\\mapper";
        pathInfo.put(OutputFile.other, classPack+"\\vo");
        pathInfo.put(OutputFile.entity, classPack+"\\entity");
        pathInfo.put(OutputFile.controller, classPack+"\\controller");
        pathInfo.put(OutputFile.service, classPack+"\\service");
        pathInfo.put(OutputFile.serviceImpl, classPack+"\\service\\impl");
        pathInfo.put(OutputFile.mapper, classPack+"\\mapper");
        pathInfo.put(OutputFile.mapperXml, xmlPack);
        generator.packageInfo(packageConfig().pathInfo(pathInfo).parent("com.demo.xyz").moduleName(module.substring(module.indexOf("-")+1,module.length())).build());
        generator.execute();


    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}
