package Main;

import Entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    boolean upOk = true, rightOk = false;

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
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; //[entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol]; //[entityTopRow][entityRightCol]
                //up
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.upOk = false;

                }
                entityTopRow = entityTopWorldY/gp.tileSize;
                //Right
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];//[entityTopRow][entityRightCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];//[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.rightOk = false;

                }


                break;
            case "leftUp":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; // [entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol]; //[entityBottomRow][entityLeftCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.leftOk = false;
                }
                entityLeftCol = entityLeftWorldX/gp.tileSize;

                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; //[entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol]; //[entityTopRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.upOk = false;
                }
                break;
            case "rightDown":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];//[entityTopRow][entityRightCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];//[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.rightOk = false;
                }
                entityRightCol = entityRightWorldX/gp.tileSize;

                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];//[entityBottomRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol]; //[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.downOk = false;
                }
                break;
            case "leftDown":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; // [entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol]; //[entityBottomRow][entityLeftCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.leftOk = false;
                }
                entityLeftCol = entityLeftWorldX/gp.tileSize;

                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];//[entityBottomRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol]; //[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.downOk = false;
                }
                break;

        }

    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){

                // get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get object solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            entity.collision = true;
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            entity.collision = true;
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            entity.collision = true;
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            entity.collision = true;
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case "leftUp":
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea) || entity.solidArea.intersects(gp.obj[i].solidArea)){
                            entity.collision = true;
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case "rightUp":
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea) || entity.solidArea.intersects(gp.obj[i].solidArea)){
                            entity.collision = true;
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case "rightDown":
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea) || entity.solidArea.intersects(gp.obj[i].solidArea)){
                            entity.collision = true;
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case "leftDown":
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea) || entity.solidArea.intersects(gp.obj[i].solidArea)){
                            entity.collision = true;
                            if(player){
                                index = i;
                            }
                        }

                        break;

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;
        for (int i = 0; i < target.length; i++){
            if(target[i] != null){

                // get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get object solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collision = true;
                            index = i;

                        }

                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collision = true;

                            index = i;

                        }

                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collision = true;

                                index = i;

                        }

                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collision = true;

                                index = i;

                        }

                        break;
                    case "leftUp":
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea) || entity.solidArea.intersects(target[i].solidArea)){
                            entity.collision = true;

                                index = i;

                        }

                        break;
                    case "rightUp":
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea) || entity.solidArea.intersects(target[i].solidArea)){
                            entity.collision = true;

                                index = i;

                        }

                        break;
                    case "rightDown":
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea) || entity.solidArea.intersects(target[i].solidArea)){
                            entity.collision = true;
                                index = i;

                        }

                        break;
                    case "leftDown":
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea) || entity.solidArea.intersects(target[i].solidArea)){
                            entity.collision = true;

                                index = i;

                        }

                        break;

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

}
