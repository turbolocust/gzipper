/*
 * Copyright (C) 2016 Matthias Fussenegger
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gzipper.java.style;

/**
 *
 * @author Matthias Fussenegger
 */
public class CSS {

    /**
     * The location of the dark theme style sheet.
     */
    public static final String STYLESHEET_DARK_THEME = "/css/DarkTheme.css";

    /**
     * Holds static members only.
     */
    private CSS() {
    }

    /**
     * Enumeration that consists of all existing visual themes.
     */
    public enum Theme {
        MODENA, DARK_THEME;
    }
}
