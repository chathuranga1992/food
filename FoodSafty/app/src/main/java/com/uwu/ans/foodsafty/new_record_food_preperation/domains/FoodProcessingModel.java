
package com.uwu.ans.foodsafty.new_record_food_preperation.domains;


@SuppressWarnings("unused")
public class FoodProcessingModel {


    private String mId;
    private String mRecordId;
    private String mComment;
    private String mTotalMarks;
    private CeilingStructure mCeilingStructure;
    private Cleaning mCleaning;
    private Cleanlines mCleanlines;
    private Contamination mContamination;
    private Drainage mDrainage;
    private FloorStructure mFloorStructure;
    private HouseKeeping mHouseKeeping;
    private LightNVentilation mLightNVentilation;
    private PestControl mPestControl;
    private Sanitation mSanitation;
    private Space mSpace;
    private WallMaintatance mWallMaintatance;

    public FoodProcessingModel(String mId, String mRecordId, CeilingStructure mCeilingStructure,
                               Cleaning mCleaning, Cleanlines mCleanlines, Contamination mContamination,
                               Drainage mDrainage, FloorStructure mFloorStructure, HouseKeeping mHouseKeeping,
                               LightNVentilation mLightNVentilation, PestControl mPestControl,
                               Sanitation mSanitation, Space mSpace, WallMaintatance mWallMaintatance,
                               String mTotalMarks, String mComment) {
        this.mId = mId;
        this.mRecordId = mRecordId;
        this.mCeilingStructure = mCeilingStructure;
        this.mCleaning = mCleaning;
        this.mCleanlines = mCleanlines;
        this.mContamination = mContamination;
        this.mDrainage = mDrainage;
        this.mFloorStructure = mFloorStructure;
        this.mHouseKeeping = mHouseKeeping;
        this.mLightNVentilation = mLightNVentilation;
        this.mPestControl = mPestControl;
        this.mSanitation = mSanitation;
        this.mSpace = mSpace;
        this.mWallMaintatance = mWallMaintatance;
        this.mTotalMarks = mTotalMarks;
        this.mComment = mComment;
    }

    public CeilingStructure getCeilingStructure() {
        return mCeilingStructure;
    }

    public void setCeilingStructure(CeilingStructure ceilingStructure) {
        mCeilingStructure = ceilingStructure;
    }

    public Cleaning getCleaning() {
        return mCleaning;
    }

    public void setCleaning(Cleaning cleaning) {
        mCleaning = cleaning;
    }

    public Cleanlines getCleanlines() {
        return mCleanlines;
    }

    public void setCleanlines(Cleanlines cleanlines) {
        mCleanlines = cleanlines;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public Contamination getContamination() {
        return mContamination;
    }

    public void setContamination(Contamination contamination) {
        mContamination = contamination;
    }

    public Drainage getDrainage() {
        return mDrainage;
    }

    public void setDrainage(Drainage drainage) {
        mDrainage = drainage;
    }

    public FloorStructure getFloorStructure() {
        return mFloorStructure;
    }

    public void setFloorStructure(FloorStructure floorStructure) {
        mFloorStructure = floorStructure;
    }

    public HouseKeeping getHouseKeeping() {
        return mHouseKeeping;
    }

    public void setHouseKeeping(HouseKeeping houseKeeping) {
        mHouseKeeping = houseKeeping;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public LightNVentilation getLightNVentilation() {
        return mLightNVentilation;
    }

    public void setLightNVentilation(LightNVentilation lightNVentilation) {
        mLightNVentilation = lightNVentilation;
    }

    public PestControl getPestControl() {
        return mPestControl;
    }

    public void setPestControl(PestControl pestControl) {
        mPestControl = pestControl;
    }

    public String getRecordId() {
        return mRecordId;
    }

    public void setRecordId(String recordId) {
        mRecordId = recordId;
    }

    public Sanitation getSanitation() {
        return mSanitation;
    }

    public void setSanitation(Sanitation sanitation) {
        mSanitation = sanitation;
    }

    public Space getSpace() {
        return mSpace;
    }

    public void setSpace(Space space) {
        mSpace = space;
    }

    public String getTotalMarks() {
        return mTotalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        mTotalMarks = totalMarks;
    }

    public WallMaintatance getWallMaintatance() {
        return mWallMaintatance;
    }

    public void setWallMaintatance(WallMaintatance wallMaintatance) {
        mWallMaintatance = wallMaintatance;
    }

}
