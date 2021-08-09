package todorht.blikmak.workinghoursrecords.dto;


import lombok.Getter;
import todorht.blikmak.workinghoursrecords.models.enums.ActivityType;

@Getter
public class ActivityInformationDto {

    private String dateTime;
    private String employeeName;
    private ActivityType activityType;

    public ActivityInformationDto(String date, String employeeName, ActivityType activityType) {
        this.dateTime = date;
        this.employeeName = employeeName;
        this.activityType = activityType;
    }
}

