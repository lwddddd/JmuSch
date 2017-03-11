package obj;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class CourseList {
	private static int weekNum;
	private static List<Course> courseList1 = new ArrayList<Course>(); 
	private static List<Course> courseList2 = new ArrayList<Course>(); 
	private static List<Course> courseList3 = new ArrayList<Course>(); 
	private static List<Course> courseList4 = new ArrayList<Course>(); 
	private static List<Course> courseList5 = new ArrayList<Course>(); 
	private static List<Course> courseList6 = new ArrayList<Course>(); 
	private static List<Course> courseList7 = new ArrayList<Course>();
	public static void setWeekNum(int weekNum) {
		CourseList.weekNum = weekNum;
	}
	public static int getWeekNum() {

		return weekNum;
	}
	public List<Course> getCourseList1() {
		return courseList1;
	}
	public void setCourseList1(List<Course> courseList1) {
		this.courseList1 = courseList1;
	}
	public List<Course> getCourseList2() {
		return courseList2;
	}
	public void setCourseList2(List<Course> courseList2) {
		this.courseList2 = courseList2;
	}
	public List<Course> getCourseList3() {
		return courseList3;
	}
	public void setCourseList3(List<Course> courseList3) {
		this.courseList3 = courseList3;
	}
	public List<Course> getCourseList4() {
		return courseList4;
	}
	public void setCourseList4(List<Course> courseList4) {
		this.courseList4 = courseList4;
	}
	public List<Course> getCourseList5() {
		return courseList5;
	}
	public void setCourseList5(List<Course> courseList5) {
		this.courseList5 = courseList5;
	}
	public List<Course> getCourseList6() {
		return courseList6;
	}
	public void setCourseList6(List<Course> courseList6) {
		this.courseList6 = courseList6;
	}
	public List<Course> getCourseList7() {
		return courseList7;
	}
	public void setCourseList7(List<Course> courseList7) {
		this.courseList7 = courseList7;
	}

	public void adbSuit(){
		List<Course> adbCourseList = DataSupport.where("weekDay = ?","1").order("id").find(Course.class);
		this.setCourseList1(adbCourseList);
		adbCourseList = DataSupport.where("weekDay = ?","2").order("id").find(Course.class);
		this.setCourseList2(adbCourseList);
		adbCourseList = DataSupport.where("weekDay = ?","3").order("id").find(Course.class);
		this.setCourseList3(adbCourseList);
		adbCourseList = DataSupport.where("weekDay = ?","4").order("id").find(Course.class);
		this.setCourseList4(adbCourseList);
		adbCourseList = DataSupport.where("weekDay = ?","5").order("id").find(Course.class);
		this.setCourseList5(adbCourseList);
		adbCourseList = DataSupport.where("weekDay = ?","6").order("id").find(Course.class);
		this.setCourseList6(adbCourseList);
		adbCourseList = DataSupport.where("weekDay = ?","7").order("id").find(Course.class);
		this.setCourseList7(adbCourseList);

	}

}
