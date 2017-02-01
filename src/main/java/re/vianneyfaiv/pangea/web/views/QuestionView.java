package re.vianneyfaiv.pangea.web.views;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import re.vianneyfaiv.pangea.domain.Country;

/**
 * A question contains a list of proposals and an answer
 */
public class QuestionView {

	private Set<SimpleCountryView> proposals;
	private SimpleCountryView answer;

	public QuestionView(@NotNull Set<Country> proposals, @NotNull Country answer) {
		Assert.notEmpty(proposals);
		Assert.isTrue(proposals.contains(answer));

		this.proposals = proposals.stream().map(SimpleCountryView::new).collect(Collectors.toSet());
		this.answer = new SimpleCountryView(answer);
	}

	public boolean verifyUserInput(Country selectedAnswer) {
		return this.answer.equals(selectedAnswer);
	}

	public Set<SimpleCountryView> getProposals() {
		return this.proposals;
	}

	public SimpleCountryView getAnswer() {
		return this.answer;
	}

	@Override
	public String toString() {
		return "QuestionView [proposals=" + this.proposals + ", answer=" + this.answer + "]";
	}
}
