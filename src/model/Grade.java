package model;

public class Grade {
	private String courseNo;
	private String courseName;
	private String courseType;
	private String courseCredit;
	private String firstMark;
	private String secondMark;

	public Grade(String _courseNo, String _courseName, String _courseType,
			String _courseCredit, String _firstMark, String _secondMark) {
		courseNo = _courseNo;
		courseName = _courseName;
		courseType = _courseType;
		courseCredit = _courseCredit;
		firstMark = _firstMark;
		secondMark = _secondMark;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(String courseCredit) {
		this.courseCredit = courseCredit;
	}

	public String getFirstMark() {
		return firstMark;
	}

	public void setFirstMark(String firstMark) {
		this.firstMark = firstMark;
	}

	public String getSecondMark() {
		return secondMark;
	}

	public void setSecondMark(String secondMark) {
		this.secondMark = secondMark;
	}

}
