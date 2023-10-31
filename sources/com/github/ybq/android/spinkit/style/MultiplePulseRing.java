package com.github.ybq.android.spinkit.style;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;

public class MultiplePulseRing extends SpriteContainer {
    public MultiplePulseRing() {
    }

    public Sprite[] onCreateChild() {
        Sprite sprite;
        Sprite sprite2;
        Sprite sprite3;
        Sprite[] spriteArr = new Sprite[3];
        new PulseRing();
        spriteArr[0] = sprite;
        Sprite[] spriteArr2 = spriteArr;
        new PulseRing();
        spriteArr2[1] = sprite2;
        Sprite[] spriteArr3 = spriteArr2;
        new PulseRing();
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
