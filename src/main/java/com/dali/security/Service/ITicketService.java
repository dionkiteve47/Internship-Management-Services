package com.dali.security.Service;

import com.dali.security.Entity.Tickets;

import java.util.List;

public interface ITicketService {
    Tickets addTicket(Tickets tickets);
    List<Tickets> getAllTickets();
    Tickets getTicketsById(long idTickets);
    void deleteTickets(long idTickets);
    Tickets updateTickets(Tickets Tickets);
    Tickets AddTicketAndAssign(Tickets tickets, long IdEvent);
}
