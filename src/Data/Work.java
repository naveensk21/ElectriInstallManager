package Data;

import java.util.Date;


public class Work {
    
    private Long id;
    private Long customerId;
    private String workType;
    private String address;
    private Date workDate;
    private Long noOfRooms;
    private Long engineerId;
    private String workStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Long getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(Long noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public Long getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(Long engineerId) {
        this.engineerId = engineerId;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }
    
    
    
}
