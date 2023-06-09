package ${packageName}.service.impl;

import ${packageName}.domain.entity.${className};
import ${packageName}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import ${packageName}.dao.${className}DAO;
import org.springframework.stereotype.Service;


/**
* @author qiaolonglong@tracetech.cn
* @date 2023/4/26
* @Description
*/
@Service
public class ${className}ServiceImpl implements ${className}Service {
    @Autowired
    private ${className}DAO ${classObjectName}DAO;

}
