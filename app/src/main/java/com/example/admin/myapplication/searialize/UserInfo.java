package com.example.admin.myapplication.searialize;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by Tin on 2017/9/19.
 */

public class UserInfo implements Serializable {

    private static final long serialVersionUID = -5700609533761139882L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static UserInfo getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private UserInfo() {
        if (SingleTonHolder.INSTANCE != null) { //避免反射机制,导致的多例问题
            return;
        }
    }

    private static class SingleTonHolder {
        private static final UserInfo INSTANCE = new UserInfo();
    }

    public Object readResolve() throws ObjectStreamException {
        return SingleTonHolder.INSTANCE;
    }

/*    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        int chars = in.readInt();
        byte[] bytes = new byte[chars];
        in.readFully(bytes);
        this.name = new String(bytes, "utf8");
    }

    public void writeObject(ObjectOutputStream oos) throws IOException{
        oos.writeChars(this.name);
    }*/


    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                '}';
    }
}
