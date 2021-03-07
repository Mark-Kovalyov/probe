package mayton.parsers.rutorparse.interfaces.rutor;

import mayton.parsers.rutorparse.entities.RutorRawEntity;

public interface IPageWalker {

    Iterable<RutorRawEntity> walk(int page);

}
