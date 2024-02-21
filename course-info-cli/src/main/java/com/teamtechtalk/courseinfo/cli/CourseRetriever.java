package com.teamtechtalk.courseinfo.cli;

import com.teamtechtalk.courseinfo.cli.service.CourseRetrievalService;
import com.teamtechtalk.courseinfo.cli.service.CourseStorageService;
import com.teamtechtalk.courseinfo.cli.service.PluralsightCourse;
import com.teamtechtalk.courseinfo.repository.CourseRepository;
import com.teamtechtalk.courseinfo.repository.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.function.Predicate.not;

public class CourseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String... args) {
        LOG.info("CourseRetriever Started!");
        if(args.length == 0){
            LOG.warn("Please provide an author name as first argument.");
            return;
        }
        try {
            retrieveCourses(args[0]);
        }catch(Exception  | RepositoryException e){
            LOG.error("Unexpected error", e);
        }
    }

    private static void retrieveCourses(String authorId) throws RepositoryException {
        LOG.info("Retrieving courses for author '{}'", authorId);

        CourseRetrievalService courseRetrievalService= new CourseRetrievalService();
        CourseRepository courseRepository = CourseRepository.openCourseRepoitory("./courses.db");
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);

        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesFor(authorId)
                        .stream()
                        .filter(not(PluralsightCourse::isRetired))
                        .toList();
        LOG.info("Retrieved the following {} courses {}", coursesToStore.size(), coursesToStore);
        courseStorageService.storePluralsightCourses(coursesToStore);
        LOG.info("Courses successfully stored");
    }
}
