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
package net.sourceforge.cilib.problem.dataset;

import static org.junit.Assert.assertEquals;
import net.sourceforge.cilib.problem.ClusteringProblem;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AssociatedPairDataSetBuilderTest {
    private static AssociatedPairDataSetBuilder dataSetBuilder = null;

    @BeforeClass
    public static void intialise() {
        dataSetBuilder = new AssociatedPairDataSetBuilder();
        dataSetBuilder.addDataSet(new MockClusteringStringDataSet());
        ClusteringProblem problem = new ClusteringProblem();
        problem.setDataSetBuilder(dataSetBuilder);
    }

    @AfterClass
    public static void destroy() {
        dataSetBuilder = null;
    }

    @Test
    public void testNumberOfPatterns() {
        assertEquals(93, dataSetBuilder.getNumberOfPatterns());
    }
}
