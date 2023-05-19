package com.lyra.codegenerate.utils;

import com.lyra.codegenerate.CodeGenerateApplication;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author qiaolonglong@tracetech.cn
 * @date 2023/5/18
 * @Description
 */
@Component
public class FreeMakerUtils {
    public String writeToTemplate(Configuration configuration, String templatePath, String templateName, Map<String, Object> templatePramMap) throws TemplateException, IOException {
        configuration.setClassForTemplateLoading(this.getClass(), templatePath);
        configuration.setDefaultEncoding("UTF-8");

        StringWriter stringWriter = new StringWriter();
        Template template = configuration.getTemplate(templateName);
        template.process(templatePramMap, stringWriter);

        return stringWriter.toString();
    }
}
