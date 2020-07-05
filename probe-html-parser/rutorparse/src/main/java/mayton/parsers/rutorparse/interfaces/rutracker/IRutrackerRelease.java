package mayton.parsers.rutorparse.interfaces.rutracker;

import mayton.parsers.rutorparse.impl.rutracker.RutrackerReleaseEntity;

public interface IRutrackerRelease {

    Iterable<RutrackerReleaseEntity> enumerate();

}
