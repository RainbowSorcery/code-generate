package ${packageName}.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("${tableName}")
public class ${className} {
<#list filedList as item>
    ${item.annotation}
    private ${item.type} ${item.name};


    public Long get${item.name} {
    return ${item.name};
    }

    public void setId(Long id) {
    this.id = ${item.name};
    }
</#list>


}
