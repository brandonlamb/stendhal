package games.stendhal.server.maps.semos;

import java.util.LinkedList;
import java.util.List;

import games.stendhal.server.StendhalRPWorld;
import games.stendhal.server.StendhalRPZone;
import games.stendhal.server.entity.npc.NPCList;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.pathfinder.Path;
import marauroa.common.game.IRPZone;

public class SemosPlainsNorth {
	private NPCList npcs = NPCList.get();

	public void build() {
		StendhalRPWorld world = StendhalRPWorld.get();

		buildSemosNorthPlainsArea((StendhalRPZone) world
				.getRPZone(new IRPZone.ID("0_semos_plains_n")));
	}

	private void buildSemosNorthPlainsArea(StendhalRPZone zone) {

		SpeakerNPC npc = new SpeakerNPC("Plink") {
			@Override
			protected void createPath() {
				List<Path.Node> nodes = new LinkedList<Path.Node>();
				nodes.add(new Path.Node(36, 108));
				nodes.add(new Path.Node(37, 108));
				nodes.add(new Path.Node(37, 105));
				nodes.add(new Path.Node(42, 105));
				nodes.add(new Path.Node(42, 111));
				nodes.add(new Path.Node(48, 111));
				nodes.add(new Path.Node(47, 103));
				nodes.add(new Path.Node(47, 100));
				nodes.add(new Path.Node(53, 100));
				nodes.add(new Path.Node(53, 90));
				nodes.add(new Path.Node(49, 90));
				nodes.add(new Path.Node(49, 98));
				nodes.add(new Path.Node(46, 98));
				nodes.add(new Path.Node(46, 99));
				nodes.add(new Path.Node(36, 99));
				
				setPath(nodes, true);
			}
		
			@Override
			protected void createDialog() {
				addGreeting();
				addJob("I play all day.");
				addHelp("Be careful, there are some wolves east of here!");
				addGoodbye();
			}
		};
		npcs.add(npc);
		zone.assignRPObjectID(npc);
		npc.put("class", "childnpc");
		npc.set(36, 108);
		npc.initHP(100);
		zone.addNPC(npc);
	}

}
