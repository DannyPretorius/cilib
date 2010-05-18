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
package net.sourceforge.cilib.math.random.generator;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Random;

/**
 * Determine the initial seed value by manipulating the current network address.
 */
public enum SeedSelectionType implements SeedSelectionStrategy {
    NETWORK_BASED;
    
    private Random random;
    private int address;

    SeedSelectionType() {
        random = new SecureRandom();
        address = getNetworkAddress();
    }

    @Override
    public long getSeed() {
        long seed = random.nextLong();
        seed ^= System.currentTimeMillis();
        seed ^= address;
        seed ^= ((long) System.identityHashCode(new Object())) << 32;

        return seed;
    }

    private int getNetworkAddress() {
        byte[] address = null;

        try {
            address = InetAddress.getLocalHost().getAddress();
        }
        catch (UnknownHostException ex) {
//            log.warn("localhost not found directly. Proceeding.");
        }

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                Enumeration<InetAddress> addresses = interfaces.nextElement().getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (!addr.isLoopbackAddress()) {
                        address = addr.getAddress();
                    }
                }
            }
        }
        catch (SocketException ex) {
//            log.warn("localhost not found through interfce list. Proceeding.");
        }

        if (address == null) {
            return 0;
        }
        else {
            return ((int) address[0]) << 24 | ((int) address[1]) << 16 | ((int) address[2]) << 8 | (int) address[3];
        }
    }
}
