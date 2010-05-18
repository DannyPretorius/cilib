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
package net.sourceforge.cilib.pso.niching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.sourceforge.cilib.entity.Entity;
import net.sourceforge.cilib.entity.Particle;
import net.sourceforge.cilib.pso.PSO;
import net.sourceforge.cilib.pso.particle.StandardParticle;
import net.sourceforge.cilib.type.types.container.Vector;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author gpampara
 */
public class StandardSwarmCreationStrategyTest {

    /**
     * This test will create a new sub-swarm when a Niche is identified.
     * The niche point will create a sub-swarm with the entity that "discovered"
     * the niche and the entity that is the closest to that niched entity.
     *
     * The niche is defined to be found / located by particle p1. The closest
     * entity to p1 will be p2.
     */
    @Test
    public void creationOfSubSwarms() {
        Particle p1 = new StandardParticle();
        Particle p2 = new StandardParticle();
        Particle p3 = new StandardParticle();
        Particle p4 = new StandardParticle();

        p1.setCandidateSolution(Vector.of(0.0));
        p2.setCandidateSolution(Vector.of(0.1));
        p3.setCandidateSolution(Vector.of(0.4));
        p4.setCandidateSolution(Vector.of(0.5));

        // Create the main swarm
        PSO pso = new PSO();
        pso.getTopology().addAll(Arrays.asList(p1, p2, p3, p4));

        Niche niche = new Niche();
        niche.setMainSwarm(pso);

        List<Entity> locatedNiche = new ArrayList<Entity>();
        locatedNiche.add(p1);

        // Identify the niche and create a subswarm!
        NicheCreationStrategy creationStrategy = new StandardSwarmCreationStrategy();
        creationStrategy.create(niche, locatedNiche);

        Assert.assertEquals(1, niche.getPopulations().size());
        Assert.assertEquals(2, niche.getPopulations().get(0).getTopology().size());
        Assert.assertEquals(2, niche.getMainSwarm().getTopology().size());
        Assert.assertTrue(niche.getMainSwarm().getTopology().contains(p3));
        Assert.assertTrue(niche.getMainSwarm().getTopology().contains(p4));
    }
}
