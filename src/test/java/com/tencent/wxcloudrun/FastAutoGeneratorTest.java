package com.tencent.wxcloudrun;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.sql.SQLException;
import java.util.Collections;

public class FastAutoGeneratorTest {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://sh-cynosdbmysql-grp-4maus0v0.sql.tencentcdb.com:24858/springboot_demo", "root", "kTDazPk3");

    /**
     * 执行 run
     */
    public static void main(String[] args) throws SQLException {
//        before();
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称"))
                        .enableSwagger()
                        .outputDir("/Users/apple/IdeaProjects/martialArtsSchool/src/main/java"))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent("com.tencent.wxcloudrun")
                        .pathInfo(Collections.singletonMap(
                                OutputFile.xml, "/Users/apple/IdeaProjects/martialArtsSchool/src/main/resources/mapper")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(scanner.apply("请输入表名，多个表名用,隔开")))
                .execute();
    }
}
