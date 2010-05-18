/**
 * Computational Intelligence Library (CIlib)
 * Copyright (C) 2003 - 2010
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.cilib.functions.continuous.dynamic.moo.fda2;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * This function is the g function of the FDA2 problem defined on page 429 in the following paper:
 * M.Farina, K.Deb, P.Amato. Dynamic multiobjective optimization problems: test cases, approximations
 * and applications, IEEE Transactions on Evolutionary Computation, 8(5): 425-442, 2003
 *
 * @author Marde Greeff
 */

public class FDA2_g extends ContinuousFunction {

    private static final long serialVersionUID = 8726700022515610264L;

    /**
     * Default Constructor
     */
    public FDA2_g() {
        super();
        setDomain("R(-1, 1)^15");
    }

    /**
     * copy constructor
     * @param copy
     */
    public FDA2_g(FDA2_g copy) {
        super(copy);
        this.setDomain(copy.getDomain());
    }

    /**
     * return a clone
     */
    public FDA2_g getClone() {
        return new FDA2_g(this);
    }

    /**
     * Evaluates the function
     * g(XII) = 1 + sum((x_i)^2)
     */
    @Override
    public Double apply(Vector input) {

        double sum = 1.0;

        for (int k=0; k < input.size(); k++) {
            sum += Math.pow(input.doubleValueOf(0), 2);
        }

        return sum;
    }

}
