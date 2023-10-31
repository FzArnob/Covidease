package com.github.ybq.android.spinkit;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.MultiplePulse;
import com.github.ybq.android.spinkit.style.MultiplePulseRing;
import com.github.ybq.android.spinkit.style.Pulse;
import com.github.ybq.android.spinkit.style.PulseRing;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.RotatingPlane;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.github.ybq.android.spinkit.style.Wave;

public class SpriteFactory {
    public SpriteFactory() {
    }

    public static Sprite create(Style style) {
        Sprite sprite;
        Sprite sprite2;
        Sprite sprite3;
        Sprite sprite4;
        Sprite sprite5;
        Sprite sprite6;
        Sprite sprite7;
        Sprite sprite8;
        Sprite sprite9;
        Sprite sprite10;
        Sprite sprite11;
        Sprite sprite12;
        Sprite sprite13;
        Sprite sprite14;
        Sprite sprite15;
        Sprite sprite16 = null;
        switch (style) {
            case ROTATING_PLANE:
                new RotatingPlane();
                sprite16 = sprite15;
                break;
            case DOUBLE_BOUNCE:
                new DoubleBounce();
                sprite16 = sprite14;
                break;
            case WAVE:
                new Wave();
                sprite16 = sprite13;
                break;
            case WANDERING_CUBES:
                new WanderingCubes();
                sprite16 = sprite12;
                break;
            case PULSE:
                new Pulse();
                sprite16 = sprite11;
                break;
            case CHASING_DOTS:
                new ChasingDots();
                sprite16 = sprite10;
                break;
            case THREE_BOUNCE:
                new ThreeBounce();
                sprite16 = sprite9;
                break;
            case CIRCLE:
                new Circle();
                sprite16 = sprite8;
                break;
            case CUBE_GRID:
                new CubeGrid();
                sprite16 = sprite7;
                break;
            case FADING_CIRCLE:
                new FadingCircle();
                sprite16 = sprite6;
                break;
            case FOLDING_CUBE:
                new FoldingCube();
                sprite16 = sprite5;
                break;
            case ROTATING_CIRCLE:
                new RotatingCircle();
                sprite16 = sprite4;
                break;
            case MULTIPLE_PULSE:
                new MultiplePulse();
                sprite16 = sprite3;
                break;
            case PULSE_RING:
                new PulseRing();
                sprite16 = sprite2;
                break;
            case MULTIPLE_PULSE_RING:
                new MultiplePulseRing();
                sprite16 = sprite;
                break;
        }
        return sprite16;
    }
}
