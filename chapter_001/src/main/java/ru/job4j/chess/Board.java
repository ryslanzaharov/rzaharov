package ru.job4j.chess;

public class Board {

    public Figure[][] cells = new Figure[8][8];



    public String nameRowAndColumn (int row, int col) {
        return String.format("%s%s",(char)('`'+col), row);
    }

    public String getCellByCellName(String name) {
        int col = name.charAt(0) - 'a';
        int row = Character.digit(name.charAt(1), 10);
        return String.format("%s%s", row, col);
    }

    public boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        int sourceRow = source.getRow();

        int sourceCol = source.getCol();
        boolean result =false;
        try {
            if (cells[sourceRow][sourceCol] == null || sourceRow < 0 || sourceCol < 0 || sourceCol > 7 || sourceCol > 7) {
                throw new FigureNotFoundException("Figure not found!");
            }
            Cell[] cellWay = cells[sourceRow][sourceCol].way(dist);
            for (Cell cell : cellWay) {
                if (cell == null) {
                    break;
                }
                if (cells[cell.getRow()][cell.getCol()] != null) {
                    throw new OccupiedWayException("There is another figure on the way");
                }
            }
        }catch (FigureNotFoundException | ImpossibleMoveException | OccupiedWayException e) {
            System.out.println(e);
        }
        cells[dist.getRow()][dist.getCol()] = cells[sourceRow][sourceCol].clone(dist);
        cells[sourceRow][sourceCol] = null;
        result = true;
        return result;
    }


}
