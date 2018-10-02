package com.yamashiro0110.infrastructure;

import com.yamashiro0110.domain.models.Project;
import com.yamashiro0110.domain.models.ProjectRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    @Override
    public List<Project> findAll() {
        return Arrays.asList(
                new Project(1L, "hoge"),
                new Project(2L, "fuga"),
                new Project(3L, "piyo")
        );
    }
}
