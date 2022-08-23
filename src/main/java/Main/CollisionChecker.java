package Main;

import Entity.Entity;
import Entity.Player;

import java.awt.*;
import java.awt.geom.Arc2D;

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
            case 0:
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; //[entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol]; //[entityTopRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;

                    int remaining =  entityTopWorldY - (entityTopRow + 1) *gp.tileSize;
                    if(remaining > 1){
                        entity.worldY -= remaining;
                    }
                }
                break;
            case 1:
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
            case 2:
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];//[entityTopRow][entityRightCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];//[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;

                    int remaining =  entityRightCol*gp.tileSize - entityRightWorldX;
                    if(remaining > 1){
                        entity.worldX += remaining - 1;
                    }
                }
                break;
            case 3:
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
            case 4:
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];//[entityBottomRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol]; //[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;

                    int remaining =  entityBottomRow*gp.tileSize - entityBottomWorldY;
                    if(remaining > 1){
                        entity.worldY += remaining - 1;
                    }
                }
                break;
            case 5:
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
            case 6:
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; // [entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol]; //[entityBottomRow][entityLeftCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collision = true;

                    int remaining =  entityLeftWorldX - entityLeftCol * gp.tileSize - gp.tileSize;
                    if(remaining > 1){
                        entity.worldX -= remaining;
                }


                }
                break;
            case 7:
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
                    case 0:
                        entity.solidArea.y -= entity.speed;
                        break;
                    case 1:
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y -= entity.speed;
                        break;
                    case 2:
                        entity.solidArea.x += entity.speed;
                        break;
                    case 3:
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y += entity.speed;
                        break;
                    case 4:
                        entity.solidArea.y += entity.speed;
                        break;
                    case 5:
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y += entity.speed;
                        break;
                    case 6:
                        entity.solidArea.x -= entity.speed;
                        break;
                    case 7:
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y -= entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                    entity.collision = true;
                    if(player){
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public int checkAttack(Player player, Entity[] target) {
        int index = 999;
        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                // get player world coordinates offset for attackArea
                // get entity solid area position
                player.solidArea.x = player.worldX + player.solidArea.x;
                player.solidArea.y = player.worldY + player.solidArea.y;
                // get object solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                int x = player.solidArea.x - gp.player.solidArea.width - gp.player.solidArea.width / 2;
                int y = player.solidArea.y - gp.player.solidArea.height - gp.player.solidArea.height / 2;
                switch (player.orientation) {
                    case 0:
                        gp.player.attackAreas = new Arc2D.Float(x, y, gp.player.radius, gp.player.radius, 0, 90, Arc2D.PIE);
                        break;
                    case 1:
                        gp.player.attackAreas = new Arc2D.Float(x, y, gp.player.radius, gp.player.radius, 270, 90, Arc2D.PIE);
                        break;
                    case 2:
                        gp.player.attackAreas = new Arc2D.Float(x, y, gp.player.radius, gp.player.radius, 180, 90, Arc2D.PIE);
                        break;
                    case 3:
                        gp.player.attackAreas = new Arc2D.Float(x, y, gp.player.radius, gp.player.radius, 90, 90, Arc2D.PIE);
                        break;
                }
                if (player.attackAreas.intersects(target[i].solidArea)) {
                    if (target[i] != player) {
                        player.collision = true;
                        index = i;
                    }
                }
                player.solidArea.x = player.solidAreaDefaultX;
                player.solidArea.y = player.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
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
                    case 0:
                        entity.solidArea.y -= entity.speed;
                        break;
                    case 4:
                        entity.solidArea.y += entity.speed;
                        break;
                    case 2:
                        entity.solidArea.x += entity.speed;
                        break;
                    case 6:
                        entity.solidArea.x -= entity.speed;
                        break;
                    case 7:
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y -= entity.speed;
                        break;
                    case 1:
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y -= entity.speed;
                        break;
                    case 3:
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y += entity.speed;
                        break;
                    case 5:
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y += entity.speed;
                        break;

                }
                if(entity.solidArea.intersects(target[i].solidArea)){
                    if(target[i] != entity){
                        entity.collision = true;
                        index = i;
                    }

                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public boolean checkPlayer(Entity entity){
        boolean contactPlayer = false;

        // get entity solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // get object solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        switch (entity.direction){
            case 0:
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea)){
                    entity.collision = true;
                    contactPlayer = true;
                }

                break;
            case 4:
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea)){
                    entity.collision = true;
                    contactPlayer = true;
                }

                break;
            case 2:
                entity.solidArea.x += entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea)){
                    entity.collision = true;
                    contactPlayer = true;
                }

                break;
            case 6:
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea)){
                    entity.collision = true;
                    contactPlayer = true;

                }

                break;
            case 7:
                entity.solidArea.x -= entity.speed;
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea) || entity.solidArea.intersects(gp.player.solidArea)){
                    entity.collision = true;
                    contactPlayer = true;
                }

                break;
            case 1:
                entity.solidArea.x += entity.speed;
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea) || entity.solidArea.intersects(gp.player.solidArea)){
                    entity.collision = true;
                    contactPlayer = true;
                }

                break;
            case 3:
                entity.solidArea.x += entity.speed;
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea) || entity.solidArea.intersects(gp.player.solidArea)){
                    entity.collision = true;
                    contactPlayer = true;
                }

                break;
            case 5:
                entity.solidArea.x -= entity.speed;
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea) || entity.solidArea.intersects(gp.player.solidArea)){
                    entity.collision = true;
                    contactPlayer = true;
                }

                break;

        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
    public void checkTileDash(Entity entity){
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
            case 0:
                entityTopRow = (entityTopWorldY - entity.dashSpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; //[entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol]; //[entityTopRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionDash = true;
                }
                break;
            case 4:
                entityBottomRow = (entityBottomWorldY + entity.dashSpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];//[entityBottomRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol]; //[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionDash = true;
                }
                break;
            case 6:
                entityLeftCol = (entityLeftWorldX - entity.dashSpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; // [entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol]; //[entityBottomRow][entityLeftCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionDash = true;
                }
                break;
            case 2:
                entityRightCol = (entityRightWorldX + entity.dashSpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];//[entityTopRow][entityRightCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];//[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionDash = true;
                }
                break;
            case 1:
                entityTopRow = (entityTopWorldY - entity.dashSpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; //[entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol]; //[entityTopRow][entityRightCol]
                //up
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.upOk = false;

                }
                entityTopRow = entityTopWorldY/gp.tileSize;
                //Right
                entityRightCol = (entityRightWorldX + entity.dashSpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];//[entityTopRow][entityRightCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];//[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.rightOk = false;

                }
                break;
            case 7:
                entityLeftCol = (entityLeftWorldX - entity.dashSpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; // [entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol]; //[entityBottomRow][entityLeftCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.leftOk = false;
                }
                entityLeftCol = entityLeftWorldX/gp.tileSize;

                entityTopRow = (entityTopWorldY - entity.dashSpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; //[entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol]; //[entityTopRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.upOk = false;
                }
                break;
            case 3:
                entityRightCol = (entityRightWorldX + entity.dashSpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];//[entityTopRow][entityRightCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];//[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.rightOk = false;
                }
                entityRightCol = entityRightWorldX/gp.tileSize;

                entityBottomRow = (entityBottomWorldY + entity.dashSpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];//[entityBottomRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol]; //[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.downOk = false;
                }
                break;
            case 5:
                entityLeftCol = (entityLeftWorldX - entity.dashSpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol]; // [entityTopRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol]; //[entityBottomRow][entityLeftCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.leftOk = false;
                }
                entityLeftCol = entityLeftWorldX/gp.tileSize;

                entityBottomRow = (entityBottomWorldY + entity.dashSpeed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];//[entityBottomRow][entityLeftCol]
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol]; //[entityBottomRow][entityRightCol]
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.downOk = false;
                }
                break;

        }

    }
    public int checkObjectDash(Entity entity, boolean player){
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
                    case 0:
                        entity.solidArea.y -= entity.dashSpeed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionDash = true;
                            }
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case 4:
                        entity.solidArea.y += entity.dashSpeed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionDash = true;
                            }
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case 2:
                        entity.solidArea.x += entity.dashSpeed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionDash = true;
                            }
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case 6:
                        entity.solidArea.x -= entity.dashSpeed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionDash = true;
                            }
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case 7:
                        entity.solidArea.x -= entity.dashSpeed;
                        entity.solidArea.y -= entity.dashSpeed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea) || entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionDash = true;
                            }
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case 1:
                        entity.solidArea.x += entity.dashSpeed;
                        entity.solidArea.y -= entity.dashSpeed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea) || entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionDash = true;
                            }
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case 3:
                        entity.solidArea.x += entity.dashSpeed;
                        entity.solidArea.y += entity.dashSpeed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea) || entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionDash = true;
                            }
                            if(player){
                                index = i;
                            }
                        }

                        break;
                    case 5:
                        entity.solidArea.x -= entity.dashSpeed;
                        entity.solidArea.y += entity.dashSpeed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea) || entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision){
                                entity.collisionDash = true;
                            }
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

}
