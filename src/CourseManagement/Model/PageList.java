package CourseManagement.Model;

import java.util.ArrayList;

public class PageList {
    private Page page;
    private ArrayList<Page> pages = new ArrayList<>();

    public PageList() {
        if (this.getPages().isEmpty()) {
            createPageList();
        }
    }

    public void createPageList() {
        for (int i = 0; i <= 5; i++) {
            this.setPage(new Page("Page " + i));
            this.getPages().add(this.getPage());
        }
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }
}
