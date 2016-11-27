import java.util.Random;

public class SpawnPower extends Thread{

private static Tile tiles[][];
private int powerMap[][];
private int maxPower=15;

  public SpawnPower(Tile tiles[][]) {
    this.tiles = tiles;

    powerMap = new int[25][2];
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
    for(int i=0; i<maxPower; i++) {
      Random rand = new Random();
  		int x = rand.nextInt(25);
      int y = rand.nextInt(25);
      int type = rand.nextInt(3) + 1;
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

  public void clearPower() {
    for(int i=0; i<maxPower; i++) {
      tiles[ powerMap[i][0] ][ powerMap[i][1] ].placeBlank();
    }
  }
}
