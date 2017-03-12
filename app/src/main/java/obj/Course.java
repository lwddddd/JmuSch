package obj;
import org.litepal.crud.DataSupport;

public class Course extends DataSupport{
	private String weekDay;
	private String classNum;
	private String courseName="classes";
	private String loca="location";
	private String teacher="teacher";
	private String zhoushu="zhoushu";
	private String jieshu="jieshu";
	private String time1="time1";
	private String time2="time2";
	private String which="which";

	public Course()
	{
		this.weekDay="";
		this.courseName="";
		this.loca="";
		this.teacher="";
		this.zhoushu="";
		this.jieshu="";
		this.time1="";
		this.time2="";
		this.which="";


	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	public String getWeekDay() {

		return weekDay;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getClassNum() {

		return classNum;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getLoca() {
		return loca;
	}
	public void setLoca(String loca) {
		this.loca = loca;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getZhoushu() {
		return zhoushu;
	}
	public void setZhoushu(String zhoushu) {
		this.zhoushu = zhoushu;
	}
	public String getJieshu() {
		return jieshu;
	}
	public void setJieshu(String jieshu) {
		this.jieshu = jieshu;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	public String getWhich() {
		return which;
	}
	public void setWhich(String which) {
		this.which = which;
	}
	public void saveAdb()
	{
		String wD=this.getWeekDay();
		String cN=this.getClassNum();
		//if(!wD.equals("")&&!cN.equals(""))
		this.updateAll("weekDay = ? and classNum=?",wD,cN);

	}
	public void reSet(){
		this.setToDefault("courseName");
		this.setToDefault("loca");
		this.setToDefault("teacher");

	}
}