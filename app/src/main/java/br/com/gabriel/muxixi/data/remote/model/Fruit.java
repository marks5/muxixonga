
package br.com.gabriel.muxixi.data.remote.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fruit implements Parcelable
{

    @SerializedName("fruits")
    @Expose
    private List<Fruits> fruits = null;
    public final static Parcelable.Creator<Fruit> CREATOR = new Creator<Fruit>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Fruit createFromParcel(Parcel in) {
            Fruit instance = new Fruit();
            in.readList(instance.fruits, (Fruits.class.getClassLoader()));
            return instance;
        }

        public Fruit[] newArray(int size) {
            return (new Fruit[size]);
        }

    }
    ;

    public List<Fruits> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruits> fruits) {
        this.fruits = fruits;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(fruits);
    }

    public int describeContents() {
        return  0;
    }

}
