package re.vianneyfaiv.pangea.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import re.vianneyfaiv.pangea.business.QuizBusiness;
import re.vianneyfaiv.pangea.web.views.QuestionView;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuizBusiness quizBusiness;

	@RequestMapping("/quiz/{number}")
	public QuestionView getQuestion(@PathVariable int number) {
		return this.quizBusiness.generateQuestion(number);
	}
}
