package com.tz.quiz;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tz.quiz.domain.Logger;
import com.tz.quiz.domain.Player;
import com.tz.quiz.domain.Roll;
import com.tz.quiz.service.DrinkingService;
import com.tz.quiz.support.Constants;
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

		// 1) making game
		Roll roll = new Roll();
		List<Player> players = new ArrayList<Player>();

		Constants.radomPlay = false;
		Constants.debug = true;

		given("with @ players,", "3");
		{
			roll.setMaxDrinkingCnt(2);
			roll.setPausetime(2);

			// 2) define players
			Player player1 = new Player("Alex", 2);
			Logger.debug("0 / ready:" + player1.getName() + " ("
					+ player1.getDrinkingTime() + ")");
			players.add(player1);
			Player player2 = new Player("Bob", 3);
			Logger.debug("0 / ready:" + player2.getName() + " ("
					+ player2.getDrinkingTime() + ")");
			players.add(player2);
			Player player3 = new Player("Chris", 5);
			Logger.debug("0 / ready:" + player3.getName() + " ("
					+ player3.getDrinkingTime() + ")");
			players.add(player3);
		}

		when("run service.drinkingPlayer");
		{
			roll = service.playDrinkingGame(roll, players);
		}

		then(roll.getPlayers().size() + "'s winner is only 1.");
		{
			assertThat(roll.getPlayers().size(), is(1));
		}
	}

}