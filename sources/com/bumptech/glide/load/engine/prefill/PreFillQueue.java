package com.bumptech.glide.load.engine.prefill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class PreFillQueue {
    private final Map<PreFillType, Integer> bitmapsPerType;
    private int bitmapsRemaining;
    private int keyIndex;
    private final List<PreFillType> keyList;

    public PreFillQueue(Map<PreFillType, Integer> map) {
        List<PreFillType> list;
        Map<PreFillType, Integer> bitmapsPerType2 = map;
        this.bitmapsPerType = bitmapsPerType2;
        new ArrayList(bitmapsPerType2.keySet());
        this.keyList = list;
        for (Integer count : bitmapsPerType2.values()) {
            this.bitmapsRemaining += count.intValue();
        }
    }

    public PreFillType remove() {
        PreFillType result = this.keyList.get(this.keyIndex);
        Integer countForResult = this.bitmapsPerType.get(result);
        if (countForResult.intValue() == 1) {
            Integer remove = this.bitmapsPerType.remove(result);
            PreFillType remove2 = this.keyList.remove(this.keyIndex);
        } else {
            Integer put = this.bitmapsPerType.put(result, Integer.valueOf(countForResult.intValue() - 1));
        }
        this.bitmapsRemaining--;
        this.keyIndex = this.keyList.isEmpty() ? 0 : (this.keyIndex + 1) % this.keyList.size();
        return result;
    }

    public int getSize() {
        return this.bitmapsRemaining;
    }

    public boolean isEmpty() {
        return this.bitmapsRemaining == 0;
    }
}
