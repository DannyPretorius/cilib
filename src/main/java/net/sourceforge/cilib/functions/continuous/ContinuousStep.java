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
package net.sourceforge.cilib.functions.continuous;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;


/**
 * The continuous Step function. It is the same as the normal step function, however,
 * it is continuous and not discrete.
 *
 * <p>
 * The default domain of the function is defined to be R(-100.0, 100.0)^30
 *
 * @author Andries Engelbrecht
 */
public class ContinuousStep extends ContinuousFunction {
    private static final long serialVersionUID = 4962101545621686038L;

    /** Creates a new instance of Step. */
    public ContinuousStep() {
        setDomain("R(-100.0, 100.0)^30");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContinuousStep getClone() {
        return new ContinuousStep();
    }

    /**
     * Get the defined minimum of the {@linkplain Function}. The minimum is defined to
     * be the value of 0.0.
     * @return An {@code Object} containing the value 0.0.
     */
    public Double getMinimum() {
        return 0.0;
    }

    /**
     * Get the defined maximum of the {@linkplain Function}. The maximum value is defined to
     * be the value of 55.0.
     * @return An {@code Object} containing the value 55.0.
     */
    public Double getMaximum() {
        return 55.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double apply(Vector input) {
        double sum = 0.0;
        for (int i = 0; i < getDimension(); ++i) {
            sum += (input.doubleValueOf(i) + 0.5) * (input.doubleValueOf(i) + 0.5);
        }
        return sum;
    }

}
