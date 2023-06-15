package com.lyra.codegenerate.service;

import com.lyra.codegenerate.domain.dto.MysqlDTO;

/**
 * @author qiaolonglong@tracetech.cn
 * @date 2023/5/18
 * @Description
 */
public interface IGenerateService {
    String entity(MysqlDTO mysqlDTO);

    String dao(MysqlDTO mysqlDTO);

    String service(MysqlDTO mysqlDTO);

    String controller(MysqlDTO mysqlDTO);
}
