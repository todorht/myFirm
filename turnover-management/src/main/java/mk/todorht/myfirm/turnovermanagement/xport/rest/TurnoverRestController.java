package mk.todorht.myfirm.turnovermanagement.xport.rest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mk.todorht.myfirm.turnovermanagement.domain.model.Turnover;
import mk.todorht.myfirm.turnovermanagement.service.TurnoverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/turnover")
@AllArgsConstructor
public class TurnoverRestController {

    private final TurnoverService service;

    @GetMapping
    public List<Turnover> getTurnoverRecords(){
        return service.findAll();
    }
}
