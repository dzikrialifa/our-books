package org.android.dzik.ourbooks.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Film implements Parcelable {


    private String pemesan;
    private int anak,dewasa,seat,jumlah;

    public static final int REGULER = 0;
    public static final int SWEET = 1;
    public static final int FAMILY = 2;

    public Film() {
    }

    public Film(String pemesan,int anak, int dewasa, int seat) {
        this.pemesan = pemesan;
        this.anak = anak;
        this.dewasa = dewasa;
        this.seat = seat;
        this.jumlah = hitung();
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getPemesan() {
        return pemesan;
    }

    public int getAnak() {
        return anak;
    }

    public int getDewasa() {
        return dewasa;
    }

    public int getSeat() {
        return seat;
    }

    protected Film(Parcel in){
        pemesan = in.readString();
        seat = in.readInt();
        anak = in.readInt();
        dewasa = in.readInt();
        jumlah = in.readInt();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return  new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[0];
        }
    };


    private int hitung(){
        switch (seat){
            case REGULER :
                return (anak * 15000) + (dewasa * 30000); // KURSI REGULER
            case SWEET :
                return (dewasa * 80000);
            case FAMILY :
                return (anak * 25000) + (dewasa * 50000);
            default:return 0;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(pemesan);
        parcel.writeInt(dewasa);
        parcel.writeInt(seat);
        parcel.writeInt(anak);
        parcel.writeInt(jumlah);
    }
}
