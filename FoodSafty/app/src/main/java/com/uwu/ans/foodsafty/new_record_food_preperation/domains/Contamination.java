
package com.uwu.ans.foodsafty.new_record_food_preperation.domains;


@SuppressWarnings("unused")
public class Contamination {


    private String mFromGarbage;
    private String mFromHazards;
    private String mFromSanitires;
    private String mFromToilets;
    private String mMarks;
    private String mRemark;

    public Contamination(String mFromGarbage, String mFromHazards, String mFromSanitires, String mFromToilets, String mMarks, String mRemark) {
        this.mFromGarbage = mFromGarbage;
        this.mFromHazards = mFromHazards;
        this.mFromSanitires = mFromSanitires;
        this.mFromToilets = mFromToilets;
        this.mMarks = mMarks;
        this.mRemark = mRemark;
    }

    public String getFromGarbage() {
        return mFromGarbage;
    }

    public void setFromGarbage(String fromGarbage) {
        mFromGarbage = fromGarbage;
    }

    public String getFromHazards() {
        return mFromHazards;
    }

    public void setFromHazards(String fromHazards) {
        mFromHazards = fromHazards;
    }

    public String getFromSanitires() {
        return mFromSanitires;
    }

    public void setFromSanitires(String fromSanitires) {
        mFromSanitires = fromSanitires;
    }

    public String getFromToilets() {
        return mFromToilets;
    }

    public void setFromToilets(String fromToilets) {
        mFromToilets = fromToilets;
    }

    public String getMarks() {
        return mMarks;
    }

    public void setMarks(String marks) {
        mMarks = marks;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

}
