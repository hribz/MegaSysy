package ir;

import java.util.LinkedList;
import java.util.logging.Logger;

public abstract class Value {
    private LinkedList<Use> UseList;
    private Type type;
    private String name;

    public int getUseSize() {
        return UseList.size();
    }

    public LinkedList<Use> getUseList() {
        return UseList;
    }

    public void setUseList(LinkedList<Use> useList) {
        UseList = useList;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Value(Type type) {
        this.type = type;
        setName("x"+String.valueOf(MyContext.valuePtr++));
    }

    public Value(Type type, String name) {
        this.type = type;
        setName(name);
    }

    public User userBack() {
        if (UseList.isEmpty()) return null;
        return UseList.peek().getU();
    }

    public void addUse(Use U) {
        if (UseList == null) UseList = new LinkedList<>();
        U.addToList(UseList);
    }

    public void removeUse(Use U) {
        UseList.remove(U);
    }

    public void removeUseByUser(User U) {
        UseList.removeIf(use -> use.getU() == (U));
    }

    public void replaceAllUsesWith(Value V) {

    }
}
