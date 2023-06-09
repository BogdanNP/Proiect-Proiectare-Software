package com.example.demo.models;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room extends DataModel{
    @Id
    // @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer width;
    private Integer length;
    private List<Integer> deskList;
    private String details;

    public Room(){}

    public Room copy(){
        Room room = new Room();
        room.setId(id);
		room.setWidth(width);
		room.setLength(length);
		room.setDeskList(deskList);
		room.setDetails(details);
        return room;
    }

    /**
     * Creates a Room object from a JSON String.
     * @param json 
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public Room(String json) throws JsonMappingException, JsonProcessingException {
		super(json);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        setDetails((String)map.get("details"));
        setWidth((Integer)map.get("width"));
        setLength((Integer)map.get("length"));
    }
    
    /**
     * Copies all the data from one source room to the current object.
     * @param source
     */
    @Override
    public void updateFrom(DataModel dataModel){
        Room source = (Room) dataModel;
        if(source.getDetails() != null){
            setDetails(source.getDetails());
        }
        if(source.getDeskList() != null){
            setDeskList(source.getDeskList());
        }
        if(source.getWidth() != null){
            setWidth(source.getWidth());
        }
        if(source.getLength() != null){
            setLength(source.getLength());
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
     * @return Integer return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @return Integer return the length
     */
    public Integer getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * @return List<Integer> return the deskList
     */
    public List<Integer> getDeskList() {
        return deskList;
    }

    /**
     * @param deskList the deskList to set
     */
    public void setDeskList(List<Integer> deskList) {
        this.deskList = deskList;
    }

    /**
     * @return String return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    
    @Override 
    public boolean equals(Object obj) {
        if(obj.getClass() != Room.class){
            return false;
        }
        Room room = (Room)obj;
        return id.equals(room.getId())
        && width.equals(room.getWidth())
        && length.equals(room.getLength())
        && details.equals(room.details)
        && deskList.equals(room.getDeskList()); 
    }

    @Override
    public String toString() {
       return "Room[id="+id
       + "; width="+width
       + "; length="+length
       + "; details="+details
       + "; deskList="+deskList
       + "]";
    }

}
