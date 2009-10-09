package games.stendhal.server.entity.mapstuff.game.movevalidator;

import games.stendhal.server.entity.item.token.BoardToken;
import games.stendhal.server.entity.mapstuff.game.GameBoard;
import games.stendhal.server.entity.player.Player;

/**
 * Is the movement target empty?
 *
 * @author hendrik
 */
public class MovementTargetEmptyValidator implements MoveValidator {

	public boolean validate(GameBoard board, Player player, BoardToken token, int xIndex, int yIndex) {
		// TODO Auto-generated method stub
		return true;
	}

}
