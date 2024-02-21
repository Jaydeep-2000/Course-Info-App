package com.teamtechtalk.courseinfo.repository;

import com.teamtechtalk.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {
    void saveCourse(Course course) throws RepositoryException;

    List<Course> getAllCourses() throws RepositoryException;

    void addNotes(String id, String notes) throws RepositoryException;

    static CourseRepository openCourseRepoitory(String databaseFile){
        return new CourseJdbcRepository(databaseFile);
    }
}
