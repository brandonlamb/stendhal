/* $Id$ */
/***************************************************************************
 *                      (C) Copyright 2003 - Marauroa                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.entity;

import games.stendhal.common.Rand;
import games.stendhal.server.events.TurnListener;
import games.stendhal.server.events.TurnNotifier;

import marauroa.common.game.RPClass;

/**
 * Represents a blood puddle that is left on the ground after an entity
 * was injured or killed.
 */
public class Blood extends PassiveEntity implements TurnListener {
	/**
	 * Blood will disappear after so many seconds.
	 */
	public static final int DEGRADATION_TIMEOUT = 30 * 60; // 30 minutes

	public static void generateRPClass() {
		RPClass blood = new RPClass("blood");
		blood.isA("entity");
		blood.add("class", RPClass.STRING);
		blood.add("amount", RPClass.BYTE);
	}


	/**
	 * Create a blood entity.
	 */
	public Blood() {
		this("red", Rand.rand(4));
	}


	/**
	 * Create a blood entity.
	 *
	 * @param	clazz		The class of blood.
	 * @param	amount		The amount of blood.
	 */
	public Blood(final String clazz, final int amount) {
		put("type", "blood");
		put("class", clazz);
		put("amount", amount);

		TurnNotifier.get().notifyInSeconds(DEGRADATION_TIMEOUT, this);
	}


	//
	// Entity
	//

	/**
	 * Get the entity description.
	 *
	 * @return	The description text.
	 */
	@Override
	public String describe() {
		return ("You see a pool of blood.");
	}


	//
	// TurnListener
	//

	/**
	 * This method is called when the turn number is reached.
	 * NOTE: The <em>message</em> parameter is deprecated.
	 *
	 * @param	currentTurn	The current turn number.
	 * @param	message		The string that was used.
	 */
	public void onTurnReached(int currentTurn, String message) {
		getZone().remove(this);
	}
}
