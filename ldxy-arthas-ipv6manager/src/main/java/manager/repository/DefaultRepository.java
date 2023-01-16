package manager.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public abstract class DefaultRepository implements IRepository {
    Logger logger = LoggerFactory.getLogger(this.getClass());
}
