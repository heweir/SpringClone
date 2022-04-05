package demo.annotation;

@CourseInfoAnnotation(courseName = "剑指面试",courseTag = "面试",courseProfile = "距离春招实习仅剩4个月")
public class ImoocCourse {

    @PersonInfoAnnotation(name = "he",language = {"c","java","go"})
    private String author;

    @CourseInfoAnnotation(courseName = "SpirngFramework", courseTag = "源码", courseProfile = "源码深度解析", courseIndex = 404)
    public void getCourseInfo(){

    }

}
