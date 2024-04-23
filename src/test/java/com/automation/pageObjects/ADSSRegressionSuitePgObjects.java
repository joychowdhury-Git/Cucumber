package com.automation.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.test.SetUp;

import junit.framework.Assert;

public class ADSSRegressionSuitePgObjects {
	
	WebDriver driver = SetUp.driver;
	
	//@FindBy(how=How.XPATH, using = "//*[@auto-test='switchTab0']")
	//@FindBy(xpath = "//p[@class='mb-0']")
	@FindBy(xpath = "//*[@auto-test='switchTab0']")
	public WebElement scheduleTab;
	
	@FindBy(xpath = "//mat-error")
	public WebElement materialError;
	
	@FindBy(xpath = "//*[contains(@class,'text-danger')]")
	public WebElement invalidCouponErrorMessage;
	
	@FindBy(xpath = "//*[@class='alert alert-danger']")
	public WebElement configError;
	
	@FindBy(xpath="//*[@auto-test='materialPreview']/..//*[@class='img-fluid']")
	public WebElement materialPreviewImage;
	
	@FindBy(xpath="//*[@class='img-fluid preview-img']")
	public WebElement materialPreviewImageAfterConfiguringImg;
	
	@FindBy(xpath="//*[@auto-test='viewCarousel']")
	public WebElement editMaterial;
	
	@FindBy(xpath="//ngu-tile//a")
	public List<WebElement> availableTemplates;

	@FindBy(xpath = "//*[@auto-test='removeDate']")
	public List<WebElement> defaultDates;

	@FindBy(xpath = "//*[@class='ngb-dp-months']/div[2]//*[@class='ngb-dp-day ng-star-inserted']/span")
	public List<WebElement> calendarDates;

	@FindBy(xpath = "//*[@auto-test='switchTab1']")
	public WebElement layoutTab;

	@FindBy(xpath="(//*[@auto-test='bindTemplates'])[1]")
	public WebElement adSizeId;

	@FindBy(xpath="//*[@auto-test='uploadImage']")
	public WebElement uploadButton;

	@FindBy(xpath = "//input[@title='Upload Image']")
	public WebElement uploadLink;

	@FindBy(xpath="//*[@auto-test='reviewAndSubmit']")
	public WebElement ReviewandSubmitButton;

	@FindBy(xpath="//textarea[@placeholder='Invoice notes']")
	public WebElement orderNotes;

	@FindBy(xpath="//*[@placeholder='Phone Number']")
	public WebElement contactPhoneNumber;

	@FindBy(xpath="//*[@placeholder='Email Address']")
	public WebElement emailId;

	//@Name("confEmailId  Input Box")
	@FindBy(xpath="//*[@placeholder='Confirm Email Address']")
	public WebElement confEmailId;

	//@Name("streetAddress Input Box")

	//@Name("state Input Box")
	@FindBy(xpath="//select[contains(@class,'custom-select-box')]")
	public WebElement state;
	
	//For CCT verify Purchase Test Scenarios done by Preetham
	public WebElement State (String statename) throws InterruptedException {
		String linkXpath="//option[contains(text(),'"+statename+"')]";
		return driver.findElement(By.xpath(linkXpath));
	}
	//Preetham for SSC ST Scenario
	@FindBy(xpath="//div[@class='sliderimg-wrapper']/img[@src='https://assets-adssv2.tribqa.com/assets/images/adTemplates/GENDISPLAY29/sample.jpg']")
	public WebElement template;
	//Preetham K P
	@FindBy(xpath="//div[@class='sliderimg-wrapper']/img[@src='https://assets-adssv2.tribqa.com/assets/images/adTemplates/576/sample.jpg']")
	public WebElement templatetwo;
	//Preethsm K P
	@FindBy(xpath="//*[@placeholder='Body Copy 1']")
	public WebElement Bodycopy1;
	
	//Preetham K P
	@FindBy(xpath="//*[@placeholder='Address']")
	public WebElement Addresssscst;
	
	//preetham K P
	@FindBy(xpath="//*[@placeholder='Company Name']")
	public WebElement CompanyNameSSCST;
	
	//Preetham K P
	@FindBy(xpath="//*[@placeholder='Web Address'] ")
	public WebElement WebAddressSSCST;
	//Preetham K P
	@FindBy(xpath="//*[@value='Submit Form']")
	public WebElement SaveandContinueButton;
	//Preetham K P
	@FindBy(xpath="//strong[contains(text(),'APPLY')]")
	public WebElement Applybutton;

	@FindBy(xpath = "//*[contains(text(),'I have proofread my work')]")
	public WebElement checkProofRead;

	@FindBy(xpath = "//*[@auto-test='purchase']")
	public WebElement bookAdButton;

	@FindBy(xpath = "//span[contains(@class,'ng-star-inserted')]/a")
	public WebElement orderId;

	@FindBy(xpath = "(//*[contains(text(),'Dates')]/following-sibling::div)[1]")
	public List<WebElement> orderDate;

	@FindBy(xpath = "//div[contains(@class,'col-lg-1')][contains(text(),'$')]")
	public WebElement totalPrice;
	
	@FindBy(xpath = "//div[contains(@class,'col-lg-1')][contains(text(),'$')]")
	public List<WebElement> totalPriceList;
	
	@FindBy(xpath = "//*[@placeholder='City']")
	public WebElement City;
	
	@FindBy(xpath = "//*[@placeholder='Type text then preview for final ad format']")
	public WebElement LATObitText;
	
	@FindBy(xpath = "//*[contains(text(),'Birth Month')]/..")
	public WebElement BirthMonth;

	//@Name("Birth Year - design your Material")
	@FindBy(xpath = "//*[contains(text(),'Birth Year')]/..")
	public WebElement BirthYear;

	//@Name("Birth DaY - design your Material")
	@FindBy(xpath = "//*[contains(text(),'Day of Birth')]/..")
	public WebElement DayOfBirth;

	//@Name("Death Month - design your Material")
	@FindBy(xpath = "//*[contains(text(),'Death Month')]/..")
	public WebElement DeathMonth;

	//@Name("Death Year - design your Material")
	@FindBy(xpath = "//*[contains(text(),'Death Year')]/..")
	public WebElement DeathYear;

	//@Name("Death Day - design your Material")
	@FindBy(xpath = "//*[contains(text(),'Day of Death')]/..")
	public WebElement DayOfDeath;
	
	@FindBy(xpath="//*[@auto-test='uploadLogo']")
	public WebElement LogoUpload;
	
	@FindBy(xpath = "//div[contains(@class,'ql-editor')]")
	public WebElement CTCObitText;
	
	@FindBy(xpath = "//*[@placeholder='Funeral Home Name']")
	public WebElement funeralHomeName;
	
	@FindBy(xpath="//tbody[@class='order-lines ng-scope ng-isolate-scope']")
	public List<WebElement> aditOrderLines;
	
	@FindBy(xpath = "//*[@placeholder='Condolence']")
	public WebElement Condolence;
	
	@FindBy(xpath="//*[@placeholder='Customer Name']")
	public WebElement CustomerName;
	
	@FindBy(xpath = "(//select)[1]")
	public WebElement PetType;

	//@Name("Breed Field")
	@FindBy(xpath = "(//select)[2]")
	public WebElement Breed;

	//@Name("TextOnlineEnhancement Field")
	//@Name("HeadLine Field")
	@FindBy(xpath = "//*[@placeholder='Ad Text']")
	public WebElement AdText;
	
	@FindBy(xpath="//*[@placeholder='Price - No Commas, $, decimals, or letters']")
	public WebElement Price;
	
	@FindBy(xpath="//*[@placeholder='Account Email (for online account)']")
	public WebElement EnhancementEmail;
	
	
	@FindBy(xpath="//*[text()='7 Day Featured Ad $6.00']")
	public WebElement adHighlight;
	
	@FindBy(xpath="//*[contains(text(),'Photos - online Free')]")
	public WebElement adUpsells;
	
	@FindBy(xpath = "//input[@title='Upload Image']")
	public List<WebElement> uploadUpsells;
	
	@FindBy(xpath = "((//*[contains(text(),'Dates')]/following-sibling::div)//*[contains(text(),'')])[2]")
	public List<WebElement> orderDateOnline1;
	
	@FindBy(xpath="//*[contains(text(),'Select State')]/..")
	public WebElement State;
	
	@FindBy(xpath="//*[@placeholder='ZipCode']")
	public WebElement ZipCode;
	
	//@FindBy(xpath = "(//*[text()='NEXT PRODUCT'])/..")
	@FindBy(xpath="//u[text()='NEXT PRODUCT']")
	public List<WebElement> nextAdConfigLink;
	
	@FindBy(xpath = "(//*[@auto-test='switchTab0'])[2]")
	public WebElement scheduleTab2;
	
	@FindBy(xpath = "(//*[@auto-test='switchTab1'])[2]")
	public WebElement layoutTab2;
	
	
	@FindBy(xpath = "((//*[contains(text(),'Dates')]/following-sibling::div)//*[contains(text(),'')])[1]")
	public List<WebElement> orderDateOnline;
	
	@FindBy(xpath = "(//*[contains(text(),'Dates')]/following-sibling::div)[4]")
	public List<WebElement> orderDate1;
	
	@FindBy(xpath="//*[@id='insertionInfo']//input[@ng-model='vm.orderLine.AgencyCommissionAmount']")
	public List<WebElement> orderLineAgencyCommissionAmount;

	@FindBy(xpath="//*[@class='w-full clearfix']//*[@class='pointer']//i")
	public List<WebElement> orderLineExpandButton;

	//@Name("Adit2 Order Lines SalesPrice")
	@FindBy(xpath="//*[@id='insertionInfo']//input[@ng-model='vm.orderLine.SalesPrice']")
	public List<WebElement> orderLineSalesPrice;

	//@Name("Adit2 Order Lines AddOn")
	@FindBy(xpath="//*[@id='insertionInfo']//input[@ng-model='vm.orderLine.AddOnAmount']")
	public List<WebElement> orderLineAddOn;

	//@Name("Adit2 Order Lines Charges")
	@FindBy(xpath="//*[@id='insertionInfo']//input[@ng-model='vm.orderLine.ChargeAmount']")
	public List<WebElement> orderLineCharges;
	
	//@Name("Adit2 Order Lines AgencyCommission")
	@FindBy(xpath="//*[@id='insertionInfo']//input[@ng-model='vm.orderLine.AgencyCommission']")
	public List<WebElement> orderLineAgencyCommission;
	
	@FindBy(xpath="//*[@auto-test='selectLogo']")
	public WebElement selectLogo;
	
	@FindBy(xpath="//*[@auto-test='showUploadLogo']")
	public WebElement showUploadLogo;
	
	@FindBy (css = "input#userName")
	public WebElement aditEmailBox;
	
	@FindBy(xpath="//*[@auto-test='accept']")
	public WebElement saveImageButton;

	@FindBy (css = "input#password")
	public WebElement aditPasswordBox;

	@FindBy (xpath = "//span[text()='Log in']/..")
	public WebElement loginButton;

	@FindBy (xpath = "//span[text()='Ad Orders']")
	public WebElement dashboardPageHeader;

	
	@FindBy(xpath="//tbody[@class='order-lines ng-scope ng-isolate-scope']//td[5]//*[@class='ng-binding ng-scope']")
	public List<WebElement> aditOrderLinesPubDates;

	@FindBy(xpath="//*[text()='Total Amount:']/following-sibling::span")
	public WebElement aditOrderPrice;
	
	@FindBy(xpath="//*[@auto-test='renewOrder']")
	public WebElement renewLink;
	
	@FindBy(xpath="//*[@auto-test='editOrderItem']")
	public WebElement editLink;

	@FindBy(xpath="//*[text()='New']")
	public WebElement newSection;
	
	@FindBy (xpath = "//*[contains(@class,'label badge-xs')]")
	public WebElement orderStatus;

	@FindBy (xpath = "//a[contains(@ng-click,'reject()')]")
	public WebElement rejectOrderBtn;
	
	@FindBy (xpath = "//button[text()='Reject']")
	public WebElement rejectOrderConfBtn;

	@FindBy (xpath = "//a[contains(@ng-click,'approve()')]")
	public WebElement approveOrderBtn;
	
	@FindBy (xpath = "//button[text()='Approve']")
	public WebElement approveOrderConfBtn;
	
	@FindBy(xpath="//*[@auto-test='submitOrder']")
	public WebElement submitButton;
	
	@FindBy(id="identifierId")
	public WebElement emailField;
	
	@FindBy(xpath="//*[@id='identifierNext']//span[text()=\"Next\"]")
	public WebElement nextButtonId;
	
	//@Name("Next Button Password")
	@FindBy(xpath="//*[@id='passwordNext']//span[text()=\"Next\"]")
	public WebElement nextButtonPassword;
	
	@FindBy(xpath="//*[@id='password']//input")
	public WebElement passwordField;
	
	@FindBy(xpath="//*[text()='Inbox']")
	public WebElement inbox;
	
	@FindBy(xpath="(//*[contains(text(),'Reset Password')])[2]")
	public WebElement resetPasswordEmail1;
	
	@FindBy(xpath="//a[text()='click here']")
	public WebElement resetPasswordLink;
	
	//@Name("Gmail InboxContentRestPasswordEmail")
	@FindBy(xpath="(//*[contains(text(),'Reset Password')])[4]")
	public WebElement resetPasswordEmail2;
	
	@FindBy(xpath="//a[text()='I forgot my password']")
	public WebElement forgetPasswordLink;
	
	@FindBy(xpath="//*[text()='Reset your password']/..//input[@placeholder='Email']")
	public WebElement emailBoxForgotPassword;
	
	@FindBy(xpath="//button[text()='Send Link']")
	public WebElement sendLinkButton;
	
	@FindBy(xpath = "//span[text()='Product']")
	public WebElement prodAdminLink;
	
	@FindBy(xpath="//*[text()='A link to reset your password has been sent']")
	public WebElement resetLinkSentConfirmation;
	
	@FindBy(xpath="//*[text()='Continue']")
	public WebElement closeRestSentConfirmationPopupbutton;

	public WebElement adit2orderDetail;

	@FindBy(css="button#btnSearch")
	public WebElement searchBtnInOrdersPg;

	@FindBy(xpath="//*[@auto-test='targetSelected']")
	public List<WebElement> zones;
	
	@FindBy(xpath = "//*[@auto-test='accept']")
	public WebElement saveImage;
	
	@FindBy(xpath="//input[@placeholder='Click Through Url']")
	public WebElement clickThroughURLForAd;
	
	@FindBy(xpath = "//*[@auto-test='saveAndPurchase']")
	public WebElement saveAndCompletePurchaseLaterLink;
	
	//@FindBy(xpath = "//a[@data-reg-username]")
	@FindBy(xpath="//a[@aria-label='Account Navigation']")
	public WebElement usernameLink;
	
	//@Name("Signout Link")
	//@FindBy(xpath = "//a[@aria-label='Sign Out']")
	@FindBy(xpath= "//a[@aria-label='Sign Out']")
	public WebElement signOutLink;
	
	//@FindBy(xpath="//*[@auto-test='editAccount']")
	@FindBy(xpath="//button[@auto-test='editAccount']")
	public WebElement editAccountInfoLink;
	
	@FindBy(xpath="//*[@auto-test='saveAccountInformation']")
	public WebElement saveButton;
	
	@FindBy(xpath="//*[@placeholder='First Name']")
	public WebElement firstName;

	//@Name("")
	@FindBy(xpath="//*[@placeholder='Last Name']")
	public WebElement lastName;

	//@Name("")
	@FindBy(xpath="//*[@placeholder='Phone Number']")
	public WebElement phoneNumber;

	//@Name("")
	@FindBy(xpath="//*[@placeholder='Street Address']")
	public WebElement streetAddress;

	//@Name("")
	@FindBy(xpath="//*[@placeholder='City']")
	public WebElement city;
	
	@FindBy(xpath = "//*[@placeholder='Headline']")
	public WebElement HeadLine;

	@FindBy(xpath="(//*[@auto-test='openDesignMaterial'])[1]")
	public WebElement Design_Template;
	
	@FindBy(xpath = "//*[@placeholder='WebAddress']")
	public WebElement WebAddress;
	
	//@Name("BodyCopy2 Field")
	@FindBy(xpath = "//*[@placeholder='BodyCopy2']")
	public WebElement BodyCopy2;

	//@Name("BodyCopy Field")
	@FindBy(xpath = "//*[@placeholder='BodyCopy']")
	public WebElement BodyCopy;

	//@Name("CompanyName Field")
	@FindBy(xpath = "//*[@placeholder='CompanyName']")
	public WebElement CompanyName;

	//@Name("Address Field")
	@FindBy(xpath = "//*[contains(@placeholder,'Address')]")
	public WebElement Address;

	//@Name("PhoneNumber Field")
	@FindBy(xpath = "//*[@placeholder='PhoneNumber']")
	public WebElement PhoneNumberInDesignYourMaterialPg;
	
	@FindBy(xpath = "//*[@placeholder='Phone Number']")
	public WebElement PhoneNumber;
	
	@FindBy(xpath="//*[@placeholder='Zip Code']")
	public WebElement zipCode;

	@FindBy(xpath = "//mat-error")
	public WebElement errorMessage;
	
	@FindBy(xpath="//*[@auto-test='editChangePassword']")
	public WebElement changePasswordButton;
	
	@FindBy(xpath="//*[@auto-test='changePassword']")
	public WebElement changePasswordSaveButton;
	
	@FindBy(xpath="//*[@placeholder='Old Password']")
	public WebElement OldPasswordField;
	
	@FindBy(xpath="//*[@placeholder='New Password']")
	public WebElement NewPasswordField;
	
	@FindBy(xpath="//*[@placeholder='Confirm Password']")
	public WebElement ConfirmPasswordField;
	
	@FindBy(xpath="//*[contains(text(),'Full Run')]")
	public WebElement zoneFullRun;
	
	@FindBy(how=How.XPATH, using = "//span[@class='mat-button-wrapper'][text()='Save']")
	public WebElement saveBtnInEditWindow;

	@FindBy(xpath="//span[@class='mat-button-wrapper'][text()='Save & Continue Booking']")
	public WebElement SaveAndContinueButton;
	
	@FindBy(css="input.form-control.ng-valid")
	public WebElement couponInputField;
	
	@FindBy(xpath="//button[@type='button']/strong[text()='APPLY']")
	public WebElement couponApplyField;	
	
	@FindBy(xpath="//*[contains(text(),'Use a new credit card')]")
	public WebElement newCC;
	
	@FindBy(xpath="//*[contains(text(),'Credit card information same as')]")
	public WebElement creditCardInformationCheckBox;
	
	@FindBy(id = "PurchaseData_CreditCardType_Id")
	public WebElement typeOfCard;

	//@Name("cardNumber Input Box")
	@FindBy(xpath = "//*[@placeholder='Card Number']")
	public WebElement cardNumber;

	//@Name("expirationMonth Input Box")
	@FindBy(xpath = "//*[@formcontrolname='ExpirationMonth']/option[4]")  //joy
	public WebElement expirationMonth;

	//@Name("expirationYear Input Box")
	@FindBy(xpath = "//*[@formcontrolname='ExpirationYear']/option[5]") //joy
	public WebElement expirationYear;

	//@Name("verificationNumber Input Box")
	@FindBy(xpath = "//*[@placeholder='Verification Number']")
	public WebElement verificationNumber;
	
	@FindBy(xpath="//*[contains(text(),'Discounted')]")
	public WebElement couponApplySuccess;

	@FindBy(xpath = "//p[contains(text(),'$')]")
	public List<WebElement> orderPrice;
	
	@FindBy(xpath="//span[@class='no-wrap ng-binding'][contains(text(),'$')]")
	public List<WebElement> orderPriceList;

	@FindBy (id = "user_info_dropdown")
	public WebElement aditUserName;

	@FindBy (id = "logout")
	public WebElement logoutOptionInAdit;
	
	//@FindBy(xpath = "//*[@auto-test='selectCategoryOption']")
	//public WebElement SelectCategory; Preetham 
	public WebElement SelectCategory (String category) throws InterruptedException {
		String linkXpath="//option[contains(text(),'"+category+"')]";
		return driver.findElement(By.xpath(linkXpath));
	}
	//Preetham 
	public WebElement SelectTown (String townname) throws InterruptedException {
		String linkXpath="//option[contains(text(),'"+townname+"')]";
		return driver.findElement(By.xpath(linkXpath));
	}
	
	@FindBy(xpath = "(//*[contains(text(),'Type of Celebration')]/..//select)")
	public WebElement CelebrationType;
	
	@FindBy(xpath = "//*[text()='Headline']/../../..//textarea")
	public WebElement HeadlineDPR;

	//@Name("DPR Input AdTextHeader")
	@FindBy(xpath = "//*[text()='Ad Text']/../..//textarea")
	public WebElement AdTextHeaderDPR;

	//@Name("DPR Input FirstName")
	
	@FindBy(xpath = "//input[@placeholder='First Name']")
	public WebElement FirstName;

	//@Name("DPR Input LastName")
	@FindBy(xpath = "//input[@placeholder='Last Name']")
	//@FindBy(xpath="//div[@class='mat-input-infix mat-form-field-infix']//input[@placeholder,'Last Name']")
	public WebElement LastName;
	
	@FindBy(xpath = "//*[@auto-test='switchTab2']")
	public WebElement otherInfoTab;
	
	
	
	//@FindBy(xpath="//input[@placeholder='Email required to send Memorial Tree Certificate']") //joy
	@FindBy(xpath="//input[@placeholder='Contact Email']")
	public WebElement contactEmail;
	
	@FindBy(xpath = "//*[@placeholder='Obit Text']")	
	public WebElement ObitText;
	//@FindBy(xpath="//div[contains(@data-placeholder,'html')]")//Preetham K P
	
	@FindBy(xpath = "//div[contains(@class,'ql-editor')]")
	public WebElement ObitText1;
	
	@FindBy(xpath="//input[@placeholder='Contact Name']")
	public WebElement contactName;
	
	@FindBy(how=How.CSS, using = "div.px-1.ng-star-inserted mat-icon.mat-icon.material-icons")
	public WebElement editLinkInDesignTemplatePg;
	
	@FindBy(how=How.XPATH, using = "//div[@class='text-center'][normalize-space()=\"Edit\"]/preceding-sibling::button//mat-icon[@class='mat-icon material-icons']")
	public WebElement editLinkInEditAdMaterialImg;
	
	@FindBy(xpath="//*[@formcontrolname='phoneNumber']")
	public WebElement contactPhoneNumberWithoutPlaceholder;
	
	@FindBy(how=How.CSS, using = "select.custom-select-box.required")
	public WebElement petTypeDropdown;

	@FindBy(xpath="//*[@ng-reflect-name='state']")
	public WebElement StateInEditAccountInfoWindow;

	@FindBy(xpath="//span[contains(@class,'m-n font-thin')][contains(text(),'Order')]")
	public WebElement orderNrHeadingInOrderNrPg;

	@FindBy(how=How.XPATH, using = "//button[@type='submit'][text()=\"Reset Password\"]")
	public WebElement resetPasswordButton;

	@FindBy(how=How.CSS, using = "register.ng-star-inserted")
	public WebElement registerWindow;

	@FindBy(css="input[ng-reflect-placeholder='Business Name']")
	public WebElement businessName;

	@FindBy(how=How.CSS, using = "textarea.mat-input-element")
	public WebElement ObitText2;

	@FindBy(css="mat-icon[aria-label='Close']")
	public List<WebElement> closeIconInRegisterPopupWindowList;
	
	@FindBy(css="mat-icon[aria-label='Close']")
	public WebElement closeIconInRegisterPopupWindow;

	@FindBy(how=How.CSS, using = "a.text-left.text-primary.help-icon")
	public WebElement viewAdCreationGuidelinesInConfigPg;

	@FindBy(css="div.modal-content app-guidelines")
	public WebElement adCreationGuidelinesPopup;

	@FindBy(how=How.CSS, using = "mat-icon.help-icon.mat-icon.material-icons")
	public WebElement showOrHidePwdIconInRegisterWindow;

	@FindBy(css="div.pl-0.text-right")
	public List<WebElement> totalPriceInReviewOrderPg;

	@FindBy(how=How.CSS, using = "img.img-fluid.preview-img")
	public WebElement imgInUpdateMaterialSection;

	@FindBy(css="img.preview-img#target")
	public WebElement imgInPreviewAdMaterialPopup;

	@FindBy(how=How.CSS, using = "span.ng-star-inserted.m-md-0")
	public List<WebElement> datasUnderMenuOptionInMyDashboard;
	
	@FindBy(how=How.CSS, using = "span[ng-reflect-klass='align-top ml-4 m-md-0']")
	public List<WebElement> datasUnderMenuOptionInMyDashboard1;

	@FindBy(css="div.text-center.mb-2>img.img-fluid")
	public WebElement imgInMaterialPreviewSectionInDesignYourMaterial;

	@FindBy(how=How.CSS, using = "i.fa.fa-caret-right.fa-lg")
	public WebElement expandIconInCurrentTotalDetailsSection;

	@FindBy(css="ngb-popover-window[role='tooltip']")
	public WebElement detailsAvailableAfterExpandingIconInCurrentTotalSection;

	@FindBy(how=How.CSS, using = "button#statusDropDown")
	public WebElement paymentOptionDropdownIcon;

	@FindBy(css="div.col-12.col-sm-6.ng-star-inserted")
	public WebElement billingOptions;

	@FindBy(how=How.CSS, using = "mat-list.mat-list")
	public WebElement creditCardOptions;

	@FindBy(xpath="//div[@class='fab-buttons text-right button-row ng-star-inserted']//mat-icon[@class='mat-icon material-icons'][@role='img'][text()=\"mode_edit\"]")
	public WebElement editIconInOrdersList;

	@FindBy(how=How.CSS, using = "button[color='primary'] mat-icon.mat-icon.material-icons")
	public List<WebElement> threeDotIconListFromOrdersList;

	@FindBy(css="div.alert.bg-warning.ng-star-inserted")
	public WebElement yellowColorErrorMsgInBottomOfConfigPg;

	@FindBy(how=How.CSS, using = "span.padder.ng-binding")
	public List<WebElement> nrOfDollarAmtsShownInPaymentsTab;

	@FindBy(css="i.fa.fa-ellipsis-h")
	public WebElement threeDotIconInOrderPg;

	@FindBy(how=How.CSS, using = "input[ng-reflect-placeholder='Create Your Username']")
	public WebElement createYourUsernameInRegistrationWindow;

	@FindBy(css="div.ql-editor p span")
	public WebElement textAreaParagraphFld;

	@FindBy(how=How.CSS, using = "div.ql-editor p")
	public List<WebElement> textAreaParagrphFldWithChars;

	@FindBy(css="button.ql-clean")
	public WebElement cleanBtnAboveTextAreaParagraphFld;

	@FindBy(how=How.CSS, using = "div.modal-footer.border-0.ng-star-inserted")
	public WebElement confirmTextFormattingPopUp;

	@FindBy(xpath="//div[@class='ql-editor']/p[text()]")
	public List<WebElement> textsPresentNotHighlightedInAnyFormat;

	@FindBy(how=How.CSS, using = "img.material-preview")
	public WebElement materialPreviewImgInInserttionsTbl;

	@FindBy(css="div.material-carousel-modal.ng-scope")
	public List<WebElement> materialReviewPopUpList;
	
	@FindBy(css="div.material-carousel-modal.ng-scope")
	public WebElement materialReviewPopUp;

	@FindBy(how=How.CSS, using = "i.fa.fa-share")
	public WebElement openInNewWindowBtnInMaterialReviewPopUp;

	@FindBy(css="a.float-right>u")
	public WebElement editLinkInMaterialOrderId;

	@FindBy(how=How.CSS, using = "img.imgWithinParent.preview-img.img-fluid")
	public List<WebElement> materialPreviewImgAfterClickingOnEditLinkList;
	
	@FindBy(how=How.CSS, using = "img.imgWithinParent.preview-img.img-fluid")
	public WebElement materialPreviewImgAfterClickingOnEditLink;
	
	@FindBy(xpath = "//a[text()='Auto Insertion Management']")
	public WebElement AutoInsertionManagementTab;

	@FindBy(how=How.XPATH, using = "//div[contains(@class,'text-danger ng-star-inserted')][contains(text(),\"Coupon is not applicable or has expired\")]")
	public WebElement couponExpiredOption;

	@FindBy(css="div.customer-address-modal.ng-scope")
	public WebElement insertionPopup;
	
	@FindBy(how=How.CSS, using = "select#Type_ID")
	public WebElement typeInUpdatePopup;

	@FindBy(css="button#btnOkTemplate")
	public WebElement okBtnInUpdateInsertionPopup;

	@FindBy(how=How.CSS, using = "a.btn-primary")
	public WebElement saveBtnInBUPgInAdit;

	@FindBy(css="a[ng-click=\"vm.approve()\"]")
	public WebElement approveBtnInAdit;
	
	//Partha
	@FindBy(xpath="//i[@class='fa fa-ellipsis-h']")
	public WebElement moreActionsinAditOrder;
	
	@FindBy(xpath="//li[@role='menuitem']//a[@class='ng-scope'][contains(text(),'Kill')]")
	public WebElement KillLinkInAditOrder;
	
	@FindBy (xpath = "//div[@class='modal-footer ng-scope']/button[text()='Kill']")
	  public WebElement killOrderButtonOnConformation;
	
	@FindBy (xpath = "//span[contains(@class,'label badge-xs order-status')]")
	  public WebElement OrderStatusinAdit;
	//Relative product Partha
	@FindBy (xpath = "//a[@class='auto']//span[contains(text(),'General Settings')]")
	  public WebElement generalSettingInAdit;
	
	@FindBy (xpath = "//li[@ui-sref-active='active']//a[@class='auto']//span[contains(text(),'General')]")
	  public WebElement generalLinkInAdit;
	
	@FindBy (xpath = "//span[contains(text(),'Relative Products Group')]")
	  public WebElement relativeProductGroupLinkInAdit;
	
	@FindBy (xpath = "//select[@id='BusinessUnitId']")
	  public WebElement BusinessUnitDropdownInAdit;
	
	@FindBy (xpath = "//select[@id='productTypeId']")
	  public WebElement ProductTypeDropdowninRelativeProductsGroupPage;
	
	@FindBy (xpath = "//div[@class='col-md-12']//product-toggle[contains(@class,'ng-star-inserted')]//div[@class='row prev-next-warpper']//div[@class='col-md-4 d-none d-md-block text-center']")
	  public WebElement countHeaderInmultipleProduct;
	
	@FindBy (xpath = "//span[contains(text(),'CONTINUE')]")
	  public WebElement ContinueButtonOnDeleteproduct;
	
	@FindBy (xpath = "//div[@class='col-md-12']//div[@class='row prev-next-warpper']//a[@class='next help-icon ng-star-inserted']//u[contains(text(),'NEXT PRODUCT')]")
	  public WebElement NextProductButton;
	
	@FindBy (xpath = "//u[contains(text(),'PREVIOUS PRODUCT')]")
	  public WebElement PreviousProductButton;
	
	@FindBy (xpath = "//a[contains(@class,'navbar-brand d-none d-lg-block')]//img[@id='logo']")
	  public WebElement ViewHomeLogo;
	
	@FindBy (xpath = "//div[contains(text(),'Progress Summary')]")
	  public WebElement ProgressSummary;
	
	@FindBy (xpath = "(//span[contains(text(),'Product Type')][contains(@id,'header-text')])[1]")
	  public WebElement ProductTypeColumn;
	
	@FindBy (xpath = "//input[@id='mat-input-0']")
	  public WebElement ClickThroughURL;
	
	
	
	public WebElement adminPgLinkITopRight(String adminPgLink) {
		return driver.findElement(By.xpath("//div[@data-reg-role='base']/a[@aria-label=\""+adminPgLink+"\"]"));
	}


	public WebElement inputFieldInSignwindow(String inputField) {
		return driver.findElement(By.cssSelector("div.reg-form input[type='"+inputField+"']"));
	}
	
	public List<WebElement> inputFieldInSignwindowList(String inputField) {
		return driver.findElements(By.cssSelector("div.reg-form input[type='"+inputField+"']"));
	}


	public WebElement btnInLoginWindow(String btnInLoginWindow) {
		//Partha
		//Assert.assertEquals(1, driver.findElements(By.xpath("//div[@class='reg-form']/*[contains(@class,'reg-')][text()=\""+btnInLoginWindow+"\"]")).size());
		return driver.findElement(By.xpath("//div[@class='reg-form']/*[contains(@class,'reg-')][text()=\""+btnInLoginWindow+"\"]"));
	}


	public WebElement linkSectionInAditLinkList(String aditLinkSection) {
		return driver.findElement(By.xpath("//ul[@class='nav ng-scope']/li/a[normalize-space()=\""+aditLinkSection+"\"]"));
	}


	public WebElement orderNumberLinkInAditTable(String orderId2) {
		return driver.findElement(By.xpath("//span[@class='ng-scope']/a[text()=\""+orderId2+"\"]"));
	}


	public WebElement inputFieldInAditOrdersPg(String orderNrField) {
		return driver.findElement(By.xpath("//label[@class='control-label'][text()='"+orderNrField+"\']/following-sibling::input"));
	}


	public WebElement myDashboardBtn(String myDashboardBtn) {
		return driver.findElement(By.xpath("//button[contains(@class,'text-white')]/span[@class='mat-button-wrapper'][normalize-space()=\""+myDashboardBtn+"\"]"));
	}


	public WebElement creditCardCheckboxToCheck(String ccType) {
		return driver.findElement(By.xpath("//div[@class='mat-radio-container']/div[@class='mat-radio-inner-circle'][./../following-sibling::div[@class='mat-radio-label-content']//div[normalize-space()='"+ccType+"']]"));
	}


	public WebElement headingBelowReviewOrderSectionHeading(String btnName) {
		return driver.findElement(By.xpath("//div[contains(@class,'justify-content-end')]//button[@ng-reflect-color='primary']/span[@class='mat-button-wrapper'][text()=\""+btnName+"\"]"));
	}


	public WebElement inputFieldInRegisterWindow(String inputPlaceholder) {
		return driver.findElement(By.cssSelector("input[ng-reflect-placeholder=\""+inputPlaceholder+"\"]"));
	}


	public WebElement checkboxInRegisterWindow(String checkboxSectionInregisterWindow) {
		return driver.findElement(By.xpath("//label[@class='mat-checkbox-layout']/span[@class='mat-checkbox-label'][contains(normalize-space(),\""+checkboxSectionInregisterWindow+"\")]/preceding-sibling::div[@class='mat-checkbox-inner-container']/input[@type='checkbox']"));
	}


	public WebElement buttonInRegisterWindow(String inputBtnInRegisterWindow) {
		return driver.findElement(By.xpath("//button[contains(@class,'text-uppercase')]/span[@class='mat-button-wrapper'][text()=\""+inputBtnInRegisterWindow+"\"]"));
	}


	public WebElement menuInMydashboardPg(String menuOption) {
		return driver.findElement(By.xpath("//div[@class='mat-tab-label-content'][text()=\""+menuOption+"\"]"));
	}


	public List<WebElement> menuInMydashboardPgList(String menuOption) {
		return driver.findElements(By.xpath("//div[@class='mat-tab-label-content'][text()=\""+menuOption+"\"]"));
	}


	public WebElement errorMsgInRegistrationWindow(String errorMsg) {
		return driver.findElement(By.xpath("//mat-error[contains(@class,'mat-error')][normalize-space()=\""+errorMsg+"\"]"));
	}


	public List<WebElement> btnInForgotPwdPopUpList(String btnName) {
		return driver.findElements(By.xpath("//div[@class='reg-dialog forgot-password']//button[contains(@class,'reg')][text()=\""+btnName+"\"]"));
	}
	
	public WebElement btnInForgotPwdPopUp(String btnName) {
		return driver.findElement(By.xpath("//div[@class='reg-dialog forgot-password']//button[contains(@class,'reg')][text()=\""+btnName+"\"]"));
	}


	public WebElement linkInRegistrationPopupWindow(String linkName) {
		return driver.findElement(By.xpath("//u[contains(@class,'ng-tns')][text()=\""+linkName+"\"]"));
	}


	public WebElement pgHeaderSameAsPgTitle(String pgTitle) {
		return driver.findElement(By.xpath("//div[@class='container p-10']/h1[contains(text(),\""+pgTitle+"\")]"));
	}


	public WebElement btnInUploadPopup(String btnName) {
		return driver.findElement(By.xpath("//div[contains(@class,'modalfooter')]//span[@class='mat-button-wrapper'][text()=\""+btnName+"\"]"));
	}


	public WebElement errorMsgInUploadPopupInDesignMaterialPg(String errorMsg) {
		return driver.findElement(By.xpath("//mat-error[@class='mat-error'][normalize-space()=\""+errorMsg+"\"]"));
	}


	public WebElement btnInConfirmationPg(String btnName) {
		return driver.findElement(By.xpath("//span[contains(@class,'mx-2 text-uppercase')][text()=\""+btnName+"\"]"));
		//return driver.findElement(By.xpath("//span[contains(text(),"+btnName+")")); //joy
	}
	//Preetham K P
	/*public WebElement btnInConfirmationPgofOrderDetails(String buttonName) {
		return driver.findElement(By.xpath("//button[@auto-test='printReceipt']//span[text()=\""+buttonName+"\"]"));
	}*/


	public WebElement inputFieldWithPlaceholder(String inputFld) {
		return driver.findElement(By.cssSelector("input[placeholder='"+inputFld+"'][type='text']"));
	}
	
	public List<WebElement> inputFieldWithPlaceholderList(String inputFld) {
		return driver.findElements(By.cssSelector("input[placeholder='"+inputFld+"'][type='text']"));
	}


	public WebElement btnInUpdateMaterialSection(String btnName) {
		return driver.findElement(By.xpath("//mat-icon[@class='mat-icon material-icons'][text()=\""+btnName+"\"]"));
	}


	public WebElement btnLinkInStartOverPopup(String btnName) {
		return driver.findElement(By.xpath("//div[contains(@class,'modal-footer')]//span[@class='mat-button-wrapper'][text()=\""+btnName+"\"]"));
	}


	public WebElement btnInEditAdMaterialSection(String btnName) {
		return driver.findElement(By.xpath("//div[@class='row pl-md-3 mt-3']//span[@class='mat-button-wrapper'][text()=\""+btnName+"\"]"));
	}


	public WebElement linkToClickFromUserProfileOptions(String linkToClick) {
		return driver.findElement(By.xpath("//ul[@data-reg-role='minimodal']//a[contains(@data-reg-handler,'Handler')][text()=\""+linkToClick+"\"]"));
	}


	public WebElement iconInMaterialPreviewSectionInDesignYourMaterialPg(String iconName) {
		return driver.findElement(By.xpath("//div[contains(@class,'d-flex')]//mat-icon[@role='img'][text()=\""+iconName+"\"]"));
	}


	public WebElement paymentOptionToChoose(String paymentOption) {
		return driver.findElement(By.xpath("//button[@class='dropdown-item'][normalize-space()=\""+paymentOption+"\"]"));
	}


	public WebElement creditCardOptionToSelect(String creditCardOption) {
		return driver.findElement(By.xpath("//div[@class='mat-radio-label-content'][.//div[contains(@class,'mb')][normalize-space()='"+creditCardOption+"']]/preceding-sibling::div[@class='mat-radio-container']"));
	}


	public WebElement errorMsgInHomePg(String errorMsg) {
		return driver.findElement(By.xpath("//div[@class='my-3'][contains(normalize-space(),'"+errorMsg+"')]"));
	}
	
	public List<WebElement> errorMsgInHomePgList(String errorMsg) {
		return driver.findElements(By.xpath("//div[@class='my-3'][contains(normalize-space(),'"+errorMsg+"')]"));
	}


	public WebElement tabInOrderTbl(String tabName) {
		return driver.findElement(By.xpath("//div[@class='panel-heading no-padder']//ul[@class='nav nav-tabs']//span[contains(@class,'h3')][text()=\""+tabName+"\"]"));
	}


	public WebElement optionToSelectAfterClickingOnTheThreeDotIconInTheOrderPg(String optionName) {
		return driver.findElement(By.xpath("//button[@title='More actions']/following-sibling::ul[@class='dropdown-menu pull-right']/li[@role='menuitem']/a[text()=\""+optionName+"\"]"));
	}


	public WebElement optionToClickFromPopupInOrderPg(String optionName) {
		return driver.findElement(By.xpath("//div[@class='modal-footer ng-scope']/button[text()=\""+optionName+"\"]"));
	}


	public WebElement selectedCreditCard(WebElement creditCardOptionToSelect) {
		return creditCardOptionToSelect.findElement(By.xpath("./following-sibling::input[@type='radio']"));
	}


	public WebElement fieldInNewTab(String fieldName) {
//		return driver.findElement(By.cssSelector("div#user label"));
		return driver.findElement(By.xpath("//a[text()=\""+fieldName+"\"]"));
	}


	public WebElement sectionAboveTextAreaParagraph(String sectionType) {
		String element = "button.ql-" + sectionType;
		return driver.findElement(By.cssSelector(element));
	}


	public WebElement boldTextInTextArea(WebElement webElement) {
		return webElement.findElement(By.xpath("./strong"));
	}


	public WebElement italicTextInTextArea(WebElement webElement) {
		return webElement.findElement(By.xpath("./em"));
	}


	public WebElement underlinedTextInTextArea(WebElement webElement) {
		return webElement.findElement(By.xpath("./u"));
	}


	public WebElement optionFromConfirmTextFormattingPopUp(WebElement confirmTextFormattingPopUp, String optionName) {
		return confirmTextFormattingPopUp.findElement(By.xpath("./button[@auto-test]/span[@class='mat-button-wrapper'][text()=\""+optionName+"\"]"));
	}


	public WebElement expandIconBesideMentionedFieldInDigitalSettingsPg(String fieldName) {
		return driver.findElement(By.xpath("//span[@class='mat-content'][normalize-space()=\""+fieldName+"\"]/following-sibling::span[@style]"));
	}


	public WebElement dropdownFldInMentionedSectionInDigitalSettingsPg(String sectionName) {
		return driver.findElement(By.xpath("//mat-expansion-panel-header[./span[@class='mat-content'][normalize-space()=\""+sectionName+"\"]]/following-sibling::div[contains(@id,'cdk-accordion-child')]//select[contains(@class,'custom-select-box border')]"));
	}
	
	public List<WebElement> dropdownFldInMentionedSectionInDigitalSettingsPgList(String sectionName) {
		return driver.findElements(By.xpath("//mat-expansion-panel-header[./span[@class='mat-content'][normalize-space()=\""+sectionName+"\"]]/following-sibling::div[contains(@id,'cdk-accordion-child')]//select[contains(@class,'custom-select-box border')]"));
	}


	public WebElement checkboxesInDigitalSettingsPg(String checkbox) {
		checkbox = "\u00a0" + checkbox;
		return driver.findElement(By.xpath("//mat-checkbox[contains(@class,'mat-checkbox mat-accent')]//span[@class='mat-checkbox-label'][normalize-space()=\""+checkbox+"\"]/preceding-sibling::div[@class='mat-checkbox-inner-container']/input"));
	}


	public WebElement textAreaBesideMentionedCheckbox(String checkbox) {
		return driver.findElement(By.xpath("//div[@class='col-md-4 col-3'][contains(normalize-space(),\""+checkbox+"\")]/following-sibling::div[@class='col-md-8 col-9']/textarea"));
	}


	public WebElement btnInDigitalSettingsPg(String btnName) {
		return driver.findElement(By.xpath("//div[@class='row mt-4']//button[contains(@class,'float-right')]/span[@class='mat-button-wrapper'][text()=\""+btnName+"\"]"));
	}


	public WebElement btnInConfirmationPopupInDigitalSettingsPg(String btnName) {
		return driver.findElement(By.xpath("//button[@class='btn mat-button']/span[@class='mat-button-wrapper'][text()=\""+btnName+"\"]"));
	}

	public List<WebElement> btnInConfirmationPopupInDigitalSettingsPgList(String btnName) {
		return driver.findElements(By.xpath("//button[@class='btn mat-button']/span[@class='mat-button-wrapper'][text()=\""+btnName+"\"]"));
	}

	public WebElement symbolInTemplateDesignPg(String symbol) {
		return driver.findElement(By.cssSelector("a.symbols[title='"+symbol+"']"));
	}
	
	public List<WebElement> symbolInTemplateDesignPgList(String symbol) {
		return driver.findElements(By.cssSelector("a.symbols[title='"+symbol+"']"));
	}


	public WebElement btnInEditOrderPgInNewWindow(String btnName) {
		return driver.findElement(By.xpath("//span[@class='mat-button-wrapper'][normalize-space()=\""+btnName+"\"]"));
	}


	public WebElement buNameToClickInAdit(String buName) {
		return driver.findElement(By.xpath("//span[text()='"+buName+"']"));
	}


	public WebElement orderStatusInAdit(String status) {
		return driver.findElement(By.xpath("//span[@class='label badge-xs order-status label-success'][normalize-space()=\""+status+"\"]"));
	}
	
	public WebElement tblDataInAutoInsertionMgmtTbl(String tblHeading1, String tblVal1, String tblHeading2,
			String tblVal2) {
		return driver.findElement(By.xpath("//div[@ng-style='colContainer.getViewportStyle()']//div[@class='ui-grid-row ng-scope'][.//div[contains(@class,'ng-scope ui-grid-disable-selection')][count(//div[@class='ui-grid-header-cell-wrapper']//div[contains(@class,'ui-grid-coluiGrid')][.//span[@class='ui-grid-header-cell-label ng-binding'][text()=\""+tblHeading2+"\"]]/preceding-sibling::div[contains(@class,'ui-grid-coluiGrid')])+1][./div[@class='ui-grid-cell-contents ng-binding ng-scope'][text()=\""+tblVal2+"\"]]]//div[contains(@class,'ng-scope ui-grid-disable-selection')][count(//div[@class='ui-grid-header-cell-wrapper']//div[contains(@class,'ui-grid-coluiGrid')][.//span[@class='ui-grid-header-cell-label ng-binding'][text()=\""+tblHeading1+"\"]]/preceding-sibling::div[contains(@class,'ui-grid-coluiGrid')])+1]/div[@class='ui-grid-cell-contents ng-binding ng-scope'][text()=\""+tblVal1+"\"]"));
	}
	
	public List<WebElement> tblDataInAutoInsertionMgmtTblList(String tblHeading1, String tblVal1, String tblHeading2,
			String tblVal2) {
		return driver.findElements(By.xpath("//div[@ng-style='colContainer.getViewportStyle()']//div[@class='ui-grid-row ng-scope'][.//div[contains(@class,'ng-scope ui-grid-disable-selection')][count(//div[@class='ui-grid-header-cell-wrapper']//div[contains(@class,'ui-grid-coluiGrid')][.//span[@class='ui-grid-header-cell-label ng-binding'][text()=\""+tblHeading2+"\"]]/preceding-sibling::div[contains(@class,'ui-grid-coluiGrid')])+1][./div[@class='ui-grid-cell-contents ng-binding ng-scope'][text()=\""+tblVal2+"\"]]]//div[contains(@class,'ng-scope ui-grid-disable-selection')][count(//div[@class='ui-grid-header-cell-wrapper']//div[contains(@class,'ui-grid-coluiGrid')][.//span[@class='ui-grid-header-cell-label ng-binding'][text()=\""+tblHeading1+"\"]]/preceding-sibling::div[contains(@class,'ui-grid-coluiGrid')])+1]/div[@class='ui-grid-cell-contents ng-binding ng-scope'][text()=\""+tblVal1+"\"]"));
	}
	
	public WebElement editLinkforRelativeProductGroup(String RelativeproductGroupName) {
		return driver.findElement(By.xpath("//div[contains(text(),'"+RelativeproductGroupName+"')]//following::button[1]/i[1]"));
	}
	
	public WebElement AddRemovebuutonLinkForRelativeProduct(int no) {
		return driver.findElement(By.xpath("//ngu-tile["+no+"]/div[1]/div[1]/div[1]/div[1]//button//span"));
	}
	
	public WebElement scheduleTabInmultipleProduct(int no) {
		return driver.findElement(By.xpath("(//*[@auto-test='switchTab0'])["+no+"]"));
	}
	public WebElement layoutTabInmultipleProduct(int no) {
		return driver.findElement(By.xpath("(//*[@auto-test='switchTab1'])["+no+"]"));
	}
	
	

}
