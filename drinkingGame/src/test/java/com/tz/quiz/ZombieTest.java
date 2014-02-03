package com.tz.quiz;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.quiz.domain.Output;
import com.tz.quiz.domain.Roll;
import com.tz.quiz.domain.Player;
import com.tz.quiz.service.DrinkingService;
import com.tz.test.TestSupport;

/**
 * 
 * @author
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
// @Ignore
public class PlayerTest extends TestSupport {

	@Test
	public void runtTest() {
		DrinkingService service = new DrinkingService();

		List<Roll> rolls = new ArrayList<Roll>();
		List<Output> outputs = null;

		given("with @ rolls,", "3");
		{

			Roll roll1 = new Roll();
			roll1.addPlayer(new Player(1, 0, 0));
			roll1.addPlayer(new Player(-1, 0, 0));
			roll1.addPlayer(new Player(10, 10, 1000));
			roll1.addPlayer(new Player(10, -10, 1000));
			rolls.add(roll1);

			Roll roll2 = new Roll();
			roll2.addPlayer(new Player(1, 1, 0));
			roll2.addPlayer(new Player(2, 2, 0));
			roll2.addPlayer(new Player(3, 3, 0));
			rolls.add(roll2);

			Roll roll3 = new Roll();
			roll3.addPlayer(new Player(10, 10, 1000));
			roll3.addPlayer(new Player(-10, 10, 1000));
			roll3.addPlayer(new Player(10, -10, 1000));
			roll3.addPlayer(new Player(-10, -10, 1000));
			roll3.addPlayer(new Player(20, 20, 2000));
			rolls.add(roll3);
		}

		when("run service.drinkingPlayer");
		{
			outputs = service.drinkingPlayer(rolls);
		}

		for (int j = 0; j < outputs.size(); j++) {

			Output output = outputs.get(j);
			System.out.println(output.getlog());

			if (j == 0) {
				then(j + " is 3.", j + "'s result == 3");
				{
					assertThat(output.getDrinkingCnt(), is(3));
				}
			} else if (j == 1) {
				then(j + " is 2.", j + "'s result == 2");
				{
					assertThat(output.getDrinkingCnt(), is(2));
				}
			} else if (j == 2) {
				then(j + " is 2.", j + "'s result == 2");
				{
					assertThat(output.getDrinkingCnt(), is(2));
				}
			}
		}
	}

}