package hust.jifa.resume;

/**
 * Created by jfxu on 16/10/3.
 */
public class Experience {
    private String name;
    private String nature;
    private String work;
    private String location;
    private String startTime;
    private String endTime;
    private String detail;

    public String getName() {

        return name;
    }

    public String getNature() {

        return nature;
    }

    public String getWork() {

        return work;
    }

    public String getLocation() {

        return location;
    }

    public String getStartTime() {

        return startTime;
    }

    public String getEndTime() {

        return endTime;
    }

    public String getDetail() {

        return detail;
    }

    public Experience(String name, String nature, String work, String startTime, String endTime, String detail) {

        this.name = name;
        this.nature = nature;
        this.work = work;
        this.startTime = startTime;
        this.endTime = endTime;
        this.detail = detail;
    }
}
