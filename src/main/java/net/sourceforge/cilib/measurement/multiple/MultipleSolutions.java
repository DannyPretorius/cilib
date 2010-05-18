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
package net.sourceforge.cilib.measurement.multiple;


import net.sourceforge.cilib.algorithm.Algorithm;
import net.sourceforge.cilib.measurement.Measurement;
import net.sourceforge.cilib.problem.OptimisationSolution;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 *
 * @author Edwin Peer
 * @deprecated This class is no longer valid. A combination of the
 *             {@link CompositeMeasurement} and {@link net.sourceforge.cilib.measurement.single.Solution}
 *             should be used instead
 */
@Deprecated
public class MultipleSolutions implements Measurement<Vector> {
    private static final long serialVersionUID = 1617755270627315980L;

    @Override
    public MultipleSolutions getClone() {
        return this;
    }

    @Override
    public String getDomain() {
        return "T";
    }

    @Override
    public Vector getValue(Algorithm algorithm) {
        Vector v = new Vector();
        Iterable<OptimisationSolution> solutions = algorithm.getSolutions();

        for (OptimisationSolution solution : solutions) {
            v.addAll((Vector) solution.getPosition());
        }

        return v;
    }

}
