package com.lyra.codegenerate.service;

import com.lyra.codegenerate.domain.entity.dto.MysqlDTO;

/**
 * @author qiaolonglong@tracetech.cn
 * @date 2023/5/18
 * @Description
 */
public interface IGenerateService {
    String entity(MysqlDTO mysqlDTO);

    String dao();
}
