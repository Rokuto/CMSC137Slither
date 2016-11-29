import java.util.Random;

public class SpawnPower extends Thread{

private static Tile tiles[][];
public static int players;
public static int powerMap[][];
public static int maxPower=15;

  public SpawnPower(Tile tiles[][]) {
    this.tiles = tiles;

    powerMap = new int[maxPower][2];
  }

  public void run() {
    while(true) {
      spawn();

      try{
				sleep(10000);
			}catch (Exception e) {
    }
    clearPower();
  }
}

  private void spawn() {
    boolean noSnake = true;

    for(int i=0; i<maxPower; i++) {
      Random rand = new Random();
  		int x = rand.nextInt(25);
      int y = rand.nextInt(25);
      int type = rand.nextInt(3) + 1;

    for(int j=0; j<ReceiveServer.players; j++) {
      if(ReceiveServer.snakes[j].isHead(x, y) || ReceiveServer.snakes[j].isBody(x, y)) {
        noSnake = false;
      }
    }

    if(noSnake) {
        powerMap[i][0] = x;
        powerMap[i][1] = y;

        switch(type) {
          case 1: tiles[x][y].placeSpeed();
                  break;
          case 2: tiles[x][y].placeSlow();
                  break;
          case 3: tiles[x][y].placePoison();
                  break;
        }
      }
    }
  }

  public void clearPower() {
    for(int i=0; i<maxPower; i++) {
      tiles[ powerMap[i][0] ][ powerMap[i][1] ].placeBlank();
    }
  }
}
