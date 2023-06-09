package enteties;

public class Course {
    //in DB:
	private String number;
	private String name;
	private String subjectNum;
	//not in DB:
	
	//for FX:
	
	/**
	 * empty constructor.
	 */
	public Course() {super();}
	
	/**
	 * @param number
	 * @param name
	 * @param subjectNum
	 */
	public Course(String number, String name, String subjectNum) {
		this.number = number;
		this.name = name;
		this.subjectNum = subjectNum;
	}
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the subjectNum
	 */
	public String getSubjectNum() {
		return subjectNum;
	}
	
	/**
	 * @param subjectNum the subjectNum to set
	 */
	public void setSubjectNum(String subjectNum) {
		this.subjectNum = subjectNum;
	}

	@Override
	public boolean equals(Object obj) {
		Course c = (Course) obj;
		return number.equals(c.getNumber());
	}
	
	@Override
	public String toString() {
		return "Course [number=" + number + ", name=" + name + ", subjectNum=" + subjectNum + "]";
	}
}
