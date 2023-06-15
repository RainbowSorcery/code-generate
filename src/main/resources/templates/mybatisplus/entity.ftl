package ${packageName}.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

<#list importPackageSet as item>
${item}
</#list>

@TableName("${tableName}")
public class ${className} {
<#list filedList as item>
    <#list item.annotation as ano>
    ${ano}
    </#list>
    private ${item.type} ${item.name};

</#list>

<#list filedList as item>
    public ${item.type}  get${item.getSetName}() {
        return this.${item.name};
    }

    public void set${item.getSetName}(${item.type} ${item.name}) {
        this.${item.name} = ${item.name};
    }
</#list>

}
