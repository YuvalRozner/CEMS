package enteties;


public class QuestionToShow {
	//from studentTest
		private String answerOfStudent;
		private String pointOfStudent;
		//fromQuestion
		private String numberInTest;//addbylior
		private String point;
		private String question;
		private String correctAns;
		
		public QuestionToShow(String answerOfStudent,String pointOfStudent,String numberInTest,String point,String question,String correctAns) {
			this.answerOfStudent=answerOfStudent;
			this.pointOfStudent=pointOfStudent;
			this.numberInTest=numberInTest;
			this.point=point;
			this.question=question;
			this.correctAns=correctAns;

		}
		//////answer
		public String getAnswerOfStudent() {
			return answerOfStudent;
		}
		public void setAnswerOfStudent(String answerOfStudent) {
			this.answerOfStudent=answerOfStudent;
		}
		/////pointOfQuestion
		public String getPointOfStudent() {
			return pointOfStudent;
		}
		public void setPointOfStudent(String pointOfStudent) {
			this.pointOfStudent=pointOfStudent;
		}
		//////numberInTest
		public String getNumberInTest() {
			return numberInTest;
		}
		public void setNumberInTest(String numberInTest) {
			this.numberInTest=numberInTest;
		}
		/////point
		public String getPoint() {
			return point;
		}
		public void setPoint(String point) {
			this.point=point;
		}
		///////question
		public String getQuestion() {
			return question;
		}
		public void setQuestion(String question) {
			this.question=question;
		}
		//////correctAns
		public String getCorrectAns() {
			return correctAns;
		}
		public void setCorrectAns(String correctAns) {
			this.correctAns=correctAns;
		}
		
		
}