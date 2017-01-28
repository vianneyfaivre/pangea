package re.vianneyfaiv.pangea.business;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import re.vianneyfaiv.pangea.domain.Country;
import re.vianneyfaiv.pangea.service.CountryRepository;
import re.vianneyfaiv.pangea.web.views.QuestionView;

@Configuration
public class QuizBusiness {

	@Autowired
	private CountryRepository countryRepository;

	public QuestionView generateQuestion(int proposalsCount) {
		Assert.isTrue(proposalsCount > 0);

		// Get min & max IDs
		int minId = 1;
		int maxId = (int) this.countryRepository.count();

		Assert.isTrue(proposalsCount <= maxId);

		// Generate proposals
		Set<Country> proposals = new HashSet<>(proposalsCount);

		while (proposals.size() < proposalsCount) {
			Country c = this.countryRepository.findOne(RandomUtils.nextInt(minId, maxId));

			if (c.hasFlag()) {
				proposals.add(c);
			}
		}

		// Take an answer from proposals
		int answerItemIndex = RandomUtils.nextInt(0, proposals.size());
		Country answer = proposals.stream().collect(Collectors.toList()).get(answerItemIndex);

		return new QuestionView(proposals, answer);
	}
}
