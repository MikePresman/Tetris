package GameController;


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
        }

        public int getMatrixY(int canvasY){
            return canvasY / this.TILE_HEIGHT;
        }

        public int getMatrixX(int canvasX){
            return canvasX / this.TILE_WIDTH;
        }

        public void handleRowCleanup(){

        }
}
