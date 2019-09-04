package org.ybk.excel;

import java.util.ArrayList;
import java.util.List;

public class SheetHead {
	private int startRow;
	private int startCell;
	private List<String> heads = new ArrayList<String>();

	public SheetHead() {

	}

	public SheetHead(int startRow, int startCell) {
		this.startRow = startRow;
		this.startCell = startCell;
	}

	public SheetHead(int startRow, int startCell, List<String> heads) {
		super();
		this.startRow = startRow;
		this.startCell = startCell;
		this.heads = heads;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getStartCell() {
		return startCell;
	}

	public void setStartCell(int startCell) {
		this.startCell = startCell;
	}

	public List<String> getHeads() {
		return heads;
	}

	public void setHeads(List<String> heads) {
		this.heads = heads;
	}

}
