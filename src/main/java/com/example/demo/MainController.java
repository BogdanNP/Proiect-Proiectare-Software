package com.example.demo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.Desk;
import com.example.demo.models.DeskRequest;
import com.example.demo.models.Order;
import com.example.demo.models.Room;
import com.example.demo.models.UserP;
import com.example.demo.repos.DeskAlignmentRepository;
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
  
  @Autowired 
  private DeskAlignmentRepository deskAlignmentRepository;

  @PostMapping(path="/user/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (
      @RequestParam String username
    ) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    UserP user = new UserP();
    user.setUsername(username);
    userRepository.save(user);
    return "Saved";
  }

  @GetMapping(path="/user/all")
  public @ResponseBody Iterable<UserP> getAllPersons() {
    // This returns a JSON or XML with the users
   return userRepository.findAll();
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

  
  @PostMapping(path="/order/add") // Map ONLY POST Requests
  public @ResponseBody String addNewOrder (
      @RequestParam Double total, 
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