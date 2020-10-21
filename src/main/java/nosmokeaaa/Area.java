package nosmokeaaa;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Area_table")
public class Area {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long areaId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        System.out.println("##### LLLL:"+this.getAreaId());
        System.out.println("##### LLLL:"+this.getId());
        System.out.println("##### LLLL:"+this.getStatus());
        Occupied occupied = new Occupied();
        BeanUtils.copyProperties(this, occupied);
        occupied.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        Emptied emptied = new Emptied();
        BeanUtils.copyProperties(this, emptied);
        emptied.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
