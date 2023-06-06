package ${packageName}.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import ${packageName}.common.utils.Result;
import ${packageName}.domain.entity.Attachment;
import ${packageName}.service.${className}Service;


import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

${item.annotation}
@RestController
@RequestMapping("${packaeName}")
