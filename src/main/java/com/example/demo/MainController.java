package com.example.demo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.Desk;
import com.example.demo.models.DeskAlignment;
import com.example.demo.models.DeskRequest;
import com.example.demo.models.Order;
import com.example.demo.models.Room;
import com.example.demo.models.UserP;
import com.example.demo.repos.DeskRepository;
import com.example.demo.repos.DeskRequestRepository;
import com.example.demo.repos.OrderRepository;
import com.example.demo.repos.RoomRepository;
import com.example.demo.repos.UserPRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private UserPRepository userRepository;

  @Autowired 
  private DeskRepository deskRepository; 
  
  @Autowired 
  private DeskRequestRepository deskRequestRepository;  
  
  @Autowired 
  private RoomRepository roomRepository;

  @Autowired 
  private OrderRepository orderRepository;
  
  @PostMapping(path="/user/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (
      @RequestParam String username
    ) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    UserP user = new UserP();
    user.setUsername(username);
    userRepository.save(user);
    return "User Saved";
  }

  @GetMapping(path="/user/all")
  public @ResponseBody Iterable<UserP> getAllPersons() {
    // This returns a JSON or XML with the users
   return userRepository.findAll();
  } 

  @PutMapping(path="/user/update") // Map ONLY POST Requests
  public @ResponseBody String updateUser (
      @RequestParam String username,
      @RequestParam String password
    ) {
    Iterable<UserP> users = userRepository.findAll();
    users.forEach(user -> {
      if(user.getUsername().equals(username)){
        userRepository.delete(user);
        user.setPassword(password);
        userRepository.save(user);
      }
    });
    return "User Updated!";
  }
  
  @DeleteMapping(path="/user/delete") // Map ONLY POST Requests
  public @ResponseBody String deleteUsername (
      @RequestParam("username") String username
    ) {
    Iterable<UserP> users = userRepository.findAll();
    users.forEach(user -> {
      if(user.getUsername().equals(username)){
        userRepository.delete(user);
      }
    });
    return "User Deleted!";
  }
  
  @PostMapping(path="/desk/add") // Map ONLY POST Requests
  public @ResponseBody String addNewDesk (
      @RequestParam Integer width, 
      @RequestParam Integer height, 
      @RequestParam Integer length, 
      @RequestParam Float tariff, 
      @RequestParam String tariffType
    ) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Desk desk = new Desk();
    desk.setWidth(width);
    desk.setHeight(height);
    desk.setLength(length);
    desk.setTariff(tariff);
    desk.setTariffType(tariffType);
    deskRepository.save(desk);
    return "Saved";
  }

  @GetMapping(path="/desk/all")
  public @ResponseBody Iterable<Desk> getAllDesks() {
    // This returns a JSON or XML with the users
   return deskRepository.findAll();
  }  

  @PutMapping(path="/desk/update") // Map ONLY PUt Requests
  public @ResponseBody String updateDesk (
    @RequestParam(required = true) Integer id, 
    @RequestParam(required = false) Integer width, 
    @RequestParam(required = false) Integer height, 
    @RequestParam(required = false) Integer length, 
    @RequestParam(required = false) Float tariff, 
    @RequestParam(required = false) String tariffType
    ) {
    Iterable<Desk> desks = deskRepository.findAll();
    desks.forEach(desk -> {
      if(desk.getId().equals(id)){
        deskRepository.delete(desk);
        desk.setWidth(width);
        desk.setHeight(height);
        desk.setLength(length);
        desk.setTariff(tariff);
        desk.setTariffType(tariffType);
        deskRepository.save(desk);
      }
    });
    return "Desk Updated!";
  }
  
  @DeleteMapping(path="/desk/delete") // Map ONLY DELETE Requests
  public @ResponseBody String deleteDesk (
      @RequestParam("id") Integer id
    ) {
    Iterable<Desk> desks = deskRepository.findAll();
    desks.forEach(desk-> {
      if(desk.getId().equals(id)){
        deskRepository.delete(desk);
      }
    });
    return "Desk Deleted!";
  }

  @PostMapping(path="/room/add") // Map ONLY POST Requests
  public @ResponseBody String addNewRoom (
      @RequestParam Integer width, 
      @RequestParam Integer length, 
      @RequestParam List<Integer> deskList, 
      @RequestParam String details
    ) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Room room = new Room();
    room.setWidth(width);
    room.setLength(length);
    room.setDeskList(deskList);
    room.setDetails(details);
    roomRepository.save(room);
    return "Saved";
  }

  @GetMapping(path="/room/all")
  public @ResponseBody Iterable<Room> getAllRooms() {
    // This returns a JSON or XML with the users
   return roomRepository.findAll();
  }  

  @PutMapping(path="/room/update") // Map ONLY POST Requests
  public @ResponseBody String updateRoom (
      @RequestParam Integer id,
      @RequestParam(required = false) Integer width, 
      @RequestParam(required = false) Integer length, 
      @RequestParam(required = false) List<Integer> deskList, 
      @RequestParam(required = false) String details
    ) {
    Iterable<Room> rooms = roomRepository.findAll();
    rooms.forEach(room -> {
      if(room.getId().equals(id)){
        roomRepository.delete(room);
        room.setWidth(width);
        room.setLength(length);
        room.setDeskList(deskList);
        room.setDetails(details);
        roomRepository.save(room);
      }
    });
    return "Room Updated!";
  }
  
  @DeleteMapping(path="/room/delete") // Map ONLY POST Requests
  public @ResponseBody String deleteRoom (
      @RequestParam("id") String id
    ) {
    Iterable<Room> rooms = roomRepository.findAll();
    rooms.forEach(room -> {
      if(room.getId().equals(id)){
        roomRepository.delete(room);
      }
    });
    return "Room Deleted!";
  }
  
  
  @PostMapping(path="/desk_request/add") // Map ONLY POST Requests
  public @ResponseBody String addNewDeskRequest (
      @RequestParam String status, 
      @RequestParam Integer userId, 
      @RequestParam Integer deskId, 
      @RequestParam Date startDate, 
      @RequestParam Date endDate 
    ) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    DeskRequest deskRequest = new DeskRequest();
    deskRequest.setStatus(status);
    deskRequest.setUserId(userId);
    deskRequest.setDeskId(deskId);
    deskRequest.setStartDate(startDate);
    deskRequest.setEndDate(endDate);
    deskRequestRepository.save(deskRequest);
    return "Saved";
  }

  @GetMapping(path="/desk_request/all")
  public @ResponseBody Iterable<DeskRequest> getAllDeskRequests() {
    // This returns a JSON or XML with the users
   return deskRequestRepository.findAll();
  }


  
  @PutMapping(path="/desk_request/update") // Map ONLY POST Requests
  public @ResponseBody String updateDeskRequest (
      @RequestParam Integer id,
      @RequestParam(required = false) String status, 
      @RequestParam(required = false) Integer userId, 
      @RequestParam(required = false) Integer deskId, 
      @RequestParam(required = false) Date startDate,
      @RequestParam(required = false) Date endDate
    ) {
    Iterable<DeskRequest> deskRequests = deskRequestRepository.findAll();
    deskRequests.forEach(deskRequest -> {
      if(deskRequest.getId().equals(id)){
        deskRequestRepository.delete(deskRequest);
        deskRequest.setStatus(status);
        deskRequest.setUserId(userId);
        deskRequest.setDeskId(deskId);
        deskRequest.setStartDate(startDate);
        deskRequest.setEndDate(endDate);
        deskRequestRepository.save(deskRequest);
      }
    });
    return "Desk Request Updated!";
  }
  
  @DeleteMapping(path="/desk_request/delete") // Map ONLY POST Requests
  public @ResponseBody String deleteDeskRequest (
      @RequestParam("id") String id
    ) {
    Iterable<DeskRequest> deskRequests = deskRequestRepository.findAll();
    deskRequests.forEach(deskRequest -> {
      if(deskRequest.getId().equals(id)){
        deskRequestRepository.delete(deskRequest);
      }
    });
    return "Desk Request Deleted!";
  }
  
  

  
  @PostMapping(path="/order/add") // Map ONLY POST Requests
  public @ResponseBody String addNewOrder (
      @RequestParam Float total, 
      @RequestParam Integer userId, 
      @RequestParam Integer deskId, 
      @RequestParam String status
    ) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Order order = new Order();
    order.setTotal(total);
    order.setUserId(userId);
    order.setDeskId(deskId);
    order.setStatus(status);
    orderRepository.save(order);
    return "Order Saved";
  }

  @GetMapping(path="/order/all")
  public @ResponseBody Iterable<Order> getAllOrders() {
    // This returns a JSON or XML with the users
   return orderRepository.findAll();
  }  
  
}