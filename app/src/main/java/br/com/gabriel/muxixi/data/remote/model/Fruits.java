
package br.com.gabriel.muxixi.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fruits implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("price")
    @Expose
    private Double price;

    public Fruits() {
    }

    public Fruits(String name, String image, Double price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public final static Parcelable.Creator<Fruits> CREATOR = new Creator<Fruits>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Fruits createFromParcel(Parcel in) {
            Fruits instance = new Fruits();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.image = ((String) in.readValue((String.class.getClassLoader())));
            instance.price = ((Double) in.readValue((Double.class.getClassLoader())));
            return instance;
        }

        public Fruits[] newArray(int size) {
            return (new Fruits[size]);
        }

    }
    ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(image);
        dest.writeValue(price);
    }

    public int describeContents() {
        return  0;
    }

}
