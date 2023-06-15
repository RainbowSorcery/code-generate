package ${packageName}.service.impl;

import ${packageName}.domain.entity.${className};
import ${packageName}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import ${packageName}.dao.${className}DAO;
import org.springframework.stereotype.Service;


/**
* @author qiaolonglong@tracetech.cn
* @date ${.now?string("yyyy/MM/dd")}
* @Description
*/
@Service
public class ${className}ServiceImpl implements ${className}Service {
    @Autowired
    private ${className}DAO ${classObjectName}DAO;

}
