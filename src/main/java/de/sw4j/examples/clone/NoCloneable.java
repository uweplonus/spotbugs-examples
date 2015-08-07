/*
 * Copyright (C) 2015 Uwe Plonus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.sw4j.examples.clone;

/**
 * Example for "CN: Class defines clone() but doesn't implement Cloneable" {@code (CN_IMPLEMENTS_CLONE_BUT_NOT_CLONEABLE)}.
 */
public class NoCloneable {

    @Override
    public Object clone() {
        return new NoCloneable();
    }

}
