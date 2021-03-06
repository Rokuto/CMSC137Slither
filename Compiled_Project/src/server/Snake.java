import java.util.Random;

import java.lang.ArrayIndexOutOfBoundsException;


public class Snake extends Thread{

	private int playerNo;
	private int score = 0;
	private int turnCnt;
	private int time;

	private int direction;
	private int bodyMap[][];

	private static Tile tiles[][];
	private static int internalBoard[][];
	private boolean alive = true;

	private UDPServer server;

	public Snake(UDPServer server, Tile tiles[][], int internalBoard[][], int playerNo){
		this.internalBoard = internalBoard;
		this.playerNo = playerNo;
		this.server = server;
		this.tiles = tiles;

		bodyMap = new int[4][2];
		direction = 3;
		addToBoard();
		printBoard();
	}

	public void run(){
		while(alive){
			int type = this.move();
			// System.out.println("");
			printBoard();
			// System.out.println("");
			switch(type) {
				case 1: time = 200;
								turnCnt = 5;
								break;
				case 2: time = 800;
								turnCnt = 3;
								break;
				case 3: score--;
								if(turnCnt==0) {
									time = 500;
								} else {
									turnCnt--;
								}
								break;
				default: if(turnCnt==0) {
									time = 500;
								} else {
									turnCnt--;
								}
								break;
			}

			try{
				sleep(time);
			}catch (Exception e) {

			}
		}
	}

	public void printBoard(){
		// System.out.println("Snake " + playerNo);
		// String msg = " ";
		String msg = playerNo + " " + score + " ";
		for(int i = bodyMap[0][0] - 4; i < bodyMap[0][0] + 5; i++){
			for(int j = bodyMap[0][1] - 4; j < bodyMap[0][1] + 5; j++){
				if(i < 0 || i > (internalBoard[0].length - 1) || j < 0 || j > (internalBoard[0].length - 1)){
					msg += "0 ";
					// msg += "_ ";
					// System.out.print("_ ");
					// System.out.print("0 ");
					continue;
				}
				msg += internalBoard[i][j] + " ";
				// System.out.print(internalBoard[i][j] + " ");
			}
			// System.out.println("");
		}
		server.sendData(msg);
	}

	public void addToBoard(){
		Random rand = new Random();
		int  n = rand.nextInt(19);

		for(int i = 0; i < 4; i++){
			bodyMap[i][0] = n + i;
			bodyMap[i][1] = n;
		}

		renderBoard();
	}

	public void renderBoard(){
		if(alive) {
			for(int i = 0; i < 4; i++){
				if(i == 0){
					internalBoard[ bodyMap[i][0] ][ bodyMap[i][1] ] = direction;
					tiles[ bodyMap[i][0] ][ bodyMap[i][1] ].changehead(direction);
				}else{
					internalBoard[ bodyMap[i][0] ][ bodyMap[i][1] ] = 8;
					tiles[ bodyMap[i][0] ][ bodyMap[i][1] ].placeBody();
				}
			}
		} else {
			for(int i = 0; i < 4; i++){
					internalBoard[ bodyMap[i][0] ][ bodyMap[i][1] ] = 0;
					tiles[ bodyMap[i][0] ][ bodyMap[i][1] ].placeBlank();
			}
		}
	}

	public void changeDirection(int direction){
		switch(direction){
			case 1:
				if(this.direction <= 1){
					this.direction = 4;
				}else{
					this.direction--;
				}
				break;
			case 2:
				if(this.direction >= 4){
					this.direction = 1;
				}else{
					this.direction++;
				}
				break;
		}
	}

	public int move(){

		for(int i = 3; i > 0; i--){
			if(i == 3){
				internalBoard[ bodyMap[i][0] ][ bodyMap[i][1] ] = 0;
				tiles[ bodyMap[i][0] ][ bodyMap[i][1] ].placeBlank();
			}

			bodyMap[i][0] = bodyMap[i-1][0];
			bodyMap[i][1] = bodyMap[i-1][1];
		}

		switch( direction ){
			case 1:
				if(bodyMap[0][0] > 0){
					bodyMap[0][0]--;
				}else{
					bodyMap[0][0] = (internalBoard[0].length - 1);
				}
				break;
			case 2:
				if(bodyMap[0][1] < (internalBoard[0].length - 1)){
					bodyMap[0][1]++;
				}else{
					bodyMap[0][1] = 0;
				}
				break;
			case 3:
				if(bodyMap[0][0] < (internalBoard[0].length - 1)){
					bodyMap[0][0]++;
				}else{
					bodyMap[0][0] = 0;
				}
				break;
			case 4:
				if(bodyMap[0][1] > 0){
					bodyMap[0][1]--;
				}else{
					bodyMap[0][1] = (internalBoard[0].length - 1);
				}
				break;
			default:
			     break;
		}

		int type = detectPowerup();
		detectCollision();
		// System.out.println("(" + bodyMap[0][0] + "," + bodyMap[0][1] + ") : (" + bodyMap[1][0] + "," + bodyMap[1][1]  + ") : (" + bodyMap[2][0] + "," + bodyMap[2][1]  + ") : (" + bodyMap[3][0] + "," + bodyMap[3][1]  + ")");
		renderBoard();
		return type;
	}

	public boolean isHead(int x, int y) {
		if(bodyMap[0][0]==x && bodyMap[0][1]==y) {
			return true;
		}
		return false;
	}

	public boolean isBody(int x, int y) {
		for(int i=1; i<4; i++) {
			if(bodyMap[i][0]==x && bodyMap[0][1]==y) {
				return true;
			}
		}
		return false;
	}

	public boolean Alive() {
		return alive;
	}

	private void isKilled() {
		//if(alive == true) {
			alive = false;
			//detectCollision();
		//}

		for(int i=0; i<4; i++) {
			tiles[ bodyMap[i][0] ][ bodyMap[i][1] ].placeBlank();
			internalBoard[ bodyMap[i][0] ][ bodyMap[i][1] ] = 0;
		}
		//try {
			System.out.println("Snake " + playerNo + " died");
			//sleep(500);
			//this.join();
		//} catch(InterruptedException e) {
		//	System.out.println("Interrupted");
		//}
	}

	private void detectCollision() {
		for(int i=0; i<ReceiveServer.players; i++) {
			if(ReceiveServer.snakes[i].playerNo != playerNo && ReceiveServer.snakes[i].Alive()) {
					if(ReceiveServer.snakes[i].isHead(bodyMap[0][0], bodyMap[0][1])) {
						score++;
						System.out.println("Snake " + playerNo + " and snake " + ReceiveServer.snakes[i].playerNo + " killed each other ");
						ReceiveServer.snakes[i].isKilled();
						ReceiveServer.snakes[i].printBoard();
						ReceiveServer.snakes[i].renderBoard();
						isKilled();
						printBoard();
						renderBoard();
					} else if(ReceiveServer.snakes[i].isBody(bodyMap[0][0], bodyMap[0][1])) {
						score++;
						System.out.println("Snake " + playerNo + " killed snake " + ReceiveServer.snakes[i].playerNo + " Score " + score);
						ReceiveServer.snakes[i].isKilled();
						ReceiveServer.snakes[i].printBoard();
					}
				}
			}
		}

	private int detectPowerup() {
		for(int i=0; i<SpawnPower.maxPower; i++) {
			int x = SpawnPower.powerMap[i][0];
			int y = SpawnPower.powerMap[i][1];
			if(isHead(x, y)) {
				return (tiles[x][y].getType());
			}
		}
		return 0;
	}


}
