package mk.todorht.myfirm.turnovermanagement.domain.repository;

import mk.todorht.myfirm.sharedkernel.repository.GenericRepository;
import mk.todorht.myfirm.turnovermanagement.domain.model.Turnover;
import mk.todorht.myfirm.turnovermanagement.domain.model.TurnoverId;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoverRepository extends GenericRepository<Turnover, TurnoverId> {
}
