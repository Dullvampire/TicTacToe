
public class Main {
	
	public int boardState = 0:
	
	/* 
	 * Return codes :
	 * 		0 = no win
	 * 		1 = p1 wins
	 * 		2 = p2 wins
	 * 		3 = tie
	 */
	public static byte check (int state) {
		byte returnCode = 0;
		int checkState = state >> 9;
		
		if (checkState == 0)
			return 0;
		
		int[] checks = {7, 21, 56, 73, 146, 273, 292, 448};
		
		for (int check : checks) {
			if ((checkState & check) == check) {
				int winner = ((state & check) == check) ? 1 : 0;
				returnCode = (byte) rCodeCheck(returnCode, winner);
			}
		}
		
		return (checkState == 510 && returnCode == 0) ? 3 : returnCode;
	}
	
	private static int rCodeCheck (int rCode, int newCode) throws IllegalArgumentException {
		if (rCode == 0 || rCode == newCode) {
			return newCode;
		}
		
		throw new IllegalArgumentException("Impossible board state!");
	}
	
	//TODO: REMOVE THIS
	private static int boardState (int[] board) {
		int[] stateBoard = new int[board.length];
		
		for (int i = 0; i < board.length; i ++) {
			if (board[i] < 2) {
				stateBoard[i] = 1;
			} else {
				stateBoard[i] = 0;
			}
		}
		
		int returnValue = 0;
		
		for (int i = 0; i < stateBoard.length; i ++) {
			returnValue = (returnValue << 1) | (stateBoard[i]);
		}
		
		for (int i = 0; i < board.length; i ++) {
			returnValue = (returnValue << 1) | ((board[i] < 2) ? board[i] : 0);
		}
		
		return returnValue;
	}
	
	public static void main(String[] args) {
		int[] board = {1, 1, 1,
					   2, 2, 2,
					   2, 2, 2};
		System.out.println(check(boardState(board)));
	}

}
