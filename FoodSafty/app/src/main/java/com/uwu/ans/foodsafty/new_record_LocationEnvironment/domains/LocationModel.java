
package com.uwu.ans.foodsafty.new_record_LocationEnvironment.domains;


@SuppressWarnings("unused")
public class LocationModel {

    private String mId;
    private String mRecordId;
    private String mComment;
    private String mTotalMarks;
    private Suitability mSuitability;
    private Appearance mAppearance;
    private Pollution mPollution;
    private Animals mAnimals;
    private String locationMarks;


    public LocationModel(String mId, String mRecordId, String mComment, String mTotalMarks, Suitability mSuitability, Appearance mAppearance, Pollution mPollution, Animals mAnimals, String locationMarks) {
        this.mId = mId;
        this.mRecordId = mRecordId;
        this.mComment = mComment;
        this.mTotalMarks = mTotalMarks;
        this.mSuitability = mSuitability;
        this.mAppearance = mAppearance;
        this.mPollution = mPollution;
        this.mAnimals = mAnimals;
        this.locationMarks = locationMarks;
    }

    public String getLocationMarks() {
        return locationMarks;
    }

    public void setLocationMarks(String locationMarks) {
        this.locationMarks = locationMarks;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmRecordId() {
        return mRecordId;
    }

    public void setmRecordId(String mRecordId) {
        this.mRecordId = mRecordId;
    }

    public String getmComment() {
        return mComment;
    }

    public void setmComment(String mComment) {
        this.mComment = mComment;
    }

    public String getmTotalMarks() {
        return mTotalMarks;
    }

    public void setmTotalMarks(String mTotalMarks) {
        this.mTotalMarks = mTotalMarks;
    }

    public Suitability getmSuitability() {
        return mSuitability;
    }

    public void setmSuitability(Suitability mSuitability) {
        this.mSuitability = mSuitability;
    }

    public Appearance getmAppearance() {
        return mAppearance;
    }

    public void setmAppearance(Appearance mAppearance) {
        this.mAppearance = mAppearance;
    }

    public Pollution getmPollution() {
        return mPollution;
    }

    public void setmPollution(Pollution mPollution) {
        this.mPollution = mPollution;
    }

    public Animals getmAnimals() {
        return mAnimals;
    }

    public void setmAnimals(Animals mAnimals) {
        this.mAnimals = mAnimals;
    }
}
