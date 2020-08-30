package GameController;


import java.util.*;

public class BoardLogic {
    private int TILE_HEIGHT;
    private int TILE_WIDTH;

    public BoardLogic(int TILE_HEIGHT, int TILE_WIDTH){
        this.TILE_HEIGHT = TILE_HEIGHT;
        this.TILE_WIDTH = TILE_WIDTH;
    }

    public static Block[][] boardMatrix = {
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
        };


        public void addPieceToBoard(PieceAbstraction piece){
            for (int i = 0; i < piece.pieceConstructed.amountOfBlocks; i++){
                int matrixY = this.getMatrixY(piece.pieceConstructed.blockContainer.get(i).Y_POSITION);
                int matrixX = this.getMatrixX(piece.pieceConstructed.blockContainer.get(i).X_POSITION);
                boardMatrix[matrixY][matrixX] = piece.pieceConstructed.blockContainer.get(i);
            }
            //printMatrix(this.boardMatrix);

        }

    public void printMatrix(Block[][] m){
        try{
            int rows = m.length;
            int columns = m[0].length;
            String str = "|\t";

            for(int i=0;i<rows;i++){
                for(int j=0;j<columns;j++){
                    str += m[i][j] + "\t";
                }

                System.out.println(str + "|");
                str = "|\t";
            }

        }catch(Exception e){System.out.println("Matrix is empty!!");}
    }

        public int getMatrixY(int canvasY){
            return canvasY / this.TILE_HEIGHT;
        }

        public int getMatrixX(int canvasX){
            return canvasX / this.TILE_WIDTH;
        }

    public void handleRowCleanup() {
        ArrayList<Integer> rowsFilled = new ArrayList<>();

        //Here we are adding all full rows
        for (int row = 0; row < this.boardMatrix.length; row++) {
            for (int tile = 0; tile < this.boardMatrix[row].length; tile++) {
                if (this.boardMatrix[row][tile] == null) {
                    break;
                } else if (tile == this.boardMatrix[row].length - 1) {
                    rowsFilled.add(row);
                }
            }
        }

        for (int row = 0; row < rowsFilled.size(); row++) {
            for (int tile = 0; tile < this.boardMatrix[rowsFilled.get(row)].length; tile++) {
                this.boardMatrix[rowsFilled.get(row)][tile] = null;
            }
        }


        rowsFilled.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.intValue() - o1.intValue();
            }
        });

        if (rowsFilled.size() > 0) {
            //iterating through each row
            for (int i = 0; i < rowsFilled.size(); i++) {
                for (int rowToShift = rowsFilled.get(i) - 1; rowToShift >= 0; rowToShift--) { //rowToShift == 14

                    //iteration to check for consecutive rows
                    int x = 1;
                    //Checking for consecutive rows
                    while (i + x < rowsFilled.size() &&  rowToShift == rowsFilled.get(i + x)) {
                        rowToShift--;
                        x++;
                    }

                    for (int tile = 0; tile < this.boardMatrix[rowToShift].length; tile++) {
                        if (this.boardMatrix[rowToShift][tile] != null) {
                            Block temp = this.boardMatrix[rowToShift][tile]; //Temp object
                            temp.Y_POSITION += x * this.TILE_HEIGHT;
                            this.boardMatrix[rowToShift][tile] = null;

                            this.boardMatrix[rowToShift + x][tile] = temp;
                        }
                    }
                }
            }
        }
    }

    public int[] vectorizePoint(Block block) {
            return new int[]{this.getMatrixY(block.Y_POSITION), this.getMatrixX(block.X_POSITION)};
    }
}