package com.example.eventsconferencespj.Model

import java.math.BigDecimal

class Conference(
    private var conID: Int,
    private var conName: String,
    private var conAdd: String,
    private var numSeat: Number,
    private var conPrice: BigDecimal,
    private var conRequired: Number
) {
}