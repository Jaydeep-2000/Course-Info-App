package com.teamtechtalk.courseinfo.cli;

public class CourseRetriever {

    public static void main(String... args) {
        System.out.println("CourseRetriever Started!");
        if(args.length == 0){
            System.out.println("Please provide an author name as first argument.");
            return;
        }
        try {
            retrieveCourses(args[0]);
        }catch(Exception e){
            System.out.println("Unexpected error");
            e.printStackTrace();
        }
    }

    private static void retrieveCourses(String authorId) {
        System.out.println("Retrieving courses for author " + authorId);
    }
}
