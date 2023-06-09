package enteties;

public class Request {
	//in DB:
	private Integer testCode;
	private Integer lecturerId;
	private Integer hodId;
	private Integer duration;
	private String explanetion;
	//not in DB:
	
	//for FX:
	
	
	/**
	 * empty constructor.
	 */
	public Request() {super();}

	/**
	 * @param testCode
	 * @param lecurerId
	 * @param hodId
	 * @param duration
	 * @param explanetion
	 */
	public Request(Integer testCode, Integer lecurerId, Integer hodId, Integer duration, String explanetion) {
		this.testCode = testCode;
		this.lecturerId = lecurerId;
		this.hodId = hodId;
		this.duration = duration;
		this.explanetion = explanetion;
	}
	
	/**
	 * @return the testCode
	 */
	public Integer getTestCode() {
		return testCode;
	}

	/**
	 * @param testCode the testCode to set
	 */
	public void setTestCode(Integer testCode) {
		this.testCode = testCode;
	}

	/**
	 * @return the lecurerId
	 */
	public Integer getLecturerId() {
		return lecturerId;
	}

	/**
	 * @param lecurerId the lecurerId to set
	 */
	public void setLecurerId(Integer lecurerId) {
		this.lecturerId = lecurerId;
	}

	/**
	 * @return the hodId
	 */
	public Integer getHodId() {
		return hodId;
	}

	/**
	 * @param hodId the hodId to set
	 */
	public void setHodId(Integer hodId) {
		this.hodId = hodId;
	}

	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * @return the explanetion
	 */
	public String getExplanetion() {
		return explanetion;
	}

	/**
	 * @param explanetion the explanetion to set
	 */
	public void setExplanetion(String explanetion) {
		this.explanetion = explanetion;
	}
	
	@Override
	public boolean equals(Object obj) {
		Request r = (Request) obj;
		return ( testCode.equals(r.getTestCode()) && lecturerId.equals(r.getLecturerId()));
	}

	@Override
	public String toString() {
		return "Request [testCode=" + testCode + ", lecurerId=" + lecturerId + ", hodId=" + hodId + ", duration=" + duration + ", explanetion=" + explanetion + "]";
	}
	
}
