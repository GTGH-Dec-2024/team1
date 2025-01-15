package com.team1.eventproject.controllers;

  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team1.eventproject.services.ReservationServices;
  
  @RestController
  @RequestMapping("reservations") 
  public class ReservationController {
  
  @Autowired  
  ReservationServices reservationServices; 
  
 
  
  
  
  }