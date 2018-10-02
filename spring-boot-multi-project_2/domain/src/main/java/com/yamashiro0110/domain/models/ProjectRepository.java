package com.yamashiro0110.domain.models;

import java.util.List;

public interface ProjectRepository {

    List<Project> findAll();

}
