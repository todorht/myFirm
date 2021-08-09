package todorht.blikmak.workinghoursrecords.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todorht.blikmak.workinghoursrecords.dto.ActivityInformationDto;
import todorht.blikmak.workinghoursrecords.dto.EmployeeCardDto;
import todorht.blikmak.workinghoursrecords.models.enums.ActivityType;
import todorht.blikmak.workinghoursrecords.service.ActivityInformationService;
import todorht.blikmak.workinghoursrecords.service.EmployeeCardService;



import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
// @CrossOrigin(origins = "http://192.168.0.47:8081")
    @RequestMapping("/activities")
public class ActivityInformationRestController {

    private final ActivityInformationService activityInformationService;
    private final EmployeeCardService employeeCardService;

    public ActivityInformationRestController(ActivityInformationService activityInformationService, EmployeeCardService employeeCardService) {
        this.activityInformationService = activityInformationService;
        this.employeeCardService = employeeCardService;
    }

    @GetMapping()
    public ResponseEntity<List<ActivityInformationDto>> getActivities() {

        List<ActivityInformationDto> activityInformationList = activityInformationService.findAll();
        if (activityInformationList == null) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok().body(activityInformationList);
    }

    @GetMapping("/grouped")
    public ResponseEntity<Map<String, List<ActivityInformationDto>>> getActivitiesGroupedByDay() {
        Map<String, List<ActivityInformationDto>> result = this.activityInformationService.findAll().stream()
                .collect(Collectors.groupingBy(activity -> {
                    String[] parts = activity.getDateTime().split(" ");
                    return parts[0];
                }));
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/insert")
    public void makeCorrection(@RequestBody ActivityInformationDto activityInformationDto) {
        activityInformationService.save(activityInformationDto);
    }

    @GetMapping("/day")
    private ResponseEntity<List<ActivityInformationDto>> getActivitiesDaily(@RequestParam(name = "day", required = false) Integer day,
                                                                            @RequestParam(name = "month", required = false) Integer month) {
        List<ActivityInformationDto> result = this.activityInformationService.getActivitiesForSelectedDate(day, month);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<List<ActivityInformationDto>> getActivitiesByEmployee(@PathVariable(name = "cardNumber") String cardNum) {
        List<ActivityInformationDto> activities = this.activityInformationService.findAllByCardNumber(cardNum);
        if (activities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(activities);
    }

    @GetMapping("/forSomePeriod")
    private ResponseEntity<List<ActivityInformationDto>> getActivitiesForPeriod(@RequestParam(name = "fromDate", required = false) String fromDate,
                                                                                @RequestParam(name = "toDate", required = false) String toDate) {
        List<ActivityInformationDto> result = this.activityInformationService.getActivitiesForSomePeriod(fromDate, toDate);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/employee-cards")
    public ResponseEntity<List<EmployeeCardDto>> getEmployeeCards() {
        List<EmployeeCardDto> employees = this.employeeCardService.findAll();
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/types")
    public ResponseEntity<List<ActivityType>> getActivityTypes() {
        return ResponseEntity.ok().body(Arrays.asList(ActivityType.values()));
    }
}
