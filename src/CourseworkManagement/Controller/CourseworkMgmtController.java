package CourseworkManagement.Controller;

import CourseworkManagement.Model.Assignment;
import CourseworkManagement.View.CourseworkMgmtInterface;

public class CourseworkMgmtController {
    private CourseworkMgmtInterface cwi; // changed this to private

    /**
     * Constructor for the coursework management interface
     * @param a1 Assignment to be managed through the interface
     */
    public CourseworkMgmtController(Assignment a1) {
        this.cwi = new CourseworkMgmtInterface(); // initiates the interface
    }
}
