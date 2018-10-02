package com.yamashiro0110;

import com.yamashiro0110.domain.models.Project;
import com.yamashiro0110.domain.models.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Resource
    private ProjectRepository projectRepository;

    @GetMapping
    List<Project> projects() {
        return this.projectRepository.findAll();
    }

}
