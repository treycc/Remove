package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/9/14 0014 下午 4:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FamilyMember implements Serializable{

    /**
     * PersonId : 96e1b34c-3da9-4b93-90c6-02bf7403545b
     * RealName : 杯子啊备注
     * Gender : false
     * BookletId : 0
     * PoliticalTitle : 1
     * PoliticalTitleDesc : 群众
     * TypeId : 1
     * TypeName : 户主
     * Idcard : 330355881949100999
     * MobilePhone : 18868269008
     * Address : 特福隆集团
     * IsFarming : False
     * IsHouseOwner : False
     * IsEnterprisePrincipal : False
     * IsLegalPerson : False
     */

    private String PersonId;
    private String RealName;
    private boolean Gender;
    private int BookletId;
    private int PoliticalTitle;
    private String PoliticalTitleDesc;
    private int TypeId;
    private String TypeName;
    private String Idcard;
    private String MobilePhone;
    private String Address;
    private boolean IsFarming;
    private String IsHouseOwner;
    private String IsEnterprisePrincipal;
    private String IsLegalPerson;
    private String BookletNum;

    public String getBookletNum() {
        return BookletNum;
    }

    public void setBookletNum(String bookletNum) {
        BookletNum = bookletNum;
    }

    public String getPersonId() {
        return PersonId;
    }

    public void setPersonId(String PersonId) {
        this.PersonId = PersonId;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean Gender) {
        this.Gender = Gender;
    }

    public int getBookletId() {
        return BookletId;
    }

    public void setBookletId(int BookletId) {
        this.BookletId = BookletId;
    }

    public int getPoliticalTitle() {
        return PoliticalTitle;
    }

    public void setPoliticalTitle(int PoliticalTitle) {
        this.PoliticalTitle = PoliticalTitle;
    }

    public String getPoliticalTitleDesc() {
        return PoliticalTitleDesc;
    }

    public void setPoliticalTitleDesc(String PoliticalTitleDesc) {
        this.PoliticalTitleDesc = PoliticalTitleDesc;
    }

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int TypeId) {
        this.TypeId = TypeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    public String getIdcard() {
        return Idcard;
    }

    public void setIdcard(String Idcard) {
        this.Idcard = Idcard;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String MobilePhone) {
        this.MobilePhone = MobilePhone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public boolean getIsFarming() {
        return IsFarming;
    }

    public void setIsFarming(boolean IsFarming) {
        this.IsFarming = IsFarming;
    }

    public String getIsHouseOwner() {
        return IsHouseOwner;
    }

    public void setIsHouseOwner(String IsHouseOwner) {
        this.IsHouseOwner = IsHouseOwner;
    }

    public String getIsEnterprisePrincipal() {
        return IsEnterprisePrincipal;
    }

    public void setIsEnterprisePrincipal(String IsEnterprisePrincipal) {
        this.IsEnterprisePrincipal = IsEnterprisePrincipal;
    }

    public String getIsLegalPerson() {
        return IsLegalPerson;
    }

    public void setIsLegalPerson(String IsLegalPerson) {
        this.IsLegalPerson = IsLegalPerson;
    }
}
