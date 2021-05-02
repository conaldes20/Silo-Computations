package com.conaldes.scomputations.compdata.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.conaldes.scomputations.util.Constants;

import java.io.Serializable;

@Entity(tableName = Constants.TABLE_NAME)
public class Result implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long result_id;

    @ColumnInfo(name = "result_content")
    // column name will be "result_content" instead of "content" in table
    private String content;

    private String type;

//    public Note(int note_id, String content, String type, Date date) {
//        this.note_id = note_id;
//        this.content = content;
//        this.type = type;
//        this.date = date;
//    }

    public Result(String content, String type) {
        this.content = content;
        this.type = type;
    }

    @Ignore
    public Result() {
    }

    public long getResult_id() {
        return result_id;
    }

    public void setResult_id(long result_id) {
        this.result_id = result_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object res) {
        if (this == res) return true;
        if (!(res instanceof Result)) return false;

        Result result = (Result) res;

        if (result_id != result.result_id) return false;
        return type != null ? type.equals(result.type) : result.type == null;
    }


    @Override
    public int hashCode() {
        int result = (int) result_id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result_id=" + result_id +
                ", content='" + content + '\'' +
                ", type=" + type +
                '}';
    }
}
