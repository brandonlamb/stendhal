package games.stendhal.server.maps.kalavan.citygardens;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

/**
 * Test buying ice cream.
 *
 * @author Martin Fuchs
 */
public class IceCreamSellerNPCTest extends ZonePlayerAndNPCTestImpl {

	private static final String ZONE_NAME = "0_kalavan_city_gardens";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();

		setupZone(ZONE_NAME, new IceCreamSellerNPC());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	public IceCreamSellerNPCTest() {
		super(ZONE_NAME, "Sam");
	}

	/**
	 * Tests for hiAndBye.
	 */
	@Test
	public void testHiAndBye() {
		final SpeakerNPC npc = getNPC("Sam");
		assertNotNull(npc);
		final Engine en = npc.getEngine();

		assertTrue(en.step(player, "hello"));
		assertEquals("Hi. Can I #offer you an icecream?", getReply(npc));

		assertTrue(en.step(player, "bye"));
		assertEquals("Bye, enjoy your day!", getReply(npc));
	}

	/**
	 * Tests for buyIceCream.
	 */
	@Test
	public void testBuyIceCream() {
		final SpeakerNPC npc = getNPC("Sam");
		final Engine en = npc.getEngine();

		assertTrue(en.step(player, "hi"));
		assertEquals("Hi. Can I #offer you an icecream?", getReply(npc));

		assertTrue(en.step(player, "job"));
		assertEquals("I sell delicious icecreams.", getReply(npc));

		assertTrue(en.step(player, "offer"));
		assertEquals("I sell icecream.", getReply(npc));

		assertTrue(en.step(player, "quest"));
		assertEquals("Mine's a simple life, I don't need a lot.", getReply(npc));

		assertTrue(en.step(player, "buy"));
		assertEquals("Please tell me what you want to buy.", getReply(npc));

		assertTrue(en.step(player, "buy dog"));
		assertEquals("Sorry, I don't sell dogs.", getReply(npc));

		assertTrue(en.step(player, "buy house"));
		assertEquals("Sorry, I don't sell houses.", getReply(npc));

		assertTrue(en.step(player, "buy someunknownthing"));
		assertEquals("Sorry, I don't sell someunknownthings.", getReply(npc));

		assertTrue(en.step(player, "buy a bunch of socks"));
		assertEquals("Sorry, I don't sell bunches of socks.", getReply(npc));

		assertTrue(en.step(player, "buy icecream"));
		assertEquals("An icecream will cost 30. Do you want to buy it?", getReply(npc));

		assertTrue(en.step(player, "no"));
		assertEquals("Ok, how else may I help you?", getReply(npc));

		assertTrue(en.step(player, "buy icecream"));
		assertEquals("An icecream will cost 30. Do you want to buy it?", getReply(npc));

		assertTrue(en.step(player, "yes"));
		assertEquals("Sorry, you don't have enough money!", getReply(npc));

		// equip with enough money to buy two ice creams
		assertTrue(equipWithMoney(player, 60));

		assertTrue(en.step(player, "buy three icecreams"));
		assertEquals("3 icecreams will cost 90. Do you want to buy them?", getReply(npc));

		assertTrue(en.step(player, "yes"));
		assertEquals("Sorry, you don't have enough money!", getReply(npc));

		assertTrue(en.step(player, "buy icecream"));
		assertEquals("An icecream will cost 30. Do you want to buy it?", getReply(npc));

		assertFalse(player.isEquipped("icecream"));

		assertTrue(en.step(player, "yes"));
		assertEquals("Congratulations! Here is your icecream!", getReply(npc));
		assertTrue(player.isEquipped("icecream", 1));

		assertTrue(en.step(player, "buy icecream"));
		assertEquals("An icecream will cost 30. Do you want to buy it?", getReply(npc));

		assertTrue(en.step(player, "yes"));
		assertEquals("Congratulations! Here is your icecream!", getReply(npc));
		assertTrue(player.isEquipped("icecream", 2));

		assertTrue(en.step(player, "buy 0 icecreams"));
		assertEquals("Sorry, how many icecreams do you want to buy?!", getReply(npc));
		
		// buying one icecream by answering "yes" to npc's greeting
		assertTrue(equipWithMoney(player, 30));		
		assertTrue(en.step(player, "bye"));
		assertEquals("Bye, enjoy your day!", getReply(npc));
		assertTrue(en.step(player, "hi"));
		assertEquals("Hi. Can I #offer you an icecream?", getReply(npc));		
		assertTrue(en.step(player, "yes"));
		assertEquals("An icecream will cost 30. Do you want to buy it?", getReply(npc));
		assertTrue(en.step(player, "yes"));
		assertEquals("Congratulations! Here is your icecream!", getReply(npc));
		assertTrue(player.isEquipped("icecream", 3));		
	}

	/**
	 * Tests for sellIceCream.
	 */
	@Test
	public void testSellIceCream() {
		final SpeakerNPC npc = getNPC("Sam");
		final Engine en = npc.getEngine();

		assertTrue(en.step(player, "hi Sam"));
		assertEquals("Hi. Can I #offer you an icecream?", getReply(npc));

		// Currently there are no response to sell sentences for Sam.
		assertFalse(en.step(player, "sell"));
	}

}
