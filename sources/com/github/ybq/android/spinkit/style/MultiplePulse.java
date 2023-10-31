package com.github.ybq.android.spinkit.style;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;

public class MultiplePulse extends SpriteContainer {
    public MultiplePulse() {
    }

    public Sprite[] onCreateChild() {
        Sprite sprite;
        Sprite sprite2;
        Sprite sprite3;
        Sprite[] spriteArr = new Sprite[3];
        new Pulse();
        spriteArr[0] = sprite;
        Sprite[] spriteArr2 = spriteArr;
        new Pulse();
        spriteArr2[1] = sprite2;
        Sprite[] spriteArr3 = spriteArr2;
        new Pulse();
        spriteArr3[2] = sprite3;
        return spriteArr3;
    }

    public void onChildCreated(Sprite... spriteArr) {
        Sprite[] sprites = spriteArr;
        for (int i = 0; i < sprites.length; i++) {
            Sprite animationDelay = sprites[i].setAnimationDelay(200 * (i + 1));
        }
    }
}
