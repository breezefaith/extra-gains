import java.util.Iterator;

public class TreeQuery {

    private BinarySearchTree<Course> bst;

    public TreeQuery(BinarySearchTree<Course> bst) {

        this.bst = bst;

    } // end TreeQuery (constructor)

    public int findNumberOfStudentsInCourse(String s) {
        Iterator<Course> iterator = bst.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (s.equals(course.getCourseCode())) {
                return course.getNumStudents();
            }
        }
        return 0;
    } // end findNumberOfStudentsInCourse

    public ListADT<String> findAllCoursesTaughtBy(String s) {
        ArrayUnorderedList<String> res = new ArrayUnorderedList<>();
        Iterator<Course> iterator = bst.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            for (int i = 0; i < course.getProfessors().length; i++) {
                if (s.equals(course.getProfessors()[i])) {
                    res.addToRear(course.getCourseCode());
                }
            }
        }
        return res;

    } // end findAllCoursesTaughtBy

    public int findNumberOfFirstYearStudents() {
        int res = 0;
        Iterator<Course> iterator = bst.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getCourseCode().charAt(2) == '1') {
                res += course.getNumStudents();
            }
        }
        return res;

    } // end findAllFirstYearStudents

    public String findCourseWithMostStudents() {
        Course max = bst.getRoot().getElement();
        Iterator<Course> iterator = bst.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getNumStudents() > max.getNumStudents()) {
                max = course;
            }
        }
        return max.getCourseCode();
    } // end findCourseWithMostStudents

    public int computeTreeHeight() {
        return height(bst.getRoot()) - 1;
    } // end computeTreeHeight

    private int height(BinaryTreeNode<Course> root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.getLeft());
        int rh = height(root.getRight());
        return lh > rh ? lh + 1 : rh + 1;
    }

} // end TreeQuery (class)