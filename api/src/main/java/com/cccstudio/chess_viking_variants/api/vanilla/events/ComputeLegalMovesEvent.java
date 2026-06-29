package com.cccstudio.chess_viking_variants.api.vanilla.events;

import com.cccstudio.chess_viking_variants.api.Move;
import com.cccstudio.chess_viking_variants.api.event.ComputationEvent;

import java.util.Set;

public class ComputeLegalMovesEvent extends ComputationEvent<Set<Move>> {

    public ComputeLegalMovesEvent(Set<Move> computed) {
        super(computed);
    }

}