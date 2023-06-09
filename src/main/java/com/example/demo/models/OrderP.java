package com.example.demo.models;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderP extends DataModel{
	@Id
    // @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Double total;
    private Integer userId;
    private Integer deskId;
    private String status;
    
      
    public OrderP() {}

    public OrderP copy(){
        OrderP orderP = new OrderP();
        orderP.setId(id);
		orderP.setStatus(status);
		orderP.setUserId(userId);
		orderP.setDeskId(deskId);
 		orderP.setTotal(total);
        return orderP;
    }

    /**
     * Creates an OrderP object from a JSON String.
     * @param json 
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public OrderP(String json) throws JsonMappingException, JsonProcessingException {
		super(json);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        setId((Integer)map.get("id"));
        setStatus((String)map.get("status"));
        setUserId((Integer)map.get("user_id"));
        setDeskId((Integer)map.get("desk_id"));
        setTotal((Double)map.get("total"));
    }
    
    /**
     * Copies all the data from one source order to the current object.
     * @param source
     */
    @Override
    public void updateFrom(DataModel dataModel){
        OrderP source = (OrderP) dataModel;
        if(source.getStatus() != null){
            setStatus(source.getStatus());
        }
        if(source.getUserId() != null){
            setUserId(source.getUserId());
        }
        if(source.getDeskId() != null){
            setDeskId(source.getDeskId());
        }
        if(source.getTotal() != null){
            setTotal(source.getTotal());
        }
    }

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Double return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return Integer return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return Integer return the deskId
     */
    public Integer getDeskId() {
        return deskId;
    }

    /**
     * @param deskId the deskId to set
     */
    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    /**
     * @return String return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
    @Override 
    public boolean equals(Object obj) {
        if(obj.getClass() != OrderP.class){
            return false;
        }
        OrderP orderP = (OrderP)obj;
        return id.equals(orderP.getId()) 
        && status.equals(orderP.getStatus())
        && userId.equals(orderP.getUserId())
        && deskId.equals(orderP.getDeskId())
        && total.equals(orderP.getTotal())
        && status.equals(orderP.getStatus()); 
    }

    @Override
    public String toString() {
       return "OrderP[id="+id
       + "; status="+status
       + "; userId="+userId
       + "; deskId="+deskId
       + "; status="+status
       + "; total="+total
       + "]";
    }

   
}
