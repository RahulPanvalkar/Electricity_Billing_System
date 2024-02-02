package com.ebs.entities;

import java.io.Serializable;

public class CostPerUnit implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private double unitsZeroToHundred;
	private double unitsOneHundredOneToThreeHundred;
	private double unitsThreeHundredOneToFiveHundred;
	private double unitsFiveHundredOneAndAbove;
	
	public CostPerUnit() {
		
	}

	public CostPerUnit(double unitsZeroToHundred, double unitsOneHundredOneToThreeHundred,
			double unitsThreeHundredOneToFiveHundred, double unitsFiveHundredOneAndAbove) {

		this.unitsZeroToHundred = unitsZeroToHundred;
		this.unitsOneHundredOneToThreeHundred = unitsOneHundredOneToThreeHundred;
		this.unitsThreeHundredOneToFiveHundred = unitsThreeHundredOneToFiveHundred;
		this.unitsFiveHundredOneAndAbove = unitsFiveHundredOneAndAbove;
	}

	public double getUnitsZeroToHundred() {
		return unitsZeroToHundred;
	}

	public void setUnitsZeroToHundred(double unitsZeroToHundred) {
		this.unitsZeroToHundred = unitsZeroToHundred;
	}

	public double getUnitsOneHundredOneToThreeHundred() {
		return unitsOneHundredOneToThreeHundred;
	}

	public void setUnitsOneHundredOneToThreeHundred(double unitsOneHundredOneToThreeHundred) {
		this.unitsOneHundredOneToThreeHundred = unitsOneHundredOneToThreeHundred;
	}

	public double getUnitsThreeHundredOneToFiveHundred() {
		return unitsThreeHundredOneToFiveHundred;
	}

	public void setUnitsThreeHundredOneToFiveHundred(double unitsThreeHundredOneToFiveHundred) {
		this.unitsThreeHundredOneToFiveHundred = unitsThreeHundredOneToFiveHundred;
	}

	public double getUnitsFiveHundredOneAndAbove() {
		return unitsFiveHundredOneAndAbove;
	}

	public void setUnitsFiveHundredOneAndAbove(double unitsFiveHundredOneAndAbove) {
		this.unitsFiveHundredOneAndAbove = unitsFiveHundredOneAndAbove;
	}
	

	@Override
	public String toString() {
		
		return "CostPerUnit :: unitsZeroToHundred = " + unitsZeroToHundred + ",\nunitsOneHundredOneToThreeHundred = "
				+ unitsOneHundredOneToThreeHundred + ", \nunitsThreeHundredOneToFiveHundred = "
				+ unitsThreeHundredOneToFiveHundred + ", \nunitsFiveHundredOneAndAbove=" + unitsFiveHundredOneAndAbove;
		
	}
	
	
}
