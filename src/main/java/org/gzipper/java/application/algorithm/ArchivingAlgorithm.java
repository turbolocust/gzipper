/*
 * Copyright (C) 2017 Matthias Fussenegger
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
package org.gzipper.java.application.algorithm;

import java.io.File;
import java.io.IOException;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;
import org.gzipper.java.application.concurrency.Interruptable;
import org.gzipper.java.application.pojo.ArchiveInfo;

/**
 * Any implementing class offers methods to compress and extract archives.
 *
 * @author Matthias Fussenegger
 */
public interface ArchivingAlgorithm extends Interruptable {

    /**
     * Extracts an archive using the algorithm of the concrete class and stores
     * the files of the archive to the specified path.
     *
     * @param location the location where to extract the archive.
     * @param name the filename of the archive to extract.
     * @throws IOException if an I/O error occurs.
     * @throws ArchiveException if an error related to the archiver occurs.
     * @throws CompressorException if an error related to the compressor occurs.
     */
    void extract(String location, String name)
            throws IOException, ArchiveException, CompressorException;

    /**
     * Extracts an archive using the algorithm of the concrete class and stores
     * the files of the archive to the path specified in {@link ArchiveInfo}.
     *
     * @param info POJO that holds information required for extraction.
     * @throws IOException if an I/O error occurs.
     * @throws ArchiveException if an error related to the archiver occurs.
     * @throws CompressorException if an error related to the compressor occurs.
     */
    void extract(ArchiveInfo info)
            throws IOException, ArchiveException, CompressorException;

    /**
     * Compresses files using the algorithm of the concrete class with default
     * settings and stores an archive to the specified path.
     *
     * @param files the files selected from the file chooser.
     * @param location defines where to store the archive.
     * @param name the name of the archive.
     * @throws IOException if an I/O error occurs.
     * @throws ArchiveException if an error related to the archiver occurs.
     * @throws CompressorException if an error related to the compressor occurs.
     */
    void compress(File[] files, String location, String name)
            throws IOException, ArchiveException, CompressorException;

    /**
     * Compresses files using the algorithm of the concrete class with default
     * settings and stores an archive to the path specified in
     * {@link ArchiveInfo}.
     *
     * @param info POJO that holds information required for compression.
     * @throws IOException if an I/O error occurs.
     * @throws ArchiveException if an error related to the archiver occurs.
     * @throws CompressorException if an error related to the compressor occurs.
     */
    void compress(ArchiveInfo info)
            throws IOException, ArchiveException, CompressorException;
}
