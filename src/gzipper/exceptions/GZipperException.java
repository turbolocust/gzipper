/*
 * Copyright (C) 2016 Matthias Fussenegger
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
package gzipper.exceptions;

/**
 * Class to handle generic errors that can occur while using application
 *
 * @author Matthias Fussenegger
 */
public class GZipperException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Delegates exception to its super class {@code Exception}
     */
    public GZipperException() {
        super();
    }

    /**
     * Delegates error message to its super class {@code Exception}
     *
     * @param errorMessage The specified error message
     */
    public GZipperException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Delegates exception cause to its super class {@code Exception}
     *
     * @param cause The cause of this exception
     */
    public GZipperException(Throwable cause) {
        super(cause);
    }

    /**
     * Delegates error message and cause to its super class {@code Exception}
     *
     * @param errorMessage The specified error message
     * @param cause The cause of this exception
     */
    public GZipperException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}