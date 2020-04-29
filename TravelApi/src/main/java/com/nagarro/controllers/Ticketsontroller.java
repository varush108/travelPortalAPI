package com.nagarro.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.nagarro.entities.Ticket;
import com.nagarro.repositories.TicketsRepository;

@RestController
@RequestMapping("/travelApi/v1")
public class Ticketsontroller {
	
	@Autowired
	private TicketsRepository ticketRepository;
	
	/**
	 * Get the list of all tickets
	 * @return
	 */
	@GetMapping("/tickets")
	public List<Ticket> getAllTickets(){
		
		return ticketRepository.findAll();
		
	}
	
	/**
	 * Get ticket by id
	 * @param ticketId
	 * @return ticket object associated with that id
	 * @throws ResourceAccessException
	 */
	@GetMapping("/tickets/{id}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable(value = "id") 
		Long ticketId) throws ResourceAccessException{
		
		Ticket ticket =  
				ticketRepository
				.findById(ticketId)
				.orElseThrow(() -> 
				new ResourceAccessException("Ticket not found for id : "+ ticketId));
		
		return ResponseEntity.ok().body(ticket); 
	}
	
	/**
	 * Create a new ticket
	 * @param ticket
	 * @return the newly created ticket object
	 */
	@PostMapping("/tickets")
	public Ticket createTicket(@Valid @RequestBody Ticket ticket) {
		return ticketRepository.save(ticket);
	}
}
