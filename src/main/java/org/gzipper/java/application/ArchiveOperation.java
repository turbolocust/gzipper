/*
 * Copyright (C) 2017 Matthias Fussenegger
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
package org.gzipper.java.application;

import java.io.IOException;
import java.util.concurrent.Callable;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;
import org.gzipper.java.application.concurrency.Interruptable;
import org.gzipper.java.application.model.ArchiveType;
import org.gzipper.java.application.pojo.ArchiveInfo;
import org.gzipper.java.exceptions.GZipperException;
import org.gzipper.java.i18n.I18N;
import org.gzipper.java.util.Log;
import org.gzipper.java.application.algorithm.CompressionAlgorithm;

/**
 * Object that represents an archiving operation.
 *
 * @author Matthias Fussenegger
 */
public class ArchiveOperation implements Callable<Boolean>, Interruptable {

    /**
     * The aggregated {@link ArchiveInfo}.
     */
    private final ArchiveInfo _archiveInfo;

    /**
     * The algorithm that is used for either creating or extracting an archive.
     * It will be determined during the initialization of an object.
     */
    private final CompressionAlgorithm _algorithm;

    /**
     * Describes what kind of operation shall be performed.
     */
    private final CompressionMode _compressionMode;

    /**
     * True if a request for interruption has been received.
     */
    private boolean _interrupt;

    /**
     * The elapsed time of the operation which will be stored after its end.
     */
    private long _elapsedTime;

    /**
     * Constructs a new instance of this class using the specified values.
     *
     * @param info the {@link ArchiveInfo} to be aggregated.
     * @param compressionMode the {@link CompressionMode} for this operation.
     * @throws org.gzipper.java.exceptions.GZipperException if determination of
     * archiving algorithm has failed.
     */
    public ArchiveOperation(ArchiveInfo info, CompressionMode compressionMode)
            throws GZipperException {
        _archiveInfo = info;
        _compressionMode = compressionMode;
        if ((_algorithm = init(info)) == null) {
            throw new GZipperException();
        }
    }

    /**
     * Initializes this object by determining the right archiving algorithm. See
     * {@link #_algorithm} for more information.
     *
     * @param info the {@link ArchiveInfo} to use for initialization.
     * @return the determined {@link CompressionAlgorithm} or {@code null} if it
     * could not be determined.
     */
    private CompressionAlgorithm init(ArchiveInfo info) {
        ArchiveType archiveType = info.getArchiveType();
        return archiveType.determineArchivingAlgorithm();
    }

    /**
     * Calculates the elapsed time in seconds.
     *
     * @return the elapsed time in seconds.
     */
    public double calculateElapsedTime() {
        return ((double) _elapsedTime / 1E9);
    }

    /**
     * Returns the aggregated instance of {@link ArchiveInfo}.
     *
     * @return the aggregated instance of {@link ArchiveInfo}.
     */
    public ArchiveInfo getArchiveInfo() {
        return _archiveInfo;
    }

    @Override
    public Boolean call() throws Exception {
        boolean success = false;
        if (_algorithm != null) {
            long startTime = System.nanoTime();
            try {
                switch (_compressionMode) {
                    case COMPRESS:
                        _algorithm.compress(_archiveInfo);
                        break;
                    case DECOMPRESS:
                        _algorithm.extract(_archiveInfo);
                        break;
                    default:
                        throw new GZipperException("Mode could not be determined.");
                }
                success = true;
            } catch (IOException ex) {
                if (!_interrupt) { // considered to be critical
                    Log.e(ex.getLocalizedMessage(), ex);
                    Log.w(I18N.getString("missingAccessRights.text"), true);
                }
            } catch (CompressorException | ArchiveException ex) {
                Log.e(ex.getLocalizedMessage(), ex);
            }
            _elapsedTime = System.nanoTime() - startTime;
        }
        return success;
    }

    @Override
    public void interrupt() {
        _interrupt = true;
        _algorithm.interrupt();
    }
}
