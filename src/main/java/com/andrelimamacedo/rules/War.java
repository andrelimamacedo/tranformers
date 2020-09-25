package com.andrelimamacedo.rules;

import java.util.List;

import com.andrelimamacedo.model.Transformer;

public class War {

	List<Transformer> autobots;
	List<Transformer> decepticons;
	int battles ;
	String winner = "";		
	int transformersLeft = 0;
	
	/**
	 * Rule - If any fighter is down 4 or more points of courage and 3 or more points of strength compared to their opponent, 
	 * the opponent automatically wins the face-off regardless of overall rating (opponent has ran away)
	 * @param autobot
	 * @param decepticon
	 * @return winner
	 */	
	public Transformer rule1(Transformer autobot,Transformer decepticon) {
		Transformer winner = null;
		if( ( autobot.getCourage() > (decepticon.getCourage() + 4) ) && 
				(( autobot.getStrength() > (decepticon.getStrength() + 3) ))) {
			winner =  autobot;
		}
		if( ( autobot.getCourage() + 4 < (decepticon.getCourage() ) ) && 
				(( autobot.getStrength()  + 3 < (decepticon.getStrength()) ))) {
			winner =  decepticon;
		}
		return winner;
	}
	
	/**
	 * Rule -  if one of the fighters is 3 or more points of skill above their opponent,
	 *  they win the fight regardless of overall rating 
	 * @param autobot
	 * @param decepticon
	 * @return winner
	 */	
	public Transformer rule2(Transformer autobot,Transformer decepticon) {
		Transformer winner = null;
		if ( autobot.getSkill() > (decepticon.getSkill() + 3) ){
			winner =  autobot;
		}
		if ( autobot.getSkill() + 3 < (decepticon.getSkill() ) ){
			winner =  decepticon;
		}
		return winner;
	}		
	/**
	 * The “overall rating” of a Transformer is the following formula:
	 *  (Strength + Intelligence + Speed + Endurance + Firepower). 
	 * @param transformer
	 * @return
	 */
	public int getOverralRate(Transformer transformer) {
		return (transformer.getStrength() + transformer.getIntelligence() + transformer.getSpeed() + 
				transformer.getEndurance() + transformer.getFirepower());
	}
	
	public void setAutobots(List<Transformer> autobots) {
		this.autobots = autobots;
	}

	public void setDecepticons(List<Transformer> decepticons) {
		this.decepticons = decepticons;
	}
	
	public String checkEmptyTeams() {
		if((autobots.size() == 0 ) && (decepticons.size() > 0)) {
			winner = "Decepticons wins!";
		}
		if((autobots.size() > 0 ) && (decepticons.size() == 0)) {
			winner = "Autobots wins!";
		}
		return winner;
	}

	public void fight() {
		
		int autoBotsWins = 0;
		int decepticonsWins = 0;
		String transformersLosers = "";
		int index = 0;
		if(checkEmptyTeams().contentEquals("")) {
			//sorting Transformers by rank descending
			autobots.sort((a,b) -> b.getRank() - a.getRank());
			decepticons.sort((a,b) -> b.getRank() - a.getRank());			
			boolean battleOver = false;
			Transformer fighter;
			for (Transformer autobot : autobots) {				
				if(index == decepticons.size() ) {
					break;
				}
				Transformer decepticon = decepticons.get(index);											
				if(autobot.getName().equalsIgnoreCase("Optimus Prime") && decepticon.getName().equalsIgnoreCase("Predaking") ) {
					winner = "No winnners - all competitors destroyed ";					
					break;
				}
				if(autobot.getName().equalsIgnoreCase("Optimus Prime")) {
					autoBotsWins +=1;
					battleOver = true;
				}
				if(decepticon.getName().equalsIgnoreCase("Predaking")) {
					decepticonsWins +=1;
					battleOver = true;
				}
				if(!battleOver) {
					fighter = rule1(autobot, decepticon);
					if(fighter == null ) {
						fighter = rule2(autobot, decepticon);
					}
					if(fighter == null ) {
						if(getOverralRate(autobot) > getOverralRate(decepticon)) {
							autoBotsWins++;							
						}
						if(getOverralRate(autobot) < getOverralRate(decepticon)) {
							decepticonsWins++;							
						}
					}else {
						if(fighter.getRace() == '0') {
							decepticonsWins++;
						}
						else {
							autoBotsWins++;
						}
					}
				}
				battles++;
				index++;
				battleOver = false;
			}
			
		}
		else {
			winner = "Not enough transformers to fight!";
		}		
		if(winner.equals("")) {
			if(autoBotsWins > decepticonsWins ) {
				winner = "Autobots won by " + autoBotsWins + " x " + decepticonsWins ;
				transformersLeft = decepticons.size() - battles;
				while(index < decepticons.size()) {
					transformersLosers += " - " + decepticons.get(index).getName();
					index++;
				}
			}
			if(autoBotsWins < decepticonsWins ) {
				winner = "Decepticons won by " + decepticonsWins + " x " + autoBotsWins ;
				transformersLeft = autobots.size() - battles;
				while(index < autobots.size()) {
					transformersLosers += " - " + autobots.get(index).getName();
					index++;
				}

			}
			if(autoBotsWins == decepticonsWins ) {
				winner = "Teams are tied " + decepticonsWins + " x " + autoBotsWins ;
			}
			winner =  "Total of battles -> " + battles +  ", " +  winner + ", losers left -> " + transformersLeft + " : " + transformersLosers;
		}
	}

	public String getWinner() {
		// TODO Auto-generated method stub
		return winner;
	}

}
