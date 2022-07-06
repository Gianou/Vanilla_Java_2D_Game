package Main;

import Entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; //[entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol]; //[entityTopRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];//[entityBottomRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol]; //[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; // [entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol]; //[entityBottomRow][entityLeftCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];//[entityTopRow][entityRightCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];//[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "rightUp":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];//[entityTopRow][entityRightCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];//[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;
                }
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; //[entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol]; //[entityTopRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "leftUp":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; // [entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol]; //[entityBottomRow][entityLeftCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;
                }
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; //[entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol]; //[entityTopRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "rightDown":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];//[entityTopRow][entityRightCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];//[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;
                }
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];//[entityBottomRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol]; //[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "leftDown":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; // [entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol]; //[entityBottomRow][entityLeftCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;
                }
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];//[entityBottomRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol]; //[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;

        }
    }
}