/*
 * EventManager
 * Copyright (c) 2008-2017 James Watmuff & Leonard Hall
 *
 * This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package au.com.jwatmuff.genericp2p;

/**
 *
 * @author James
 */
public class PeerDiscoveryEvent {
    public enum Type {
        FOUND, LOST
    }
    
    private Type type;
    private PeerInfo info;
    
    public PeerDiscoveryEvent(Type type, PeerInfo info) {
        this.type = type;
        this.info = info;
    }
    
    public Type getType() {
        return type;
    }

    public PeerInfo getPeerInfo() {
        return info;
    }
}
