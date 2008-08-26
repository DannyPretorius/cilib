/*
 * Copyright (C) 2003 - 2008
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.sourceforge.cilib.boa.bees;

import net.sourceforge.cilib.boa.positionupdatestrategies.BeePositionUpdateStrategy;
import net.sourceforge.cilib.boa.positionupdatestrategies.VisualPositionUpdateStategy;
import net.sourceforge.cilib.entity.AbstractEntity;
import net.sourceforge.cilib.entity.Entity;
import net.sourceforge.cilib.entity.EntityType;
import net.sourceforge.cilib.entity.operators.selection.RandomSelectionStrategy;
import net.sourceforge.cilib.entity.operators.selection.SelectionStrategy;
import net.sourceforge.cilib.problem.InferiorFitness;
import net.sourceforge.cilib.problem.OptimisationProblem;
import net.sourceforge.cilib.type.types.Type;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * The entity class for the ABC algorithm that represents the bees.
 * @author Andrich
 *
 */
public abstract class AbstractBee extends AbstractEntity implements HoneyBee {
	private static final long serialVersionUID = 7005546673802814268L;

	protected BeePositionUpdateStrategy positionUpdateStrategy;
	protected SelectionStrategy targetSelectionStrategy;
	protected int dimension;

	/**
	 * Default constructor. Defines reasonable defaults for common members.
	 */
	public AbstractBee() {
		this.positionUpdateStrategy = new VisualPositionUpdateStategy();
		this.targetSelectionStrategy = new RandomSelectionStrategy();
	}

	/**
	 * Copy constructor. Create a copy of the provided instance.
	 * @param copy the reference of the bee that is deep copied.
	 */
	public AbstractBee(AbstractBee copy) {
		super(copy);
		this.positionUpdateStrategy = copy.positionUpdateStrategy;
		this.targetSelectionStrategy = copy.targetSelectionStrategy;
		this.dimension = copy.dimension;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract AbstractBee getClone();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BeePositionUpdateStrategy getPositionUpdateStrategy() {
		return this.positionUpdateStrategy;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void calculateFitness() {
		this.calculateFitness(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void calculateFitness(boolean count) {
		this.getProperties().put(EntityType.FITNESS, getFitnessCalculator().getFitness(this, count));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(Entity o) {
		return getFitness().compareTo(o.getFitness());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getDimension() {
		return this.dimension;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Vector getPosition() {
		return (Vector) this.getCandidateSolution();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setPosition(Vector position) {
		this.setCandidateSolution(position);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialise(OptimisationProblem problem) {
		this.setCandidateSolution((Type) problem.getDomain().getBuiltRepresenation().getClone());
		this.getCandidateSolution().randomise();

		this.dimension = this.getCandidateSolution().getDimension();
		this.getProperties().put(EntityType.FITNESS, InferiorFitness.instance());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reinitialise() {
		throw new UnsupportedOperationException("Reinitialise not implemented for AbstractBee");
	}

}
