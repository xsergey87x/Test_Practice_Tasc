package com.example.demo.web;

import com.example.demo.DTO.entityDto.TeacherDTO;
import com.example.demo.DTO.entityDto.TeacherFullModelDTO;
import com.example.demo.DTO.serviceDto.Mapper;
import com.example.demo.entity.Group;
import com.example.demo.entity.Teacher;
import com.example.demo.exception.CustomException;
import com.example.demo.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherRestController {

private final TeacherService teacherService;
private final Mapper mapper;

  public TeacherRestController(TeacherService teacherService, Mapper mapper) {
    this.teacherService = teacherService;
    this.mapper = mapper;
  }

  @PostMapping(value = "/saveTeacher")
  public ResponseEntity<Teacher> addTeacher(@Validated @RequestBody TeacherFullModelDTO teacherFullModelDTO)
  {
      return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(mapper.mapFullDtoToEntity(teacherFullModelDTO)));
  }

  @PostMapping(value = "/updateTeacher")
  public Teacher updateTeacher(@RequestBody Teacher teacher)
  {
    return teacherService.updateTeacher(teacher);
  }

  @PostMapping(value = "/addGroupToTeacher/{teacherId}")
  public Teacher addGroupToTeacher(@RequestBody Group group, @PathVariable Long teacherId)
  {
    return teacherService.addGroupToTeacher(group,teacherId);
  }

  @GetMapping(value = "/removeGroupToTeacher/{groupId}/{teacherId}")
  public Teacher removeGroupFromTeacher(@PathVariable Long groupId, @PathVariable Long teacherId)
  {
    return teacherService.deleteGroupFromTeacher(groupId,teacherId);
  }

  @GetMapping(value = "/getAllGroupByTeacher/{teacherId}")
  public List<Group> getAllGroupByTeacher(@PathVariable Long teacherId)
  {
    return teacherService.getAllGroupByTeacher(teacherId);
  }

  @GetMapping(value = "/getAll")
  public List<Teacher> getAllTeacher()  {return teacherService.getAllTeacher();}

  @GetMapping(value = "/getAllDto")
  public List<TeacherDTO> getAllDtoTeacher()  {return teacherService.getAllTeacher().stream().map(mapper::mapEntityToDto).collect(Collectors.toList());}

  @GetMapping(value = "/getById")
  public ResponseEntity getTeacherById(@RequestParam("id") @Min(0) Long id)
  {
    Optional<Teacher> teacher = teacherService.getTeacherById(id);
    if (teacher.isEmpty())
    {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher with Id " + id + " not found");
    }
    return new ResponseEntity(teacher, HttpStatus.OK);
  }

  @GetMapping(value = "/getDtoById")
  public TeacherDTO getAllDtoTeacher(@RequestParam("id") @Min(0) Long id)
  {
    return mapper.mapEntityToDto((Teacher)teacherService.getTeacherById(id).get());
  }

  @GetMapping(value = "/saveTestTeacher")
  public Teacher createTestTeacher()
  {
    return teacherService.createTeacher(new Teacher("Sem","Smith","male",35));
  }
}
