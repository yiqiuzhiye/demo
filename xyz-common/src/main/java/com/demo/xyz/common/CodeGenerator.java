package com.demo.xyz.common;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
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

        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        String table = scanner("表名");
        String flag = scanner("输入 'Y' 生成controller");
        if("Y".equals(flag)){
            Map<String, String> customFile = new HashMap<>();
            String className = table.substring(2);
            className =  className.substring(0,1).toUpperCase() + className.substring(1);
            customFile.put(className + "RespVo.java", "/template/MyRespVo.java.vm");
            customFile.put(className + "QueryVo.java", "/template/MyQueryVo.java.vm");
            customFile.put(className + "AddReqVo.java", "/template/MyAddReqVo.java.vm");
            generator.injection(injectionConfig().customFile(customFile).build());

            generator.template(templateConfig()
                    .service("/template/MyService1.java")
                    .serviceImpl("/template/MyServiceImpl1.java")
                    .controller("/template/MyController1.java")
                    .entity("/template/MyEntity.java")
                    .build());
        }else {
            generator.template(templateConfig()
                    .service("/template/MyService1.java")
                    .serviceImpl("/template/MyServiceImpl1.java")
                    .disable(TemplateType.CONTROLLER)
                    .entity("/template/MyEntity.java")
                    .build());
        }
        generator.strategy(strategyConfig(table).addTablePrefix("t_").entityBuilder().versionColumnName("version").logicDeleteColumnName("status").mapperBuilder().enableMapperAnnotation().build());
        generator.global(globalConfig().author("Jiahong.Li").build());

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
        generator.packageInfo(packageConfig().pathInfo(pathInfo).parent("com.demo.xyz").moduleName(module.substring(module.indexOf("-")+1,module.length())).other("vo").build());
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
