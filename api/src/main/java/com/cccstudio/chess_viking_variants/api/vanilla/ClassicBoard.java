package com.cccstudio.chess_viking_variants.api.vanilla;

import com.cccstudio.chess_viking_variants.api.Board;
import com.cccstudio.chess_viking_variants.api.CasePos;
import com.cccstudio.chess_viking_variants.api.PieceType;
import com.cccstudio.chess_viking_variants.api.vanilla.pieces.Pawn;
import com.cccstudio.chess_viking_variants.api.vanilla.pieces.Queen;
import com.cccstudio.chess_viking_variants.api.vanilla.pieces.Rook;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ClassicBoard extends Board {

    @Override
    public Map<PieceType, Byte[]> buildBoard() {
        return staticBoard(Map.of(
                new Pawn(), Map.ofEntries(),
                new Queen(), Map.of(3, (byte) 2, 59, (byte) 1),
                new Rook(), Map.of(0, ((byte) 2),  7, ((byte) 2), 56, ((byte) 1),  63, ((byte) 1))
        ), 64);
    }

    @Override
    public LinkedHashSet<CasePos> setup() {
        LinkedHashSet<CasePos> casePosSet = new LinkedHashSet<>();
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                casePosSet.add(new CasePos(x, y));
            }
        }
        return casePosSet;
    }

    @Override
    public int height() {
        return 8;
    }

    @Override
    public int size() {
        return 64;
    }

    @Override
    public Board clone() {
        Board cloned = new ClassicBoard();
        cloned.bitboards = this.bitboards;
        return cloned;
    }

    /**
     * @param pieces
     * Each entry contains one of the piece type that is by default present on the board
     * and a map of where there are pieces and the byte index of their owner.
     * @param size
     * The size of the board. Should be equal to {@code Board.setup().length}
     * @return
     * The bitboards of this board.
     */
    public static Map<PieceType, Byte[]> staticBoard(Map<PieceType, Map<Integer, Byte>> pieces, int size){
        Map<PieceType, Byte[]> board = new HashMap<>();
        for(Map.Entry<PieceType, Map<Integer, Byte>> entry : pieces.entrySet()){
            Map<Integer, Byte> positions = entry.getValue();
            Stream<Byte> base = IntStream.range(0, size).mapToObj(
                    i -> positions.computeIfAbsent(i, ignored -> (byte) 0)
            );
            board.put(entry.getKey(), (Byte[]) base.toArray());
        }
        return board;
    }

}
