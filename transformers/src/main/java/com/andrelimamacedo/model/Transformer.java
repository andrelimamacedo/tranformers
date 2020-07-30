package com.andrelimamacedo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity(name = "transformer" )
@SequenceGenerator(name = "SEQ_TB01", schema = "public" , initialValue = 1, allocationSize = 1)
@Table(name = "TB01_TRANSFORMER", schema = "public")
public class Transformer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TB01")	
	private Long id;
	
	@Column(name = "na_name")
	@NotNull
	private String name;
	
	@Column(name = "nu_strength")
	@NotNull
	private int strength;
	
	@Column(name = "nu_intelligence")
	@NotNull
	private int intelligence;

	@Column(name = "nu_speed")
	@NotNull
	private int speed;

	@Column(name = "nu_endurance")
	@NotNull
	private int endurance;

	@Column(name = "nu_rank")
	@NotNull
	private int rank;
	
	@Column(name = "nu_courage")
	@NotNull
	private int courage;

	@Column(name = "nu_firepower")
	@NotNull
	private int firepower;

	@Column(name = "nu_skill")
	@NotNull
	private int skill;

	@Column(name = "ic_race")
	@NotNull
	/**
	 * D  decepticons and A for autobots 
	 */
	private char race;

	
	public String getName() {
		return name;
	}

	public Transformer(Long id, String name, int strength, int intelligence, int speed, int endurance, int rank,
			int courage, int firepower, int skill, char race) {
		super();
		this.id = id;
		this.name = name;
		this.strength = strength;
		this.intelligence = intelligence;
		this.speed = speed;
		this.endurance = endurance;
		this.rank = rank;
		this.courage = courage;
		this.firepower = firepower;
		this.skill = skill;
		this.race = race;
	}

	public Transformer() {
		// TODO Auto-generated constructor stub
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strenght) {
		this.strength = strenght;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getCourage() {
		return courage;
	}

	public void setCourage(int courage) {
		this.courage = courage;
	}

	public int getFirepower() {
		return firepower;
	}

	public void setFirepower(int firepower) {
		this.firepower = firepower;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public char getRace() {
		return race;
	}

	public void setRace(char race) {
		this.race = race;
	}
	
	

}
