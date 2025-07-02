package me.cworldstar.highriseSF.impl.items.armor;

import java.util.ArrayList;
import java.util.List;

public abstract interface ArmorSetHolder {
	final List<ArmorSetPiece> pieces = new ArrayList<ArmorSetPiece>();
	
	default void addPiece(ArmorSetPiece piece) {
		ArmorSetHolder.pieces.add(piece);
	}
	
	default void removePiece(ArmorSetPiece piece) {
		pieces.removeIf(piece2 -> piece2.equals(piece));
	}	
}
