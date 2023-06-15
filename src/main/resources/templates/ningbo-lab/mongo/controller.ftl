package ${packageName}.controller;

import org.springframework.beans.factory.annotation.Autowired;

import ${packageName}.common.utils.CommonResult;
import ${packageName}.domain.entity.${className};
import ${packageName}.service.${className}Service;



import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
* @author qiaolonglong@tracetech.cn
* @date ${.now?string("yyyy/MM/dd")}:
* @Description
*/
<#--${item.annotation}-->
@RestController
@RequestMapping("/${subClassName}")
public class ${className}Controller {
    @Autowired
    private ${className}Service ${subClassName}Service;
}