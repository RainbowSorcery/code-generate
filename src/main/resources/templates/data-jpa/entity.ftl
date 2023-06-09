package ${packageName}.domain.entity;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

<#list importPackageSet as item>
${item}
</#list>

@Entity
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
