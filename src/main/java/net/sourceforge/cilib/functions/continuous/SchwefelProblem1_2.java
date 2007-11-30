/*
 * SchwefelProblem1_2.java
 * 
 * Created on Aug 3, 2005
 *
 * Copyright (C) 2003, 2004, 2005 - CIRG@UP 
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
 * 
 */
package net.sourceforge.cilib.functions.continuous;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * Characteristics:
 * 
 * <li>Unimodal</li>
 * <li>Non Separable</li>
 * 
 * f(x) = 0; x = (0,0,...,0)
 * 
 * x e [-100,100]
 * 
 * @author Gary Pampara
 */
public class SchwefelProblem1_2 extends ContinuousFunction {
	private static final long serialVersionUID = -65519037071861168L;

	public SchwefelProblem1_2() {
		setDomain("R(-100,100)^30)");
	}
	
	@Override
	public SchwefelProblem1_2 getClone() {
		return new SchwefelProblem1_2();
	}
	
	public Object getMinimum() {
		return new Double(0.0);
	}

	@Override
	public double evaluate(Vector x) {
		double sumsq = 0.0;
		double sum = 0.0;
		
		for (int i = 0; i < getDimension(); i++) {
			sum = 0.0;
			
			for (int j = 0; j < i; j++) {
				sum += x.getReal(j);
			}
			
			sumsq += sum * sum; 
		}
		
		return sumsq;
	}

}
