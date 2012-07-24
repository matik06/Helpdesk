/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author matik06
 */
@ManagedBean
public class BidBean2 {

    private String userID;
    private String keyword;
    private Double bidAmount = 0d;
    private Integer bidDuration = 0;

    
    public Double getBidAmount() {
        return (bidAmount);
    }

    public void setBidAmount(Double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Integer getBidDuration() {
        return (bidDuration);
    }

    public void setBidDuration(Integer bidDuration) {
        this.bidDuration = bidDuration;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
    
    public String doBid() {
        //doBusinessLogicForValidData();
        return ("/pages/validator/show-bid2");
    }
    
    public void validateBidAmount(FacesContext context, UIComponent componentToValidate, Object value) throws ValidatorException {
        
        double bidAmount = ((Double) value).doubleValue();
        double previousHighestBid = currentHighestBid();
        
        if (bidAmount <= previousHighestBid) {
            FacesMessage message =
                    new FacesMessage("Bid must be higher than current "
                    + "highest bid ($"
                    + previousHighestBid + ").");
            
            throw new ValidatorException(message);
        }
    }
    
    private double currentHighestBid() {
        return this.bidAmount;
    }
}
