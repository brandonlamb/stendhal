/**
 *
 */
package games.stendhal.server.maps;

import games.stendhal.server.StendhalRPWorld;
import marauroa.common.game.RPObject;

public class MockStendlRPWorld extends StendhalRPWorld {

	@Override
	public void modify(RPObject object) {
	}

	public static StendhalRPWorld get() {
		if (instance == null) {
			instance = new MockStendlRPWorld();
		}
		return instance;
	}

	@Override
	protected void initialize() {

	}

	@Override
	public int getTurnsInSeconds(int seconds) {
		return seconds;
	}


}