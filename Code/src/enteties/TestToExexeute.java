package enteties;

public class TestToExexeute {

	private int testCode;
	private int testNum;
	private int testingType;
	private StudentTest studentsTestsLst;
	private boolean lock;
	private int timeExtension;
	private String date;
	private double average;
	private double median;
	private int[] distribution;

	public int getTestCode() {
		return this.testCode;
	}

	public void setTestCode(int testCode) {
		this.testCode = testCode;
	}

	public int getTestNum() {
		return this.testNum;
	}

	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}

	public int getTestingType() {
		return this.testingType;
	}

	public void setTestingType(int testingType) {
		this.testingType = testingType;
	}

	public StudentTest getStudentsTestsLst() {
		return this.studentsTestsLst;
	}

	public void setStudentsTestsLst(StudentTest studentsTestsLst) {
		this.studentsTestsLst = studentsTestsLst;
	}

	public boolean isLock() {
		return this.lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public int getTimeExtension() {
		return this.timeExtension;
	}

	public void setTimeExtension(int timeExtension) {
		this.timeExtension = timeExtension;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getAverage() {
		return this.average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getMedian() {
		return this.median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	public int[] getDistribution() {
		return this.distribution;
	}

	public void setDistribution(int[] distribution) {
		this.distribution = distribution;
	}

}