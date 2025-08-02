package org.example.renders;

import org.example.entities.Entity;
import org.example.renders.sprites.SpriteRegister;
import org.example.worlds.World;

public class RenderImpl implements Render{
    private static final String FIELD = "⬛";

    private final SpriteRegister spriteRegister;

    public RenderImpl(SpriteRegister spriteRegister) {
        this.spriteRegister = spriteRegister;
    }

    @Override
    public String rendering(World world) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= world.getSizeRow() ; i++) {
            for (int j = 1; j <= world.getSizeColumn() ; j++) {
                if (world.isEmptyKey(i,j)){
                    sb.append(FIELD);
                }else{
                    sb.append(getSprite(world.getByKey(i,j)));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String getSprite(Entity entity) {
       String sprite = spriteRegister.get(entity.getClass());
       if(sprite == null){
           throw new IllegalStateException("No sprite for this Entity");
       }
       return sprite;
    }
}