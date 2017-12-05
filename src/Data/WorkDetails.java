package Data;

import java.util.List;


public class WorkDetails {
    
    private Long id;
    private Long workId;
    private List<String> appliances;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public List<String> getAppliances() {
        return appliances;
    }

    public void setAppliances(List<String> appliances) {
        this.appliances = appliances;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
