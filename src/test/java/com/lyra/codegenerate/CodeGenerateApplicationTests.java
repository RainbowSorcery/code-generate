package com.lyra.codegenerate;

import cn.hutool.core.util.StrUtil;
import com.lyra.codegenerate.service.IGenerateService;
import com.lyra.codegenerate.utils.FreeMakerUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class CodeGenerateApplicationTests {
    @Autowired
    private IGenerateService generateService;

    @Autowired
    private FreeMakerUtils freeMakerUtils;

    @Autowired
    private Configuration configuration;

    @Test
    public void test() {
        String packageName = "com.tracetech.ningbolab.core";
        String className = "TestFactor";

        Map<String, Object> templatePramMap = new HashMap<>();
        templatePramMap.put("packageName", packageName);
        templatePramMap.put("className", className);
        templatePramMap.put("classObjectName", StrUtil.lowerFirst(className));

        try {
            String service = freeMakerUtils.writeToTemplate(configuration, "/templates/ningbo-lab/mongo", "serviceImpl.ftl", templatePramMap);
            System.out.println(service);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void contextLoads() throws Exception {
        String packageName = "package com.tracetech.ningbolab.core";
        String className = "ProcessRecord";

        Map<String, Object> templatePramMap = new HashMap<>();
        templatePramMap.put("packageName", packageName);
        templatePramMap.put("className", className);

        String name = "testItemId\n" +
                "testConclusion\n" +
                "testItem\n" +
                "equipTypeCode";
        String[] nameArr = name.split("\n");

        String ano = "试验项目id\n" +
                "试验结论\n" +
                "试验项目名称\n" +
                "设备类型编码";
        String[] anoArr = ano.split("\n");

        List<Code> list = new ArrayList<>();

        for (int i = 0; i < anoArr.length; i++) {
            String s = anoArr[i];
            String s1 = nameArr[i];
            Code code = new Code();
            code.setAno(s);
            code.setName(s1);
            list.add(code);
        }

        templatePramMap.put("filedList", list);


        String service = freeMakerUtils.writeToTemplate(configuration, "/templates/", "test.ftl", templatePramMap);


        FileOutputStream fileOutputStream = new FileOutputStream("d://" + className + "ServiceImpl.java");
        fileOutputStream.write(service.getBytes());

        fileOutputStream.flush();
        fileOutputStream.close();
    }

}

