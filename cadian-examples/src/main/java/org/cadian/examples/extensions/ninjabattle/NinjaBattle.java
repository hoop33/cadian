package org.cadian.examples.extensions.ninjabattle;

import org.cadian.examples.extensions.ninjabattle.attack.Attack;
import org.cadian.examples.extensions.ninjabattle.ninja.Ninja;

/**
 * Ninja battle simulator!
 * 
 * @author Joshua Hornsby
 */
public class NinjaBattle {

	public static void main(String... args) {
		System.out.println(fight(new Ninja("Bob", "scrub", 198), new Ninja("Joe", "brown-belt", 178)));
		System.out.println(fight(new Ninja("Max", "brown-belt", 199), new Ninja("Sam", "brown-belt", 256)));
		System.out.println(fight(new Ninja("Nate", "brown-belt", 204), new Ninja("Tom", "black-belt", 315)));
		System.out.println(fight(new Ninja("Ultimate Ninja", "black-belt", 548), new Ninja("Tom", "black-belt", 315)));
		System.out.println(fight(new Ninja("Ultimate Ninja", "black-belt", 548), new Ninja("Pai Mei", "white-lotus-master", 1024)));
	}
	
	public static String fight(Ninja a, Ninja b) {
		StringBuffer buffer = new StringBuffer();
		boolean odd = true;
		while(a.getLife() > 0 && b.getLife() > 0) {
			buffer.append(fightRound(odd?a:b, odd?b:a));
			odd = !odd;
		}
		return buffer.toString();
	}
	
	public static String fightRound(Ninja attacker, Ninja defender) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(attacker.getName()).append(" (").append(attacker.getLife()).append(") attacks ").append(defender.getName()).append(" (").append(defender.getLife()).append(")");
		Attack attack = attacker.attack();
		buffer.append(" with a ").append(attack.getName()).append(" dealing ").append(attack.getDamage()).append(" damage!\n");
		defender.getAttacked(attack);
		if(defender.getLife() < 1) {
			buffer.append(attacker.getName()).append(" has defeated ").append(defender.getName()).append(".\n\n");
		}
		return buffer.toString();
	}
}