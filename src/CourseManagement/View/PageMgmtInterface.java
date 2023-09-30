package CourseManagement.View;

import CourseManagement.Model.Page;

import java.util.Scanner;

public class PageMgmtInterface {
    public PageMgmtInterface(){

    }

    public Page createPage(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCourse Page Title:");
        String pageTitle = scanner.nextLine();
        System.out.println("Course Page Content:");
        String pageBody = scanner.nextLine();
        System.out.println("\n---Page Created Successfully---\n");
        return new Page(pageTitle, pageBody);
    }
}
