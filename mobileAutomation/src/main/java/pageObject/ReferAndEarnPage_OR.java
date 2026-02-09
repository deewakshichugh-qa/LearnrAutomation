package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ReferAndEarnPage_OR {
	
	@AndroidFindBy(accessibility = "Invite your friends")
	private MobileElement inviteYourFrndHeader;

	public MobileElement getInviteYourFrndHeader() {
		return inviteYourFrndHeader;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Invite your friends']/parent::android.view.View/preceding-sibling::android.view.View/android.widget.ImageView[1]")
	private MobileElement referAndEarnScreenBackBtn;
	
	public MobileElement getReferAndEarnScreenBackBtn() {
		return referAndEarnScreenBackBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Invite your friends']/parent::android.view.View/preceding-sibling::android.view.View/android.widget.ImageView[2]")
	private MobileElement helpBtn;

	public MobileElement getHelpBtn() {
		return helpBtn;
	}

	@AndroidFindBy(accessibility = "My Rewards")
	private MobileElement myRewardsLink;

	public MobileElement getMyRewardsLink() {
		return myRewardsLink;
	}
	
	@AndroidFindBy(accessibility = "Refer a course\nTab 1 of 2")
	private MobileElement referCourseTab;

	public MobileElement getReferCourseTab() {
		return referCourseTab;
	}
	
	@AndroidFindBy(accessibility = "Share app\nTab 2 of 2")
	private MobileElement shareAppTab;

	public MobileElement getShareAppTab() {
		return shareAppTab;
	}
	
	
	/*-----------------------------------------My Reward Section-----------------------------------*/
	
	@AndroidFindBy(accessibility = "Your Rewards")
	private MobileElement yourRewardScreenTitle;

	public MobileElement getYourRewardScreenTitle() {
		return yourRewardScreenTitle;
	}
	
	@AndroidFindBy(accessibility = "Withdraw")
	private MobileElement withdrawBtn;

	public MobileElement getWithdrawBtn() {
		return withdrawBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Your Earnings']/preceding-sibling::android.view.View")
	private MobileElement earningAmountText;

	public MobileElement getEarningAmountText() {
		return earningAmountText;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Install referrals')]")
	private MobileElement successfullyInstallHeader;

	public MobileElement getSuccessfullyInstallHeader() {
		return successfullyInstallHeader;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Purchase referrals')]")
	private MobileElement successfullyPurchasedHeader;

	public MobileElement getSuccessfullyPurchasedHeader() {
		return successfullyPurchasedHeader;
	}
	
//	@AndroidFindBy(accessibility = "Transaction History")
//	private MobileElement transactionHistoryHeader;
//
//	public MobileElement getTransactionHistoryHeader() {
//		return transactionHistoryHeader;
//	}
//	
//	@AndroidFindBy(accessibility = "Earning Activities")
//	private MobileElement earningActivityHeader;
//
//	public MobileElement getEarningActivityHeader() {
//		return earningActivityHeader;
//	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Your Rewards']/preceding-sibling::android.widget.ImageView")
	private MobileElement rewardScreenBackBtn;

	public MobileElement getRewardScreenBackBtn() {
		return rewardScreenBackBtn;
	}
	
	
	/*----------------------Refer a Course Section-----------------------------*/
	
	@AndroidFindBy(accessibility = "Refer our courses & Earn")
	private MobileElement referOurCourseAndEarnTitle;

	public MobileElement getReferOurCourseAndEarnTitle() {
		return referOurCourseAndEarnTitle;
	}
	
//	@AndroidFindBy(accessibility = "Amount")
//	private MobileElement amountColumnHeader;
//
//	public MobileElement getAmountColumnHeader() {
//		return amountColumnHeader;
//	}
//	
//	@AndroidFindBy(accessibility = "Referrer Reward")
//	private MobileElement referrerRewardColumnHeader;
//
//	public MobileElement getReferrerRewardColumnHeader() {
//		return referrerRewardColumnHeader;
//	}
//	
//	@AndroidFindBy(accessibility = "Refree Reward")
//	private MobileElement refreeRewardColumnHeader;
//
//	public MobileElement getRefreeRewardColumnHeader() {
//		return refreeRewardColumnHeader;
//	}
//	
//	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Earn More')]")
//	private MobileElement earnMoreLink;
//
//	public MobileElement getEarnMoreLink() {
//		return earnMoreLink;
//	}
	
	@AndroidFindBy(accessibility = "Leaderboard")
	private MobileElement leaderBoardHeader;

	public MobileElement getLeaderBoardHeader() {
		return leaderBoardHeader;
	}
	
	@AndroidFindBy(accessibility = "Monthly\nAnnually")
	private MobileElement monthlyAnnuallyBtn;

	public MobileElement getMonthlyAnnuallyBtn() {
		return monthlyAnnuallyBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Monthly')]/following-sibling::android.view.View/descendant::android.view.View[@content-desc]")
	private List<MobileElement> leaderBoardUserCell;

	public List<MobileElement> getLeaderBoardUserCell() {
		return leaderBoardUserCell;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Your Rewards\n" +
			"View all your rewards and referrals\"]")
	private MobileElement yourRewardLink;

	public MobileElement getYourRewardLink() {
		return yourRewardLink;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Your referral code')]")
	private MobileElement referalCodeText;

	public MobileElement getReferalCodeText() {
		return referalCodeText;
	}
	
	@AndroidFindBy(accessibility = "Refer now")
	private MobileElement referNowBtn;

	public MobileElement getReferNowBtn() {
		return referNowBtn;
	}
	
	@AndroidFindBy(accessibility = "Refer via")
	private MobileElement referShareScreenHeader;
	
	public MobileElement getReferShareScreenHeader() {
		return referShareScreenHeader;
	}
	
	@AndroidFindBy(accessibility = "Copy to clipboard")
	private MobileElement copyToClipboardLink;

	public MobileElement getCopyToClipboardLink() {
		return copyToClipboardLink;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[not(@content-desc)]")
	private MobileElement referShareScreenCloseBtn;

	public MobileElement getReferShareScreenCloseBtn() {
		return referShareScreenCloseBtn;
	}
	
	@AndroidFindBy(accessibility = "Copied")
	private MobileElement copyToastMessage;

	public MobileElement getCopyToastMessage() {
		return copyToastMessage;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Refer now']/preceding-sibling::android.widget.ImageView")
	private MobileElement referalCodeCopyBtn;

	public MobileElement getReferalCodeCopyBtn() {
		return referalCodeCopyBtn;
	}
	
	/*----------------------------------------ShareApp------------------------------------*/
	
	@AndroidFindBy(accessibility = "Signup Referrals")
	private MobileElement signupReferralHeader;

	public MobileElement getSignupReferralHeader() {
		return signupReferralHeader;
	}
	
	@AndroidFindBy(accessibility = "Frequently asked questions")
	private MobileElement frequentlyAskedQuestionHeader;

	public MobileElement getFrequentlyAskedQuestionHeader() {
		return frequentlyAskedQuestionHeader;
	}
	
	@AndroidFindBy(accessibility = "EXPAND ALL")
	private MobileElement expandAllBtn;

	public MobileElement getExpandAllBtn() {
		return expandAllBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc='EXPAND ALL']/preceding-sibling::android.view.View[not(@content-desc)]")
	private MobileElement helpScreenCloseBtn;

	public MobileElement getHelpScreenCloseBtn() {
		return helpScreenCloseBtn;
	}
	
	@AndroidFindBy(accessibility = "Exclusive referral deals")
	private MobileElement exclusiveReferralDealsHeader;

	public MobileElement getExclusiveReferralDealsHeader() {
		return exclusiveReferralDealsHeader;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Exclusive referral deals']/preceding-sibling::android.widget.ImageView")
	private MobileElement referalScreenBackBtn;

	public MobileElement getReferalScreenBackBtn() {
		return referalScreenBackBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Referred')]")
	private List<MobileElement> referredList;

	public List<MobileElement> getReferredList() {
		return referredList;
	}
	
	
	@AndroidFindBy(accessibility = "Withdrawl request")
	private MobileElement withdrawlRequestFormTitle;

	public MobileElement getWithdrawlRequestFormTitle() {
		return withdrawlRequestFormTitle;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Withdrawl request']/preceding-sibling::android.widget.ImageView")
	private MobileElement withDrawlRequestFormCloseBtn;

	public MobileElement getWithDrawlRequestFormCloseBtn() {
		return withDrawlRequestFormCloseBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Enter Your UPI ID']/following-sibling::android.widget.EditText")
	private MobileElement upiIdTextField;

	public MobileElement getUpiIdTextField() {
		return upiIdTextField;
	}
	
	@AndroidFindBy(accessibility = "Withdrawal Amount")
	private MobileElement withDrawlAmountHeader;

	public MobileElement getWithDrawlAmountHeader() {
		return withDrawlAmountHeader;
	}
	
	@AndroidFindBy(accessibility = "SUBMIT")
	private MobileElement submitBtn;

	public MobileElement getSubmitBtn() {
		return submitBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Earn More!\n" +
			"Explore exclusive deals and earn \n" +
			"rewards for your successful referrals.\n" +
			"Explore\"]")

	private MobileElement getEarnMoreLink;

	public MobileElement getEarnMoreLink() {
		return getEarnMoreLink;
	}
}
