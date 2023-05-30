package com.example.assignment2

data class TicketModel(var id: Int = 0,
                       var fullName: String = "",
                       var departure: String = "",
                       var destination: String = "",
                       var departureDateTime: String = "",
                       var flightClass: String = "") {}