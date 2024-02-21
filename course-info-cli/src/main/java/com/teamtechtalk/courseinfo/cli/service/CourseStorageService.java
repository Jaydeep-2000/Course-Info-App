package com.teamtechtalk.courseinfo.cli.service;

import com.teamtechtalk.courseinfo.domain.Course;
import com.teamtechtalk.courseinfo.repository.CourseRepository;
import com.teamtechtalk.courseinfo.repository.RepositoryException;

import java.util.List;
import java.util.Optional;

public class CourseStorageService {
    private static final String PS_BASE_URL = "https://app.pluralsight.com";

    private final CourseRepository courseRepository;

    public CourseStorageService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public void storePluralsightCourses(List<PluralsightCourse> psCourses) throws RepositoryException {
        for(PluralsightCourse psCourse: psCourses){
            Course course = new Course(psCourse.id(),
                    psCourse.title(),
                    psCourse.durationInMinutes(),
                    PS_BASE_URL + psCourse.contentUrl(),
                    Optional.empty());
            courseRepository.saveCourse(course);
        }
    }

}
