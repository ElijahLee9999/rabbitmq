package com.example.rabbitmq.demo.controller;

import com.example.rabbitmq.demo.entity.Student;
import com.example.rabbitmq.demo.service.IStudentService;
import com.example.rabbitmq.common.PageInfo;
import com.example.rabbitmq.common.QueryVo;
import com.example.rabbitmq.common.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * 学生表 Controller
 *
 * @author Elijah
 * @since 2020-05-29
 */
@Slf4j
@RestController
@RequestMapping("/demo/student")
public class StudentController {


    private final IStudentService iStudentService;

    public StudentController(IStudentService iStudentService) {
        this.iStudentService = iStudentService;
    }

  /**
   * 列表
   *
   * @param queryVo QueryVo
   * @return Result
   */
  @GetMapping("/")
    public Result list(QueryVo queryVo) {
        //查询列表数据
        log.debug("enter list.");

        IPage<Student> page = new Page<>(queryVo.getCurrentPage(), queryVo.getPageSize());
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        IPage<Student> result = iStudentService.page(page, wrapper);
        PageInfo pageInfo = new PageInfo(result);

        return Result.ok(pageInfo);
    }

    /**
    * 根据Id获取实体
    * @param id Long
    * @return Result
    */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        log.debug("query one by id.");
        Student student = iStudentService.getById(id);
        return Result.ok(student);
    }

    /**
    * 保存
    *
    * @param student Student
    * @return Result
    */
    @PostMapping("/")
    public Result save(@RequestBody Student student) {
        log.debug("enter save.");
        iStudentService.save(student);
        return Result.ok();
    }

    /**
    * 批量保存
    *
    * @param items List<Student>
    * @return Result
    */
    @PostMapping("/batch")
    public Result saveBatch(@RequestBody List<Student> items) {
        log.debug("enter saveBatch.");
        iStudentService.saveBatch(items);
        return Result.ok();
    }

    /**
    * 修改
    *
    * @param student Student
    * @return Result
    */
    @PutMapping("/")
    public Result update(@RequestBody Student student) {
        log.debug("enter update.");
        iStudentService.updateById(student);
        return Result.ok();
    }

    /**
    * 批量修改
    *
    * @param items List<Student>
    * @return Result
    */
    @PutMapping("/batch")
    public Result updateBatch(@RequestBody List<Student> items) {
        log.debug("enter updateBatch.");
        iStudentService.updateBatchById(items);
        return Result.ok();
    }

    /**
    * 删除
    *
    * @param studentIds Long[]
    * @return Result
    */
    @DeleteMapping("/")
    public Result delete(Long[] studentIds) {
        log.debug("enter delete.");
        iStudentService.removeByIds(Arrays.asList(studentIds));
        return Result.ok();
    }
}

