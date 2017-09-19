package com.example.admin.myapplication.searialize;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tin on 2017/9/19.
 */

public class BabyInfo implements Parcelable {

    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BabyInfo() {

    }

    protected BabyInfo(Parcel in) {
        //如果元素数据是list类型的时候需要： lits = new ArrayList<?> in.readList(list);
        //否则会出现空指针异常.并且读出和写入的数据类型必须相同.如果不想对部分关键字进行序列化,可以使用transient关键字来修饰以及static修饰.
        this.name = in.readString();
        this.id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.id);
    }

    public static final Creator<BabyInfo> CREATOR = new Creator<BabyInfo>() {
        @Override
        public BabyInfo createFromParcel(Parcel in) {
            return new BabyInfo(in);
        }

        @Override
        public BabyInfo[] newArray(int size) {
            return new BabyInfo[size];
        }
    };
}
