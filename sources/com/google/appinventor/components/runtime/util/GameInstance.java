package com.google.appinventor.components.runtime.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameInstance {
    private List<String> IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi;
    private Map<String, String> KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    private String x18EVrDLKYshSIJOaOzhjZZ2d7f1HPcCxsJsFR3IcZlc2NwZ4Zt12KeOiP0vPHe3;
    private String xjswzNK7XIbcXWMkpjjnX2zHmaN4l94b4PGb57QPnGA2cyF8Wf0hPGvkbrRqJbgG = "";

    public GameInstance(String str) {
        List<String> list;
        Map<String, String> map;
        new ArrayList(0);
        this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = list;
        new HashMap();
        this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = map;
        this.x18EVrDLKYshSIJOaOzhjZZ2d7f1HPcCxsJsFR3IcZlc2NwZ4Zt12KeOiP0vPHe3 = str;
    }

    public String getInstanceId() {
        return this.x18EVrDLKYshSIJOaOzhjZZ2d7f1HPcCxsJsFR3IcZlc2NwZ4Zt12KeOiP0vPHe3;
    }

    public String getLeader() {
        return this.xjswzNK7XIbcXWMkpjjnX2zHmaN4l94b4PGb57QPnGA2cyF8Wf0hPGvkbrRqJbgG;
    }

    public void setLeader(String str) {
        String str2 = str;
        this.xjswzNK7XIbcXWMkpjjnX2zHmaN4l94b4PGb57QPnGA2cyF8Wf0hPGvkbrRqJbgG = str2;
    }

    public PlayerListDelta setPlayers(List<String> list) {
        List list2;
        List<String> list3;
        PlayerListDelta playerListDelta;
        List<String> list4 = list;
        if (list4.equals(this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi)) {
            return PlayerListDelta.NO_CHANGE;
        }
        List<String> list5 = this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi;
        new ArrayList(list4);
        List list6 = list2;
        new ArrayList(list4);
        this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = list3;
        boolean removeAll = list6.removeAll(list5);
        boolean removeAll2 = list5.removeAll(list4);
        if (list6.size() == 0 && list5.size() == 0) {
            return PlayerListDelta.NO_CHANGE;
        }
        new PlayerListDelta(list5, list6);
        return playerListDelta;
    }

    public List<String> getPlayers() {
        return this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi;
    }

    public String getMessageTime(String str) {
        String str2 = str;
        if (this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.containsKey(str2)) {
            return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.get(str2);
        }
        return "";
    }

    public void putMessageTime(String str, String str2) {
        String put = this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH.put(str, str2);
    }
}
