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
package org.gzipper.java.presentation.util;

import java.util.zip.Deflater;
import org.gzipper.java.application.model.ArchiveType;
import org.gzipper.java.application.pojo.ArchiveInfo;
import org.gzipper.java.exceptions.GZipperException;

/**
 *
 * @author Matthias Fussenegger
 */
public class ArchiveInfoFactory {

    public static ArchiveInfo createArchiveInfo(String type, int level) throws GZipperException {

        ArchiveType archiveType = ArchiveType.determineArchiveType(type);

        if (archiveType == null) {
            throw new GZipperException("Archive type could not be determined.");
        } else if (level < Deflater.DEFAULT_COMPRESSION || level > Deflater.BEST_COMPRESSION) {
            throw new GZipperException("Faulty compression level specified.");
        }

        return new ArchiveInfo(archiveType, level);
    }

    public static ArchiveInfo createArchiveInfo(String type) throws GZipperException {

        ArchiveType archiveType = ArchiveType.determineArchiveType(type);

        if (archiveType == null) {
            throw new GZipperException("Archive type could not be determined.");
        }

        return new ArchiveInfo(archiveType);
    }

}
