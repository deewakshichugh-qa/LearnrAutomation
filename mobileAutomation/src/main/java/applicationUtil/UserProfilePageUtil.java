package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.UserProfilePage_OR;
import util.Common_Function;
import util.Common_Function.direction;
import util.Common_Function.key;
import util.ConfigFileReader;

public class UserProfilePageUtil {

	LoginUtil loginUtilObj;
	UserProfilePage_OR userProfilePageObj;
	MyOrdersPageUtil myOrdersPageUtilObj;
	HomePageUtil homePageUtilObj;
	EditProfileUtil editProfileUtilObj;
	AccountSettingUtil accountSettingUtilObj;
	VideoCoursesPageUtil videoCoursesPageUtilObj;
	CommunityPageUtil communityPageUtilObj;
	CommonTestUtil commonTestUtilObj;
	PostingPageUtil postingPageUtilObj;
	CommonStudyMaterialSectionUtil commonStudyMaterialSectionUtilObj;
	CertificatePreviewUtil certificatePreviewUtilObj;
	public List<String> userProfileMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public UserProfilePageUtil(AppiumDriver<MobileElement> driver) {
		userProfilePageObj = new UserProfilePage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), userProfilePageObj);
	}

	public boolean verifyUserProfilePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getSectionCoinBalance(), 10);
			if (!result) {
				userProfileMsgList.add("Unable to verify coin balance section in User profile page");
				return result;

			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBtnLogout(), 10);
			if (!result) {
				userProfileMsgList.add("Unable to verify logout btn in User profile page");
				return result;

			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getSectionSettings(), 10);
			if (!result) {
				userProfileMsgList.add("Unable to verify setting section in User profile page");
				return result;

			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getSectionMyOrder(), 10);
			if (!result) {
				userProfileMsgList.add("Unable to verify My orders in in User profile page");
				return result;

			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTextUserEmail(), 10);
			if (!result) {
				userProfileMsgList.add("Unable to verify user email in User profile page");
				return result;

			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTextUserName(), 10);
			if (!result) {
				userProfileMsgList.add("Unable to verify user name in User profile page");
				return result;

			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getIconEdit(), 10);
			if (!result) {
				userProfileMsgList.add("Unable to verify Edit icon in User Profile Page");
				return result;
			}

		} catch (Exception e) {
			userProfileMsgList.add("verifyUserProfilePage_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickLogoutButton(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(userProfilePageObj.getBtnLogout());

			result = verifyConfirmLogoutPopup(driver);
			if (!result) {
				userProfileMsgList.add("Failed to verify ConfirmLogoutPopup.");
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickLogoutButton Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyConfirmLogoutPopup(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPopupLogout(), 10);
			if (!result) {
				userProfileMsgList.add("Unable to verify confirm Logout Popup");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBtnLogoutPopup(), 10);
			if (!result) {
				userProfileMsgList.add("Unable to verify logout button popup");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBtnCancelPopup(), 10);
			if (!result) {
				userProfileMsgList.add("Unable to verify cancel button popup");
				return result;
			}
		} catch (Exception e) {
			userProfileMsgList.add("verifyConfirmLogoutPopup_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickCancelBtnPopup(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(userProfilePageObj.getBtnCancelPopup());

			result = !cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPopupLogout(), 10);
			if (!result) {
				userProfileMsgList.add("Confirm Logout Popup is visible.");
				return result;
			}

		} catch (Exception e) {
			userProfileMsgList.add("clickCancelBtnPopup_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickLogoutBtnPopup(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(userProfilePageObj.getBtnLogoutPopup());

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.validateWelComePage(driver);
			if (!result) {
				userProfileMsgList.add("Failed to verify SuccessfullyLogout.");
			}

		} catch (Exception e) {
			userProfileMsgList.add("clickLogoutBtnPopup_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnUserPictureAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getUserProfilePicture(), 10);
			if (!result) {
				userProfileMsgList.add("User profile picture is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getUserProfilePicture());
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getUserImageView(), 30);
			if (!result) {
				userProfileMsgList.add("User profile image view is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getImageViewCloseBtn());
			result = !(cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getUserImageView(), 30));
			if (!result) {
				userProfileMsgList.add("User profile image view is visible.");
				return result;
			}

		} catch (Exception e) {
			userProfileMsgList.add("clickOnUserPictureAndValidate_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnCoinbalanceAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getSectionCoinBalance(), 10);
			if (!result) {
				userProfileMsgList.add("Section coin balance is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getSectionCoinBalance());
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCoinHistoryText(), 30);
			if (!result) {
				userProfileMsgList.add("Coin history text is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getBackBtn());
			result = !(cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCoinHistoryText(), 30));
			if (!result) {
				userProfileMsgList.add("Coin history text is visible.");
				return result;
			}

		} catch (Exception e) {
			userProfileMsgList.add("clickOnCoinbalanceAndValidate_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnMyOrderAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getSectionMyOrder(), 10);
			if (!result) {
				userProfileMsgList.add("Section MyOrder is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getSectionMyOrder());
			myOrdersPageUtilObj = new MyOrdersPageUtil(driver);

			result = myOrdersPageUtilObj.verifyMyOrderUIForNewUser(driver);
			if (!result) {
				userProfileMsgList.add("MyOrderUIForNewUser is not visible.");
				return result;
			}

			result = myOrdersPageUtilObj.navigateToUserProfilePage(driver);
			if (!result) {
				userProfileMsgList.add("Not able to navigate user profile page.");
				return result;
			}
		} catch (Exception e) {
			userProfileMsgList.add("clickOnMyOrderAndValidate_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnAccountSettingAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getSectionSettings(), 10);
			if (!result) {
				userProfileMsgList.add("Section Setting is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getSectionSettings());
			accountSettingUtilObj = new AccountSettingUtil(driver);

			result = accountSettingUtilObj.verifyAccountSettingUI(driver);
			if (!result) {
				userProfileMsgList.addAll(accountSettingUtilObj.accountSettingMsgList);
				return result;
			}

			result = accountSettingUtilObj.navigateToUserProfilePage(driver);
			if (!result) {
				userProfileMsgList.addAll(accountSettingUtilObj.accountSettingMsgList);
				return result;
			}
		} catch (Exception e) {
			userProfileMsgList.add("clickOnAccountSettingAndValidate_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnProfileEditBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getIconEdit(), 10);
			if (!result) {
				userProfileMsgList.add("Edit icon is not visible.");
            }
			cfObj.commonClick(userProfilePageObj.getIconEdit());

			cfObj.waitTillElementIsVisible(driver, 5, 2000,
					cfObj.commonGetElement(driver, "android:id/progress", "id"));

		} catch (Exception e) {
			userProfileMsgList.add("clickOnProfileEditBtn_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	/*---------------------------------------------New UserProfile Screen-------------------------------*/

	public boolean validateNewProfileScreen(AppiumDriver<MobileElement> driver, ProfileType profileType) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getNewUserProfileDotIcon(), 10);
			if (!result) {
				userProfileMsgList.add("NewUserProfileDotIcon is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowingTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowingTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowersTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowersTab is not visible.");
				return result;
			}

			if (profileType == ProfileType.OWN) {

				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCoinEarnedTabTitle(),
							10);
					if (!result) {
						userProfileMsgList.add("CoinEarnedTabTitle is not visible.");
						return result;
					}

					cfObj.commonClick(userProfilePageObj.getCoinEarnedTabTitle());

					result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCoinHistoryText(), 10);
					if (!result) {
						userProfileMsgList.add("CoinHistoryText is not visible.");
						return result;
					}

					cfObj.commonClick(userProfilePageObj.getBackBtn());

					Thread.sleep(1000);
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMyPurchasedBtn(), 10);
				if (!result) {
					userProfileMsgList.add("MyPurchasedBtn is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkBtn().get(0), 10);
				if (!result) {
					userProfileMsgList.add("BookMark logo is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkBtn().get(1), 10);
				if (!result) {
					userProfileMsgList.add("BookMarkBtn is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getProfileCompletionTitle(),
						10);
				if (!result) {
					userProfileMsgList.add("ProfileCompletionTitle is not visible.");
					return result;
				}

			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver,
						userProfilePageObj.getFollowFollowingBtn().get(0), 10);
				if (!result) {
					userProfileMsgList.add("FollowFollowing button is not visible.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllActivityTab(), 10);
			if (!result) {
				userProfileMsgList.add("AllActivityTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTotalLikesTitle(), 10);
			if (!result) {
				userProfileMsgList.add("TotalLikesTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTotalCommentsTitle(), 10);
			if (!result) {
				userProfileMsgList.add("TotalCommentsTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTotalPostTitle(), 10);
			if (!result) {
				userProfileMsgList.add("TotalPostTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Faculty\"]", "xpath", 5);
			if (!result) {

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAttemptedQuizTab(), 10);
				if (!result) {
					userProfileMsgList.add("AttemptedQuizTab is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getVideoWatchedTab(), 10);
				if (!result) {
					userProfileMsgList.add("VideoWatchedTab is not visible.");
					return result;
				}

				cfObj.scrollDown(driver, 1);

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getArticlesReadTab(), 10);
				if (!result) {
					userProfileMsgList.add("ArticlesReadTab is not visible.");
					return result;
				}

				cfObj.scrollUp(driver, 1);
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateOwnProfileScreen_Exception " + e.getMessage());
		}
		return result;
	}

	public boolean validateFacultyProfileScreen(AppiumDriver<MobileElement> driver, ProfileType profileType) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getNewUserProfileDotIcon(), 10);
			if (!result) {
				userProfileMsgList.add("NewUserProfileDotIcon is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowingTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowingTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowersTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowersTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFacultyTag(), 10);
			if (!result) {
				userProfileMsgList.add("FacultyTag is not visible.");
				return result;
			}
			result = validateGroupsTab(driver);
			if (!result) {
				userProfileMsgList.add("Not able to validateGroupsTab.");
				return result;
			}

			if (profileType == ProfileType.OWN) {

				// Issue on teacher side
//				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkBtn().get(0), 10);
//				if (!result) {
//					userProfileMsgList.add("BookMark logo is not visible.");
//					return result;
//				}
//
//				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkBtn().get(1), 10);
//				if (!result) {
//					userProfileMsgList.add("BookMarkBtn is not visible.");
//					return result;
//				}

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getProfileCompletionTitle(),
						10);
				if (!result) {
					userProfileMsgList.add("ProfileCompletionTitle is not visible.");
					return result;
				}
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver,
						userProfilePageObj.getFollowFollowingBtn().get(0), 10);
				if (!result) {
					userProfileMsgList.add("FollowFollowing button is not visible.");
					return result;
				}
			}

			result = validateMyCourseTab(driver);
			if (!result) {
				userProfileMsgList.add("Not able to validate MyCourseTab.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllActivityTab(), 10);
			if (!result) {
				userProfileMsgList.add("AllActivityTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTotalLikesTitle(), 10);
			if (!result) {
				userProfileMsgList.add("TotalLikesTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTotalCommentsTitle(), 10);
			if (!result) {
				userProfileMsgList.add("TotalCommentsTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTotalPostTitle(), 10);
			if (!result) {
				userProfileMsgList.add("TotalPostTitle is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateOwnProfileScreen_Exception" + e.getMessage());

		}
		return result;
	}

	public enum ProfileType {
		OWN, OTHER;
	}

	public boolean validateOwnFollowFollowingTabScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String attribute = null;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				attribute = "content-desc";
			} else {
				attribute = "name";
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowingTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowingTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowersTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowersTab is not visible.");
				return result;
			}

			int followingCount = Integer.parseInt(userProfilePageObj.getFollowingTab().getAttribute(attribute)
					.replaceAll("followingPageLocator", "").replaceAll("Following", "").trim());
			System.out.println("Following_Count:--->" + followingCount);

			int followersCount = Integer.parseInt(userProfilePageObj.getFollowersTab().getAttribute(attribute)
					.replaceAll("followerPageLocator", "").replaceAll("Followers", "").trim());
			System.out.println("Followers_Count:--->" + followersCount);

			cfObj.commonClick(userProfilePageObj.getFollowersTab());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowersTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowersTab is not visible.");
				return result;
			}

			result = userProfilePageObj.getFollowersTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("FollowersTab is not clickable.");
				return result;
			}
			System.out.println("FollowScreen_Count--->"
					+ (Integer.parseInt(userProfilePageObj.getFollowersTab().getAttribute(attribute).split(" ")[0]
							.replaceAll("Tab", "").trim())));

			result = Integer.parseInt(userProfilePageObj.getFollowersTab().getAttribute(attribute).split(" ")[0]
					.replaceAll("Tab", "").trim()) == followersCount;
			if (!result) {
				userProfileMsgList.add("FollowersUser count is not matching.");
				return result;
			}

//			if (Integer.parseInt(userProfilePageObj.getFollowersTab().getAttribute(attribute).split(" ")[0]
//					.replaceAll("Tab", "").trim()) > 0) {
//				if (cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getRemoveBtn().get(0), 10)) {
//					int removeBtnCount = userProfilePageObj.getRemoveBtn().size();
//					cfObj.commonClick(userProfilePageObj.getRemoveBtn().get(removeBtnCount - 1));
//					if (removeBtnCount > 1) {
//						Thread.sleep(2000);
//						result = Integer
//								.parseInt(userProfilePageObj.getFollowersTab().getAttribute(attribute).split(" ")[0]
//										.replaceAll("Tab", "").trim()) == followersCount - 1;
//						if (!result) {
//							userProfileMsgList.add("Remove button is not clickable.");
//							return result;
//						}
//					}
//				}
//			}
			Thread.sleep(2000);

			cfObj.commonClick(userProfilePageObj.getFollowingTab());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowingTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowingTab is not visible.");
				return result;
			}

			result = userProfilePageObj.getFollowingTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("FollowingTab is not clickable.");
				return result;
			}

			System.out.println("FollowingScreen_Count--->"
					+ (userProfilePageObj.getFollowingTab().getAttribute(attribute).charAt(0) - 48));

			result = Integer.parseInt(userProfilePageObj.getFollowingTab().getAttribute(attribute).split(" ")[0]
					.replaceAll("Tab", "").trim()) == followingCount;
			if (!result) {
				userProfileMsgList.add("FollowingUser count is not matching.");
				return result;
			}

			if (Integer.parseInt(userProfilePageObj.getFollowingTab().getAttribute(attribute).split(" ")[0].replaceAll("Tab", "").trim()) > 0) {

				if (cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowFollowingBtn().get(0), 10)) {

					String followUnfollowBtnText = userProfilePageObj.getFollowFollowingBtn().get(0).getAttribute(attribute);

					cfObj.commonClick(userProfilePageObj.getFollowFollowingBtn().get(0));

					if (followUnfollowBtnText.contains("followingLocator")) {
						result = userProfilePageObj.getFollowFollowingBtn().get(0).getAttribute(attribute).contains("followLocator");
						if (!result) {
							userProfileMsgList.add("Follow button is not clickable.");
							return result;
						}
						Thread.sleep(2000);

						cfObj.commonClick(userProfilePageObj.getFollowFollowingBtn().get(0));

						Thread.sleep(2000);

						result = userProfilePageObj.getFollowFollowingBtn().get(0).getAttribute(attribute).contains("followingLocator");
						if (!result) {
							userProfileMsgList.add("Following button is not clickable.");
							return result;
						}
					} else {
						result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowFollowingBtn().get(0), 10);
						if (!result) {
							userProfileMsgList.add("Following button is not clickable.");
							return result;
						}
					}
				}
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateFollowFollowingTabScreen_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateOthersFollowFollowingTabScreen(AppiumDriver<MobileElement> driver, String ownName) {
		boolean result = true;
		String attribute;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				attribute = "content-desc";
			} else {
				attribute = "name";
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowingTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowingTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowersTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowersTab is not visible.");
				return result;
			}

			int followingCount = Integer.parseInt(userProfilePageObj.getFollowingTab().getAttribute(attribute)
					.replaceAll("followingPageLocator", "").replaceAll("Following", "").trim());
			System.out.println("Following_Count:--->" + followingCount);

			int followersCount = Integer.parseInt(userProfilePageObj.getFollowersTab().getAttribute(attribute)
					.replaceAll("followerPageLocator", "").replaceAll("Followers", "").trim());
			System.out.println("Followers_Count:--->" + followersCount);

			cfObj.commonClick(userProfilePageObj.getFollowersTab());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowersTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowersTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowingTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowingTab is not visible.");
				return result;
			}

			result = userProfilePageObj.getFollowersTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("FollowersTab is not clickable.");
				return result;
			}
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = Integer.parseInt(userProfilePageObj.getFollowersTab().getAttribute(attribute).split(" ")[0]
						.replaceAll("Tab", "").trim()) == followersCount;
				if (!result) {
					userProfileMsgList.add("FollowersUser count is not matching.");
					return result;
				}
				Thread.sleep(2000);

				if (Integer.parseInt(userProfilePageObj.getFollowersTab().getAttribute(attribute).split(" ")[0]
						.replaceAll("Tab", "").trim()) == 0) {
					result = cfObj.commonWaitForElementToBeVisible(driver,
							userProfilePageObj.getFollowFollowingSceenEmptyState(), 10);
					if (!result) {
						userProfileMsgList.add("EmptyState is not visible.");
						return result;
					}
				}

				else {
					if (Integer.parseInt(userProfilePageObj.getFollowersTab().getAttribute(attribute).split(" ")[0]
							.replaceAll("Tab", "").trim()) == 1) {
						if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								"//*[contains(@" + attribute + ",'SAGAR')]", "xpath", 30)) {
							System.out.println("Only Own profile is present.");
						}
					} else {

						if (cfObj.commonWaitForElementToBeVisible(driver,
								userProfilePageObj.getFollowFollowingBtn().get(0), 30)) {

							cfObj.commonClick(userProfilePageObj.getFollowFollowingBtn().get(0));
						}
					}

					cfObj.commonClick(userProfilePageObj.getFollowingTab());

					result = userProfilePageObj.getFollowingTab().getAttribute("selected").equalsIgnoreCase("true");
					if (!result) {
						userProfileMsgList.add("FollowingTab is not clickable.");
						return result;
					}

					result = Integer.parseInt(userProfilePageObj.getFollowingTab().getAttribute(attribute).split(" ")[0]
							.replaceAll("Tab", "").trim()) == followingCount;
					if (!result) {
						userProfileMsgList.add("FollowingUser count is not matching.");
						return result;
					}
					Thread.sleep(2000);

					if (Integer.parseInt(userProfilePageObj.getFollowingTab().getAttribute(attribute).split(" ")[0]
							.replaceAll("Tab", "").trim()) == 0) {
						result = cfObj.commonWaitForElementToBeVisible(driver,
								userProfilePageObj.getFollowFollowingSceenEmptyState(), 10);
						if (!result) {
							userProfileMsgList.add("EmptyState is not visible.");
							return result;
						}
					} else {
						if (Integer.parseInt(userProfilePageObj.getFollowingTab().getAttribute(attribute).split(" ")[0]
								.replaceAll("Tab", "").trim()) == 1) {
							if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
									"//*[contains(@" + attribute + ",'SAGAR')]", "xpath", 30)) {
								System.out.println("Only Own profile is present.");
							}
						} else {
							if (cfObj.commonWaitForElementToBeVisible(driver,
									userProfilePageObj.getFollowFollowingBtn().get(0), 10)) {

								cfObj.commonClick(userProfilePageObj.getFollowFollowingBtn().get(0));

							}

						}
					}
				}
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateFollowFollowingTabScreen_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateOtherUserScreenDotIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = clickOnProfileDotIcon(driver);
			if (!result) {
				userProfileMsgList.add("Not able to click ProfileDotIcon.");
				return result;
			}

			result = validateCopyProfileLinkTab(driver);
			if (!result) {
				userProfileMsgList.add("Not able to validate CopyProfileLinkTab");
				return result;
			}

			result = clickOnProfileDotIcon(driver);
			if (!result) {
				userProfileMsgList.add("Not able to click ProfileDotIcon.");
				return result;
			}

			result = validateReportTab(driver);
			if (!result) {
				userProfileMsgList.add("Not able to validate ReportTab");
				return result;
			}

		} catch (Exception e) {
			userProfileMsgList.add("validateOtherUserScreenDotIcon_Exception" + e.getMessage());
			result = false;

		}
		return result;
	}

	public boolean validateCopyProfileLinkTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCopyProfileLinkTab(), 10);
			if (!result) {
				userProfileMsgList.add("CopyProfileLinkTab is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getCopyProfileLinkTab());

			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getProfileLinkCopyToast(),
						10);
				if (!result) {
					userProfileMsgList.add("ProfileLinkCopyToast is not visible.");
					System.out.println("ProfileLinkCopyToast is not visible.");
					result = true;
				}
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateCopyProfileLinkTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateReportTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getReportTab(), 10);
			if (!result) {
				userProfileMsgList.add("ReportTab is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getReportTab());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getReportProfilePopUpTitle(), 10);
			if (!result) {
				userProfileMsgList.add("ReportProfilePopUpTitle is not visible.");
				return result;
			}

			List<MobileElement> getReportOptionList = userProfilePageObj.getReportOptionList();

			for (int i = 0; i < getReportOptionList.size() - 1; i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, getReportOptionList.get(i), 10);
				if (!result) {
					userProfileMsgList.add("ReportOptionList is not visible at " + i);
					return result;
				}

				cfObj.commonClick(userProfilePageObj.getReportOptionList().get(i));

				result = userProfilePageObj.getReportOptionList().get(i).getAttribute("checked")
						.equalsIgnoreCase("true");
				if (!result) {
					userProfileMsgList.add("ReportOptionList is not clickable at " + i);
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					getReportOptionList.get(getReportOptionList.size() - 1), 10);
			if (!result) {
				userProfileMsgList.add("ReportOptionList is not visible at " + (getReportOptionList.size() - 1));
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getReportOptionList().get(getReportOptionList.size() - 1));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.EditText", "xpath",
					10);
			if (!result) {
				userProfileMsgList.add("In Other edit box is not visible in report popup");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, getReportOptionList.get(0), 10);
			if (!result) {
				userProfileMsgList.add("ReportOptionList is not visible at " + 0);
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getReportOptionList().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getReportBtn(), 10);
			if (!result) {
				userProfileMsgList.add("ReportBtn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getReportCancelBtn(), 10);
			if (!result) {
				userProfileMsgList.add("ReportCancelBtn is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getReportBtn());

			result = !cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getReportBtn(), 5);
			if (!result) {

				cfObj.commonClick(userProfilePageObj.getReportBtn());

				result = !cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getReportBtn(), 5);
				if (!result) {
					userProfileMsgList.add("ReportBtn is not clickable.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateReportTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnFollowFollowingScreenBackBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowFollowingScreenBackBtn(),
					10);
			if (!result) {
				userProfileMsgList.add("FollowFollowing screen back button is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getFollowFollowingScreenBackBtn());

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnFollowFollowingScreenBackBtn" + e.getMessage());
		}
		return result;

	}

	public boolean clickOnProfileDotIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getNewUserProfileDotIcon(), 20);
			if (!result) {
				userProfileMsgList.add("NewUserProfileDotIcon is not visible in profile");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getNewUserProfileDotIcon());

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnProfileDotIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateOwnProfileScreenDotIcon(AppiumDriver<MobileElement> driver, String userType) {
		boolean result = true;
		int max;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				max = 5;
			} else {
				max = 4;
			}
			for (int i = 0; i < max; i++) {
				result = clickOnProfileDotIcon(driver);
				if (!result) {
					userProfileMsgList.add("NewUserProfileDotIcon is not clickable.");
					return result;
				}
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					switch (i) {
					case 0:
						result = validateCopyProfileLinkTab(driver);
						if (!result) {
							userProfileMsgList.add("Not able to validate CopyProfileLinkTab");
							return result;
						}
						break;
					case 1:
						result = validateMyOrderTab(driver, userType);
						if (!result) {
							userProfileMsgList.add("Not able to validate MyOrderTab");
							return result;
						}
						break;

					case 2:
						result = validateCoinBalanceTab(driver);
						if (!result) {
							userProfileMsgList.add("Not able to validate CoinBalanceTab");
							return result;
						}
						break;

					case 3:
						result = validateEditPersonalInformationTab(driver);
						if (!result) {
							userProfileMsgList.add("Not able to validate EditPersonalDetailsTab");
							return result;
						}
						break;
					case 4:
						result = validateSettingTab(driver);
						if (!result) {
							userProfileMsgList.add("Not able to validate SettingTab");
							return result;
						}
						break;
					}
				} else {
					switch (i) {
					case 0:
						result = validateCopyProfileLinkTab(driver);
						if (!result) {
							userProfileMsgList.add("Not able to validate CopyProfileLinkTab");
							return result;
						}
						break;
					case 1:
						result = validateEditPersonalInformationTab(driver);
						if (!result) {
							userProfileMsgList.add("Not able to validate EditPersonalDetailsTab");
							return result;
						}
						break;
					case 2:
						result = validateMyOrderTab(driver, userType);
						if (!result) {
							userProfileMsgList.add("Not able to validate MyOrderTab");
							return result;
						}
						break;
					case 3:
						result = validateSettingTab(driver);
						if (!result) {
							userProfileMsgList.add("Not able to validate SettingTab");
							return result;
						}
						homePageUtilObj = new HomePageUtil(driver);
						result = homePageUtilObj.clickHomeBtn(driver);
						if (!result) {
							userProfileMsgList.addAll(homePageUtilObj.homeMsgList);
							return result;
						}

						result = homePageUtilObj.justOpenNavigationDrawer(driver);
						if (!result) {
							userProfileMsgList.addAll(homePageUtilObj.homeMsgList);
							return result;
						}

						result = homePageUtilObj.clickOnUserProfileSection(driver);
						if (!result) {
							userProfileMsgList.addAll(homePageUtilObj.homeMsgList);
							return result;
						}

						result = cfObj.commonWaitForElementToBeVisible(driver,
								userProfilePageObj.getBookMarkBtn().get(0), 10);
						if (!result) {
							userProfileMsgList.add("Failed to open user profile section.");
							return result;
						}
						break;
					}
				}
				Thread.sleep(500);
			}

		} catch (Exception e) {
			userProfileMsgList.add("validateOtherUserScreenDotIcon_Exception" + e.getMessage());
			result = false;

		}
		return result;
	}

	public boolean validateCoinBalanceTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCoinBalanceTab(), 10);
			if (!result) {
				userProfileMsgList.add("CoinBalanceTab is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getCoinBalanceTab());

			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCoinHistoryText(), 30);
			if (!result) {
				userProfileMsgList.add("Coin history text is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getBackBtn());

			result = !(cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCoinHistoryText(), 5));
			if (!result) {
				userProfileMsgList.add("Coin history text is visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateCoinBalanceTab_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean validateMyOrderTab(AppiumDriver<MobileElement> driver, String userType) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMyOrderTab(), 30);
			if (!result) {
				userProfileMsgList.add("MyOrderTab is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getMyOrderTab());

			myOrdersPageUtilObj = new MyOrdersPageUtil(driver);

			if (userType.equalsIgnoreCase("NewUser")) {
				result = myOrdersPageUtilObj.verifyMyOrderUIForNewUser(driver);
			} else {
				result = myOrdersPageUtilObj.verifyMyOrdersPage(driver);
			}
			if (!result) {
				userProfileMsgList.addAll(myOrdersPageUtilObj.myOrderMsgList);
				return result;
			}

			result = myOrdersPageUtilObj.navigateToUserProfilePage(driver);
			if (!result) {
				userProfileMsgList.addAll(myOrdersPageUtilObj.myOrderMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateMyOrderTab_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean clickOnOtherUserProfileInFollowFollowingScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		int size = 0;
		try {

			cfObj.scrollUtill(driver, 1, direction.UP);

			try {
				size = userProfilePageObj.getFollowFollowingUserName().size();
			} catch (Exception ignored) {
            }

			if(size == 0){
				cfObj.scrollUtill(driver, 1, direction.UP);
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					userProfilePageObj.getFollowFollowingUserName().get(0), 30);
			if (!result) {
				userProfileMsgList.add("FollowFollowingUserName is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getFollowFollowingUserName().get(0));

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnOtherUserProfileInFollowFollowingScreen_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnFollowingTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowingTab(), 10);
			if (!result) {
				userProfileMsgList.add("FollowingTab is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getFollowingTab());

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnFollowingTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnEditPersonalDetailsTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEditPersonalTab(), 10);
			if (!result) {
				userProfileMsgList.add("EditPersonalTab is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getEditPersonalTab());
			Thread.sleep(1000);
			result = !cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEditPersonalTab(), 10);
			if (!result) {
				userProfileMsgList.add("EditPersonalTab is not clickable.");
				return result;
			}

			Thread.sleep(1000);

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnEditPersonalDetailsTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateMyCourseTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMyCourseBtn(), 10);
			if (!result) {
				userProfileMsgList.add("MyCourseBtn is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getMyCourseBtn());

			if (ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")) {
				if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//*[contains(@content-desc,'Hamza')]", "xpath", 10);
					if (!result) {
						userProfileMsgList.add("MyCoursePageTitle is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver,
							"//*[contains(@content-desc,'Hamza')]/preceding-sibling::android.widget.Button", "xpath"));
				} else {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//*[contains(@content-desc,'Sagar')]", "xpath", 10);
					if (!result) {
						userProfileMsgList.add("MyCoursePageTitle is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver,
							"//*[contains(@content-desc,'Sagar')]/preceding-sibling::android.widget.Button", "xpath"));
				}
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMyCoursePageTitle(), 10);
				if (!result) {
					userProfileMsgList.add("MyCoursePageTitle is not visible.");
					return result;
				}

				cfObj.commonClick(userProfilePageObj.getMyCoursePageBackBtn());
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMyCourseBtn(), 10);
			if (!result) {
				userProfileMsgList.add("MyCourseBtn is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateMyCourseTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateFollowFollowingBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowFollowingBtn().get(0),
					10);
			if (!result) {
				userProfileMsgList.add("FollowFollowingBtn is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getFollowFollowingBtn().get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"followLocator\n" + "Following\"]", "xpath", 10);
			if (!result) {

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowFollowingBtn().get(0),
						10);
				if (!result) {
					userProfileMsgList.add("FollowFollowingBtn is not visible.");
					return result;
				}
				cfObj.commonClick(userProfilePageObj.getFollowFollowingBtn().get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc=\"followLocator\n" + "Following\"]", "xpath", 10);
				if (!result) {
					userProfileMsgList.add("Following btn in followers listing on a user is not visible.");
					result = true;
				}
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateFollowFollowingBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateFollowerInFacultyFollowerListing(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowFollowingBtn().get(0),
					10);
			if (!result) {
				userProfileMsgList.add("FollowFollowingBtn is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getFollowFollowingBtn().get(0));

			cfObj.commonClick(userProfilePageObj.getSimpleUserAccountName());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"followLocator\n" + "Follow\"]", "xpath", 10);
			if (!result) {
				userProfileMsgList.add("Follow btn in another user of faculty follower listing");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"BookMarkPageLocator\n" + "Bookmark\"]", "xpath", 10);
			if (!result) {
				userProfileMsgList.add("Bookmark btn in another user of faculty follower listing");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"allActivityLocator\n" + "View All Activity\"]", "xpath", 10);
			if (!result) {
				userProfileMsgList.add("View all activity btn in another user of faculty follower listing");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Consumption Performance\"]", "xpath", 10);
			if (!result) {
				userProfileMsgList.add("Consumption Performance tab in another user of faculty follower listing");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Total Likes\"]", "xpath", 10);
			if (!result) {
				userProfileMsgList.add("Likes,posts,comments tab in another user of faculty follower listing");
				return result;
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateFollowerInFacultyFollowerListing_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnMyPurchaseTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMyPurchasedBtn(), 10);
			if (!result) {
				userProfileMsgList.add("MyPurchasedBtn is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getMyPurchasedBtn());

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnMyPurchaseTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnBookMarkBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			Thread.sleep(3000);

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkBtn().get(0), 10);
			if (!result) {
				userProfileMsgList.add("BookMark logo is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkBtn().get(1), 10);
			if (!result) {
				userProfileMsgList.add("BookMarkBtn is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getBookMarkBtn().get(0));

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnBookMarkBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnAllActivityBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllActivityTab(), 10);
			if (!result) {
				userProfileMsgList.add("AllActivityTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getAllActivityTab());

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnAllActivityBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateGroupsTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getGroupsTab(), 10);
			if (!result) {
				userProfileMsgList.add("GroupTab is not visible.");
				return result;
			}
			int groupCount;
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				groupCount = Integer.parseInt(userProfilePageObj.getGroupsTab().getAttribute("content-desc")
						.replaceAll("facultyGroupPageLocator", "").replaceAll("Groups", "").trim());
			} else {
				groupCount = Integer.parseInt(userProfilePageObj.getGroupsTab().getAttribute("name")
						.replaceAll("facultyGroupPageLocator", "").replaceAll("Groups", "").trim());
			}
			System.out.println("GroupsCount:--->" + groupCount);

			cfObj.commonClick(userProfilePageObj.getGroupsTab());
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getGroupsTabPageTitle(), 10);
			if (!result) {
				userProfileMsgList.add("GroupsTabPageTitle is not visible.");
				return result;
			}

			result = userProfilePageObj.getGroupsTabPageTitle().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("GroupsTab is not clickable.");
				return result;
			}
			if (!(ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")
					&& ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp"))) {
				if (groupCount == 0) {
					result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getGroupEmptyState(), 10);
					if (!result) {
						userProfileMsgList.add("GroupEmptyState is not visible.");
						return result;
					}
				}
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					result = Integer.parseInt(
							userProfilePageObj.getGroupsTabPageTitle().getAttribute("content-desc").split(" ")[0]
									.replaceAll("Tab", "").trim()) == groupCount;
				} else {
					result = Integer
							.parseInt(userProfilePageObj.getGroupsTabPageTitle().getAttribute("name").split(" ")[0]
									.replaceAll("Tab", "").trim()) == groupCount;
				}
				if (!result) {
					userProfileMsgList.add("GroupsTab count is not matching.");
					return result;
				}
			}
			cfObj.commonClick(userProfilePageObj.getFollowFollowingScreenBackBtn());

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateGroupsTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateBookMarkPageUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkPageTitle(), 10);
			if (!result) {
				userProfileMsgList.add("BookMarkPage title is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostsTab(), 10);
			if (!result) {
				userProfileMsgList.add("PostsTab is not visible.");
				return result;
			}
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")
					&& ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getQuestionsTab(), 10);
				if (!result) {
					userProfileMsgList.add("QuestionsTab is not visible.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getVideosTab(), 10);
				if (!result) {
					userProfileMsgList.add("VideosTab is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCurrentAffairsTab(), 10);
				if (!result) {
					userProfileMsgList.add("CurrentAffairsTab is not visible.");
					return result;
				}

				cfObj.swipeLeftOnElement(userProfilePageObj.getCurrentAffairsTab(), driver);
				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getArticlesTab(), 10);
				if (!result) {
					userProfileMsgList.add("ArticlesTab is not visible.");
					return result;
				}

				cfObj.swipeLeftOnElement(userProfilePageObj.getArticlesTab(), driver);

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getJobAlertsTab(), 10);
				if (!result) {
					userProfileMsgList.add("JobAlertsTab is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkPageBackBtn(), 10);
				if (!result) {
					userProfileMsgList.add("BookMarkPageBackBtn is not visible.");
					return result;
				}
				cfObj.commonClick(userProfilePageObj.getVideosTab());
				Thread.sleep(2000);
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateBookMarkPageUI_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean validateMaterialInBookMarkPost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostsTab(), 10);
			if (!result) {
				userProfileMsgList.add("PostsTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getPostsTab());
			Thread.sleep(2000);
			result = userProfilePageObj.getPostsTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("PostsTab is not clickable.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkPostTitle().get(0),
					30);
			if (!result) {
				userProfileMsgList.add("BookMarkPost is not visible.");
				return result;
			}
			result = userProfilePageObj.getBookMarkPostTitle().size() == 1;
			if (!result) {
				userProfileMsgList.add("BookMarkPost count is not matching.");
				return result;
			}
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				cfObj.commonClick(userProfilePageObj.getBookMarkPostTitle().get(0));
				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllActivityTab(), 10);
				if (!result) {
					userProfileMsgList.add("Not able to open user profile screen.");
					return result;
				}

				cfObj.commonClick(userProfilePageObj.getNewUserProfileBackBtn());
				if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getQuestionsTab(), 10);
					if (!result) {
						userProfileMsgList.add("QuestionsTab is not visible.");
						return result;
					}
					cfObj.commonClick(userProfilePageObj.getQuestionsTab());
					Thread.sleep(2000);
					result = userProfilePageObj.getQuestionsTab().getAttribute("selected").equalsIgnoreCase("true");
					if (!result) {
						userProfileMsgList.add("QuestionsTab is not clickable.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkedQuestion(),
							10);
					if (!result) {
						userProfileMsgList.add("BookMarkedQuestion is not visible.");
						return result;
					}

					cfObj.commonClick(userProfilePageObj.getBookMarkedQuestion());

					result = cfObj.commonWaitForElementToBeVisible(driver,
							userProfilePageObj.getBookMarkedQuestionPageTitle(), 10);
					if (!result) {
						userProfileMsgList.add("BookMarkedQuestionPageTitle is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "back_button", "id"));

					Thread.sleep(2000);

					for (int i = 0; i < 4; i++) {
						switch (i) {
						case 0:
							result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getVideosTab(),
									10);
							if (!result) {
								userProfileMsgList.add("VideosTab is not visible.");
								return result;
							}
							cfObj.commonClick(userProfilePageObj.getVideosTab());
							Thread.sleep(2000);
							result = userProfilePageObj.getVideosTab().getAttribute("selected")
									.equalsIgnoreCase("true");
							if (!result) {
								userProfileMsgList.add("VideosTab is not clickable.");
								return result;
							}
							break;
						case 1:
							result = cfObj.commonWaitForElementToBeVisible(driver,
									userProfilePageObj.getCurrentAffairsTab(), 10);
							if (!result) {
								userProfileMsgList.add("CurrentAffairsTab is not visible.");
								return result;
							}
							cfObj.commonClick(userProfilePageObj.getCurrentAffairsTab());
							Thread.sleep(2000);
							result = userProfilePageObj.getCurrentAffairsTab().getAttribute("selected")
									.equalsIgnoreCase("true");
							if (!result) {
								userProfileMsgList.add("CurrentAffairsTab is not clickable.");
								return result;
							}
							break;
						case 2:
							result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getArticlesTab(),
									10);
							if (!result) {
								userProfileMsgList.add("ArticlesTab is not visible.");
								return result;
							}
							cfObj.commonClick(userProfilePageObj.getArticlesTab());
							Thread.sleep(2000);
							result = userProfilePageObj.getArticlesTab().getAttribute("selected")
									.equalsIgnoreCase("true");
							if (!result) {
								userProfileMsgList.add("ArticlesTab is not clickable.");
								return result;
							}
							break;
						case 3:
							result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getJobAlertsTab(),
									10);
							if (!result) {
								userProfileMsgList.add("JobAlertsTab is not visible.");
								return result;
							}
							cfObj.commonClick(userProfilePageObj.getJobAlertsTab());
							Thread.sleep(2000);
							result = userProfilePageObj.getJobAlertsTab().getAttribute("selected")
									.equalsIgnoreCase("true");
							if (!result) {
								userProfileMsgList.add("JobAlertsTab is not clickable.");
								return result;
							}
							break;

						}
						Thread.sleep(2000);

						result = cfObj.commonWaitForElementToBeVisible(driver,
								userProfilePageObj.getBookMarkMaterial().get(0), 30);
						if (!result) {
							userProfileMsgList.add("BookMarkMaterial is not visible.");
							return result;
						}
						result = userProfilePageObj.getBookMarkMaterial().size() == 1;
						if (!result) {
							userProfileMsgList.add("BookMarkMaterial count is not matching.");
							return result;
						}
					}
				}
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateMaterialInBookMarkPost_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateActivityPageUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getActivityPageTitle(), 10);
			if (!result) {
				userProfileMsgList.add("ActivityPage title is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllPostTab(), 10);
			if (!result) {
				userProfileMsgList.add("AllPostTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCommentTab(), 10);
			if (!result) {
				userProfileMsgList.add("CommentTab is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getLikeTab(), 10);
			if (!result) {
				userProfileMsgList.add("LikeTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMcqTab(), 10);
			if (!result) {
				userProfileMsgList.add("McqTab is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateActivityPageUI_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateEmptyStateInActivityTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			for (int i = 0; i < 4; i++) {
				switch (i) {
				case 0:
					result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllPostTab(), 10);
					if (!result) {
						userProfileMsgList.add("AllPostTab is not visible.");
						return result;
					}
					cfObj.commonClick(userProfilePageObj.getAllPostTab());
					break;
				case 1:
					result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCommentTab(), 10);
					if (!result) {
						userProfileMsgList.add("CommentTab is not visible.");
						return result;
					}
					cfObj.commonClick(userProfilePageObj.getCommentTab());
					break;
				case 2:
					result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getLikeTab(), 10);
					if (!result) {
						userProfileMsgList.add("LikeTab is not visible.");
						return result;
					}
					cfObj.commonClick(userProfilePageObj.getLikeTab());
					break;
				case 3:
					result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMcqTab(), 10);
					if (!result) {
						userProfileMsgList.add("McqTab is not visible.");
						return result;
					}
					cfObj.commonClick(userProfilePageObj.getMcqTab());
					break;

				}
				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEmptyStateImg(), 10);
				if (!result) {
					userProfileMsgList.add("EmptyStateImg is not visible.");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateEmptyStateInActivityTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnFacultProfileUserName(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFollowingTab(), 30);
			if (!result) {
				userProfileMsgList.add("Following tab is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getFollowingTab());

			int i = 0;
			while (!cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFacultyAccountName(), 10)) {
				cfObj.scrollUtill(driver, 1, direction.DOWN);
				if (i > 5) {
					System.out.println("Admin account is not present.");
					break;
				}
				i++;
			}

			cfObj.commonClick(userProfilePageObj.getFacultyAccountName());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getFacultyTag(), 30);
			if (!result) {
				userProfileMsgList.add("FacultyTag is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnFacultProfileUserName_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateLikeCommentPostCount(AppiumDriver<MobileElement> driver, String likeCount, String postCount,
			String commentCount) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTotalLikesCount(), 10);
			if (!result) {
				userProfileMsgList.add("Total like count is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTotalPostCount(), 10);
			if (!result) {
				userProfileMsgList.add("TotalPostCount is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTotalCommentsCount(), 10);
			if (!result) {
				userProfileMsgList.add("TotalCommentsCount is not visible.");
				return result;
			}
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				System.out.println("Like:-->" + userProfilePageObj.getTotalLikesCount().getAttribute("content-desc")
						+ "------>" + likeCount);

				result = userProfilePageObj.getTotalLikesCount().getAttribute("content-desc")
						.equalsIgnoreCase(likeCount);
				if (!result) {
					userProfileMsgList.add("Total like count is not matching.");
					return result;
				}

				System.out.println("Post:-->" + userProfilePageObj.getTotalPostCount().getAttribute("content-desc"));

				result = userProfilePageObj.getTotalPostCount().getAttribute("content-desc")
						.equalsIgnoreCase(postCount);
				if (!result) {
					userProfileMsgList.add("TotalPostCount is not matching.");
					return result;
				}

				System.out.println(
						"Comment:-->" + userProfilePageObj.getTotalCommentsCount().getAttribute("content-desc"));

				result = userProfilePageObj.getTotalCommentsCount().getAttribute("content-desc")
						.equalsIgnoreCase(commentCount);
				if (!result) {
					userProfileMsgList.add("TotalCommentsCount is not matching.");
					return result;
				}
			} else {
				System.out.println("Like:-->" + userProfilePageObj.getTotalLikesCount().getAttribute("name") + "------>"
						+ likeCount);

				result = userProfilePageObj.getTotalLikesCount().getAttribute("name").equalsIgnoreCase(likeCount);
				if (!result) {
					userProfileMsgList.add("Total like count is not matching.");
					return result;
				}

				System.out.println("Post:-->" + userProfilePageObj.getTotalPostCount().getAttribute("name"));

				result = userProfilePageObj.getTotalPostCount().getAttribute("name").equalsIgnoreCase(postCount);
				if (!result) {
					userProfileMsgList.add("TotalPostCount is not matching.");
					return result;
				}

				System.out.println("Comment:-->" + userProfilePageObj.getTotalCommentsCount().getAttribute("name"));

				result = userProfilePageObj.getTotalCommentsCount().getAttribute("name").equalsIgnoreCase(commentCount);
				if (!result) {
					userProfileMsgList.add("TotalCommentsCount is not matching.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateLikeCommentPostCount_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateEditPersonalInformationTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = clickOnEditPersonalDetailsTab(driver);
			if (!result) {
				userProfileMsgList.add("Not able to click EditPersonalDetailsTab");
				return result;
			}

			Thread.sleep(2000);

			editProfileUtilObj = new EditProfileUtil(driver);
			result = editProfileUtilObj.validatePersonalInformationPage(driver);
			if (!result) {
				userProfileMsgList.addAll(editProfileUtilObj.editProfileMsgList);
				return result;
			}
			driver.navigate().back();

			// Getting outside the profile page when click on back btn - BUG
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllActivityTab(), 10);
			if (!result) {
				System.out.println("Not able to navigate to userProfile screen, after coming from edit personal dashboard page");

				homePageUtilObj = new HomePageUtil(driver);
				result = homePageUtilObj.justOpenNavigationDrawer(driver);
				if(!result){
					userProfileMsgList.addAll(homePageUtilObj.homeMsgList);
					return result;
				}

				result = homePageUtilObj.clickOnUserProfileSection(driver);
				if(!result){
					userProfileMsgList.addAll(homePageUtilObj.homeMsgList);
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllActivityTab(), 10);
				if (!result) {
					userProfileMsgList.add("Not able to navigate to userProfile screen.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateEditPersonalInformationTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnSettingTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getSettingTab(), 10);
			if (!result) {
				userProfileMsgList.add("SettingTab is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getSettingTab());

			result = !cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getSettingTab(), 5);
			if (!result) {
				userProfileMsgList.add("SettingTab is not clickable.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnSettingTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateSettingTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = clickOnSettingTab(driver);
			if (!result) {
				userProfileMsgList.add("Not able to click SettingTab");
				return result;
			}

			accountSettingUtilObj = new AccountSettingUtil(driver);
			result = accountSettingUtilObj.verifyAccountSettingUI(driver);
			if (!result) {
				userProfileMsgList.addAll(accountSettingUtilObj.accountSettingMsgList);
				return result;
			}
			driver.navigate().back();

			// Getting outside the profile page when click on back btn - BUG
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllActivityTab(), 10);
			if (!result) {
				System.out.println("Not able to navigate to userProfile screen, after coming back from account");

				homePageUtilObj = new HomePageUtil(driver);
				result = homePageUtilObj.justOpenNavigationDrawer(driver);
				if(!result){
					userProfileMsgList.addAll(homePageUtilObj.homeMsgList);
					return result;
				}

				result = homePageUtilObj.clickOnUserProfileSection(driver);
				if(!result){
					userProfileMsgList.addAll(homePageUtilObj.homeMsgList);
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllActivityTab(), 10);
				if (!result) {
					userProfileMsgList.add("Not able to navigate to userProfile screen.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateSettingTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnProfileEditBtnNew(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getUserDetailsEditBtn(), 10);
			if (!result) {
				userProfileMsgList.add("UserDetailsEditBtn is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getUserDetailsEditBtn());
			Thread.sleep(1000);
			result = !cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getUserDetailsEditBtn(), 10);
			if (!result) {
				userProfileMsgList.add("UserDetailsEditBtn is not clickable.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnProfileEditBtnNew_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnLogOutTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getLogoutTab(), 10);
			if (!result) {
				userProfileMsgList.add("LogoutTab is not visible in profile");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getLogoutTab());

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnLogOutTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateVideoTabEmptyStateAndBookMarkFunction(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getVideosTab(), 10);
			if (!result) {
				userProfileMsgList.add("VideosTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getVideosTab());
			Thread.sleep(2000);
			result = userProfilePageObj.getVideosTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("VideosTab is not clickable.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getVideoTabEmptyState(), 10);
			if (!result) {
				userProfileMsgList.add("VideoTabEmptyState is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getGoToVideoBtn());
			result = videoCoursesPageUtilObj.validateVideoSectionPage(driver);
			if (!result) {
				userProfileMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
				return result;
			}

			result = videoCoursesPageUtilObj.addVideoInBookMark(driver);
			if (!result) {
				userProfileMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getQuestionsTab());
			cfObj.commonClick(userProfilePageObj.getVideosTab());
			Thread.sleep(3000);

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkMaterial().get(0), 30);
			if (!result) {
				userProfileMsgList.add("BookMarkMaterial is not visible.");
				return result;
			}
			result = userProfilePageObj.getBookMarkMaterial().size() == 1;
			if (!result) {
				userProfileMsgList.add("BookMarkMaterial count is not matching.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateVideoTabEmptyStateAndBookMarkFunction_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateCurrentAffairTabEmptyStateAndBookMarkFunction(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCurrentAffairsTab(), 10);
			if (!result) {
				userProfileMsgList.add("CurrentAffairsTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getCurrentAffairsTab());
			Thread.sleep(2000);
			result = userProfilePageObj.getCurrentAffairsTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("CurrentAffairsTab is not clickable.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCurrentAffairTabEmptyState(),
					10);
			if (!result) {
				userProfileMsgList.add("CurrentAffairTabEmptyState is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getGoToCurrentAffairsBtn());
			result = commonStudyMaterialSectionUtilObj.verifyCurrentAffairPage(driver);
			if (!result) {
				userProfileMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
				return result;
			}

			result = commonStudyMaterialSectionUtilObj.clicOnMaterialAndAddInBookMark(driver);
			if (!result) {
				userProfileMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getVideosTab());
			cfObj.commonClick(userProfilePageObj.getCurrentAffairsTab());
			Thread.sleep(3000);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkMaterial().get(0), 30);
			if (!result) {
				userProfileMsgList.add("BookMarkMaterial is not visible.");
				return result;
			}
			result = userProfilePageObj.getBookMarkMaterial().size() == 1;
			if (!result) {
				userProfileMsgList.add("BookMarkMaterial count is not matching.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateCurrentAffairTabEmptyStateAndBookMarkFunction_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateArticleTabEmptyStateAndBookMarkFunction(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getArticlesTab(), 10);
			if (!result) {
				userProfileMsgList.add("ArticlesTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getArticlesTab());
			Thread.sleep(2000);
			result = userProfilePageObj.getArticlesTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("ArticlesTab is not clickable.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getArticleTabEmptyState(), 10);
			if (!result) {
				userProfileMsgList.add("ArticleTabEmptyState is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getGoToArticleBtn());
			result = commonStudyMaterialSectionUtilObj.verifyNotesAndArticlesPage(driver);
			if (!result) {
				userProfileMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
				return result;
			}

			result = commonStudyMaterialSectionUtilObj.clicOnMaterialAndAddInBookMark(driver);
			if (!result) {
				userProfileMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getCurrentAffairsTab());
			cfObj.commonClick(userProfilePageObj.getArticlesTab());
			Thread.sleep(3000);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkMaterial().get(0), 30);
			if (!result) {
				userProfileMsgList.add("BookMarkMaterial is not visible.");
				return result;
			}
			result = userProfilePageObj.getBookMarkMaterial().size() == 1;
			if (!result) {
				userProfileMsgList.add("BookMarkMaterial count is not matching.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateArticleTabEmptyStateAndBookMarkFunction_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateJobAlertTabEmptyStateAndBookMarkFunction(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getJobAlertsTab(), 10);
			if (!result) {
				userProfileMsgList.add("JobAlertsTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getJobAlertsTab());
			Thread.sleep(2000);
			result = userProfilePageObj.getJobAlertsTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("JobAlertsTab is not clickable.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getJobAlertTabEmptyState(), 10);
			if (!result) {
				userProfileMsgList.add("JobAlertTabEmptyState is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getGoToJobAlertBtn());
			result = commonStudyMaterialSectionUtilObj.verifyJobAlertPage(driver);
			if (!result) {
				userProfileMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
				return result;
			}

			result = commonStudyMaterialSectionUtilObj.clicOnMaterialAndAddInBookMark(driver);
			if (!result) {
				userProfileMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getArticlesTab());
			cfObj.commonClick(userProfilePageObj.getJobAlertsTab());
			Thread.sleep(3000);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkMaterial().get(0), 30);
			if (!result) {
				userProfileMsgList.add("BookMarkMaterial is not visible.");
				return result;
			}
			result = userProfilePageObj.getBookMarkMaterial().size() == 1;
			if (!result) {
				userProfileMsgList.add("BookMarkMaterial count is not matching.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateJobAlertTabEmptyStateAndBookMarkFunction_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateBookMarkTabEmptyState(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
			commonStudyMaterialSectionUtilObj = new CommonStudyMaterialSectionUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostsTab(), 10);
			if (!result) {
				userProfileMsgList.add("PostsTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getPostsTab());
			Thread.sleep(2000);
			result = userProfilePageObj.getPostsTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("PostsTab is not clickable.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEmptyStatePost(), 30);
			if (!result) {
				userProfileMsgList.add("EmptyStatePost is not visible.");
				return result;
			}
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")
					&& ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				// QuestionTab validation
				result = validateQuestionTabEmptyStateAndBookMarkFunction(driver);
				if (!result) {
					userProfileMsgList.add("Not able to validate QuestionTabEmptyStateAndBookMarkFunction.");
					return result;
				}

				// VideoTab validation
				result = validateVideoTabEmptyStateAndBookMarkFunction(driver);
				if (!result) {
					userProfileMsgList.add("Not able to validate VideoTabEmptyStateAndBookMarkFunction.");
					return result;
				}

				// CurrentAffairTab validation
				result = validateCurrentAffairTabEmptyStateAndBookMarkFunction(driver);
				if (!result) {
					userProfileMsgList.add("Not able to validate CurrentAffairTabEmptyStateAndBookMarkFunction.");
					return result;
				}

				// Validate ArticleTab
				result = validateArticleTabEmptyStateAndBookMarkFunction(driver);
				if (!result) {
					userProfileMsgList.add("Not able to validate ArticleTabEmptyStateAndBookMarkFunction.");
					return result;
				}

				// validate JobAlert tab
				result = validateJobAlertTabEmptyStateAndBookMarkFunction(driver);
				if (!result) {
					userProfileMsgList.add("Not able to validate JobAlertTabEmptyStateAndBookMarkFunction.");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateBookMarkTabEmptyState_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateChangedUserNameAndDetails(AppiumDriver<MobileElement> driver, String accountHolderName,
			String detailsText) {
		boolean result = true;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@content-desc,'" + accountHolderName + "')]", "xpath", 30);
				if (!result) {
					userProfileMsgList.add("ChangedUserName is not visible.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'" + detailsText + "')]", "xpath", 30);
				if (!result) {
					userProfileMsgList.add("ChangedUserDetailsText is not visible.");
					return result;
				}
			} else {
				Thread.sleep(2000);
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@name,'" + accountHolderName + "')]", "xpath", 30);
				if (!result) {
					userProfileMsgList.add("ChangedUserName is not visible.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@value,'" + detailsText + "')]", "xpath", 30);
				if (!result) {
					userProfileMsgList.add("ChangedUserDetailsText is not visible.");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateChangedUserNameAndDetails_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validatePostComponent(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkPost().get(0), 10);
			if (!result) {
				userProfileMsgList.add("post is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateBookMarkPostComponent_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validatePostLikeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostLikeBtn().get(0), 10);
			if (!result) {
				userProfileMsgList.add("PostLike btn is not visible.");
				return result;
			}
			int likeCount = Integer.parseInt(
					userProfilePageObj.getPostLikeBtn().get(0).getAttribute("content-desc").trim().split(" ")[1]);
			cfObj.commonClick(userProfilePageObj.getPostLikeBtn().get(0));
			Thread.sleep(1000);
			result = Integer.parseInt(userProfilePageObj.getPostLikeBtn().get(0).getAttribute("content-desc").trim()
					.split(" ")[1]) != likeCount;
			if (!result) {
				userProfileMsgList.add("PostLike button is not clickable.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validatePostLikeBtn_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validatePostCommentBtn(AppiumDriver<MobileElement> driver, String strPostComment) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostCommentBtn().get(0), 10);
			if (!result) {
				userProfileMsgList.add("PostLike btn is not visible.");
				return result;
			}
			int commentCount = Integer.parseInt(
					userProfilePageObj.getPostCommentBtn().get(0).getAttribute("content-desc").trim().split(" ")[1]);
			cfObj.commonClick(userProfilePageObj.getPostCommentBtn().get(0));

			communityPageUtilObj = new CommunityPageUtil(driver);
			Thread.sleep(4000);
			result = cfObj.commonWaitForElementToBeVisible(driver,
					communityPageUtilObj.communityPageObj.getTextFieldComment(), 30);
			if (!result) {
				userProfileMsgList.add("Comment text field is not visible.");
				return result;
			}

			communityPageUtilObj.communityPageObj.getTextFieldComment().click();
			Thread.sleep(1000);
			communityPageUtilObj.communityPageObj.getTextFieldComment().sendKeys(strPostComment);

			result = cfObj.commonVerifyValueTextBox(communityPageUtilObj.communityPageObj.getTextFieldComment(),
					strPostComment);
			if (!result) {
				userProfileMsgList.add("Entered comment is not matching.");
				return result;
			}

			result = communityPageUtilObj.clickOnSendCommentBtn(driver, strPostComment);
			if (!result) {
				userProfileMsgList.addAll(communityPageUtilObj.communityPageMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					communityPageUtilObj.communityPageObj.getOwnProfilePic().get(0), 10);
			if (!result) {
				userProfileMsgList.add("OwnProfilePic is not visible.");
				return result;
			}
			cfObj.commonClick(communityPageUtilObj.communityPageObj.getOwnProfilePic().get(0));

			result = validateNewProfileScreen(driver, ProfileType.OWN);
			if (!result) {
				userProfileMsgList.add("Not able to validate profile screen.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getNewUserProfileBackBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver,
					communityPageUtilObj.communityPageObj.getTextFieldComment(), 10);
			if (!result) {
				userProfileMsgList.add("Comment textfield is not visible.");
				return result;
			}

			cfObj.commonClick(communityPageUtilObj.communityPageObj.getCloseCommentsBtn());
			result = Integer.parseInt(userProfilePageObj.getPostCommentBtn().get(0).getAttribute("content-desc").trim()
					.split(" ")[1]) != commentCount;
			if (!result) {
				userProfileMsgList.add("Comment count is not matching.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validatePostLikeBtn_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateQuestionTabEmptyStateAndBookMarkFunction(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			homePageUtilObj = new HomePageUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getQuestionsTab(), 10);
			if (!result) {
				userProfileMsgList.add("QuestionTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getQuestionsTab());
			Thread.sleep(2000);
			result = userProfilePageObj.getQuestionsTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("QuestionsTab is not clickable.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getQuestionTabEmptyState(), 10);
			if (!result) {
				userProfileMsgList.add("QuestionTabEmptyState is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getGoToQuizzeBtn());
			Thread.sleep(2000);

			if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
			}

			result = homePageUtilObj.verifyDailyQuizFeedTabPage(driver);
			if (!result) {
				userProfileMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='GET QUIZ']", "xpath", 30);
			if (!result) {
				userProfileMsgList.add("GetQuiz link is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElements(driver, "//*[@text='GET QUIZ']", "xpath").get(0));
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='ATTEMPT']", "xpath", 30);
			if (!result) {
				userProfileMsgList.add("ATTEMPT link is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='ATTEMPT']", "xpath"));

			commonTestUtilObj = new CommonTestUtil(driver);

			result = commonTestUtilObj.verifyInstructionPage(driver);
			if (!result) {
				userProfileMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result = commonTestUtilObj.clickStartTestBtn(driver);
			if (!result) {
				userProfileMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			if (!cfObj.commonWaitForElementToBeVisible(driver, commonTestUtilObj.commonTestPageObj.getBtnSubmitTest(),
					10)) {
				cfObj.handleHints(driver);
			}

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result = commonTestUtilObj.clickOnFilterBtn(driver);
				if (!result) {
					userProfileMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
			}
			result = commonTestUtilObj.clickSubmitTestBtn(driver);
			if (!result) {
				userProfileMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}
			result = commonTestUtilObj.addQuestionToBookMark(driver);
			if (!result) {
				userProfileMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 2);
			if (!result) {
				userProfileMsgList.add("Not able to press back button 2 times.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getPostsTab());
			Thread.sleep(1000);
			cfObj.commonClick(userProfilePageObj.getQuestionsTab());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkedQuestion(), 10);
			if (!result) {
				userProfileMsgList.add("BookMarkedQuestion is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getBookMarkedQuestion());
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkedQuestionPageTitle(),
					10);
			if (!result) {
				userProfileMsgList.add("BookMarkedQuestionPageTitle is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "back_button", "id"));

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkedQuestion(), 10);
			if (!result) {
				userProfileMsgList.add("BookMarkedQuestion is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateQuestionTabEmptyStateAndBookMarkFunction_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateEmptyStateInBookMarkScreenPostTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostsTab(), 10);
			if (!result) {
				userProfileMsgList.add("PostsTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getPostsTab());
			Thread.sleep(2000);
			result = userProfilePageObj.getPostsTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("PostsTab is not clickable.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEmptyStatePost(), 30);
			if (!result) {
				userProfileMsgList.add("EmptyStatePost is not visible.");
				return result;
			}

		} catch (Exception e) {
			userProfileMsgList.add("validateEmptyStateInBookMarkScreenPostTab_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean attemptMCQTest(AppiumDriver<MobileElement> driver, int optionCount) {
		boolean result = true;
		try {
			Thread.sleep(3000);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMcqOptionsRadioBtn().get(0),
					10);
			if (!result) {
				userProfileMsgList.add("McqOptionsRadioBtn is not visible.");
				return result;
			}

			result = userProfilePageObj.getMcqOptionsRadioBtn().size() == optionCount;
			if (!result) {
				userProfileMsgList.add("McqOptionsRadioBtn count is not matching.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getMcqOptionsRadioBtn().get(0));
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMcqSubmitBtn(), 10);
			if (!result) {
				userProfileMsgList.add("McqSubmitBtn is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getMcqSubmitBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCorrectAnswerTag(), 10);
			if (!result) {
				userProfileMsgList.add("getCorrectAnswerTag is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("attemptMCQTest_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateMCQPostInActivityTab(AppiumDriver<MobileElement> driver, int mcqOptionCount,
			String postComment, String editComment, String subComment) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllPostTab(), 10);
			if (!result) {
				userProfileMsgList.add("AllPostTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getAllPostTab());
			result = userProfilePageObj.getAllPostTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("AllPostTab is not clickable.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkPostTitle().get(0),
					30);
			if (!result) {
				userProfileMsgList.add("BookMarkPostTitle is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getLikeTab());
			result = userProfilePageObj.getLikeTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("LikeTab is not clickable.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEmptyStatePost(), 30);
			if (!result) {
				userProfileMsgList.add("EmptyStatePost is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getAllPostTab());
			result = userProfilePageObj.getAllPostTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("AllPostTab is not clickable.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkPostTitle().get(0),
					30);
			if (!result) {
				userProfileMsgList.add("BookMarkPostTitle is not visible.");
				return result;
			}

			result = clickOnPostLikeBtn(driver);
			if (!result) {
				userProfileMsgList.add("Not able to click Like button.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getLikeTab());
			result = userProfilePageObj.getLikeTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("LikeTab is not clickable.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkPostTitle().get(0),
					30);
			if (!result) {
				userProfileMsgList.add("BookMarkPostTitle is not visible.");
				return result;
			}

			result = removedPostLikeBtn(driver);
			if (!result) {
				userProfileMsgList.add("Not able to remove like from Post.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getCommentTab());
			result = userProfilePageObj.getCommentTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("CommentTab is not clickable.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEmptyStatePost(), 30);
			if (!result) {
				userProfileMsgList.add("EmptyStatePost is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getLikeTab());
			result = userProfilePageObj.getLikeTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("LikeTab is not clickable.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEmptyStatePost(), 30);
			if (!result) {
				userProfileMsgList.add("EmptyStatePost is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getAllPostTab());
			result = userProfilePageObj.getAllPostTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("AllPostTab is not clickable.");
				return result;
			}

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = enterCommentInCommentTextField(driver, postComment);
				if (!result) {
					userProfileMsgList.add("Not able to enter Comment in CommentTextField.");
					return result;
				}

				cfObj.commonClick(userProfilePageObj.getCommentTab());
				result = userProfilePageObj.getCommentTab().getAttribute("selected").equalsIgnoreCase("true");
				if (!result) {
					userProfileMsgList.add("CommentTab is not clickable.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkPostTitle().get(0),
						30);
				if (!result) {
					userProfileMsgList.add("BookMarkPostTitle is not visible.");
					return result;
				}

				cfObj.commonClick(userProfilePageObj.getPostCommentBtn().get(0));

				result = editComment(driver, postComment, editComment);
				if (!result) {
					userProfileMsgList.add("Not able to edit Comment.");
					return result;
				}
				result = enterSubComment(driver, subComment);
				if (!result) {
					userProfileMsgList.add("Not able to enter SubComment.");
					return result;
				}
				result = deleteComment(driver, editComment);
				if (!result) {
					userProfileMsgList.add("Not able to delete Comment.");
					return result;
				}

				result = cfObj.pressAndroidKey(driver, key.BACK, 1);
				if (!result) {
					userProfileMsgList.add("Unable to click Back button.");
					return result;
				}
				cfObj.commonClick(userProfilePageObj.getAllPostTab());
				result = userProfilePageObj.getAllPostTab().getAttribute("selected").equalsIgnoreCase("true");
				if (!result) {
					userProfileMsgList.add("AllPostTab is not clickable.");
					return result;
				}
				Thread.sleep(1000);
				cfObj.commonClick(userProfilePageObj.getCommentTab());
				result = userProfilePageObj.getCommentTab().getAttribute("selected").equalsIgnoreCase("true");
				if (!result) {
					userProfileMsgList.add("CommentTab is not clickable.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEmptyStatePost(), 30);
				if (!result) {
					userProfileMsgList.add("EmptyStatePost is not visible.");
					return result;
				}

				cfObj.commonClick(userProfilePageObj.getAllPostTab());
				result = userProfilePageObj.getAllPostTab().getAttribute("selected").equalsIgnoreCase("true");
				if (!result) {
					userProfileMsgList.add("AllPostTab is not clickable.");
					return result;
				}
			}
			result = attemptMCQTest(driver, mcqOptionCount);
			if (!result) {
				userProfileMsgList.add("Not able to attempt MCQTest");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getMcqTab());

			result = userProfilePageObj.getMcqTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("McqTab is not clickable.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkPostTitle().get(0),
					30);
			if (!result) {
				userProfileMsgList.add("BookMarkPostTitle is not visible.");
				return result;
			}

			result = deletePost(driver);
			if (!result) {
				userProfileMsgList.add("Not able to delete Post.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getAllPostTab());
			result = userProfilePageObj.getAllPostTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("AllPostTab is not clickable.");
				return result;
			}
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEmptyStatePost(), 30);
			if (!result) {
				userProfileMsgList.add("EmptyStatePost is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateMCQPostInActivityTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean deletePost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(userProfilePageObj.getPostDotIcon());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getDeletePostTab(), 30);
			if (!result) {
				userProfileMsgList.add("DeletePostTab  is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getDeletePostTab());

			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostDeleteToast(), 30);
				if (!result) {
					userProfileMsgList.add("PostDeleteToast  is not visible.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("deletePost_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean deletePostInCommentPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.waitForPageLoading(driver, 3, 2000, userProfilePageObj.getCommentPagePostDotIcon());
			cfObj.commonClick(userProfilePageObj.getCommentPagePostDotIcon());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getDeletePostTab(), 30);
			if (!result) {
				userProfileMsgList.add("DeletePostTab  is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getDeletePostTab());
			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostDeleteToast(), 30);
				if (!result) {
					userProfileMsgList.add("PostDeleteToast  is not visible.");
					return result;
				}
			}
			cfObj.waitTillElementIsVisible(driver, 3, 2000, userProfilePageObj.getPostDeleteToast());
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("deletePostInCommentPage_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean clickOnPostLikeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String attribute = null;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				attribute = "content-desc";
			} else {
				attribute = "name";
			}
			int likeCount = Integer.parseInt(
					userProfilePageObj.getPostLikeBtn().get(0).getAttribute(attribute).replaceAll("like", "").trim());

			cfObj.commonClick(userProfilePageObj.getPostLikeBtn().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostLikedToast(), 10);
			if (!result) {
				userProfileMsgList.add("Toast message is not visible.");
			}

			Thread.sleep(1000);

			result = Integer.parseInt(userProfilePageObj.getPostLikeBtn().get(0).getAttribute(attribute)
					.replaceAll("like", "").trim()) == (likeCount + 1);
			if (!result) {
				userProfileMsgList.add("Like Count is not Update.");
			}

		} catch (Exception e) {
			userProfileMsgList.add("clickOnPostLikeBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean removedPostLikeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String attribute = null;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				attribute = "content-desc";
			} else {
				attribute = "name";
			}
			int likeCount = Integer.parseInt(
					userProfilePageObj.getPostLikeBtn().get(0).getAttribute(attribute).replaceAll("like", "").trim());

			cfObj.commonClick(userProfilePageObj.getPostLikeBtn().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getLikeRemovedToastMessage(), 10);
			if (!result) {
				userProfileMsgList.add("Toast message is not visible.");
			}

			Thread.sleep(1000);

			result = Integer.parseInt(userProfilePageObj.getPostLikeBtn().get(0).getAttribute(attribute)
					.replaceAll("like", "").trim()) == (likeCount - 1);
			if (!result) {
				userProfileMsgList.add("Like Count is not Update.");
			}

		} catch (Exception e) {
			userProfileMsgList.add("clickOnPostLikeBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnSendCommentBtn(AppiumDriver<MobileElement> driver, String strPostComment) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBtnSendComment(), 10);
			if (!result) {
				userProfileMsgList.add("Send Comment button is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getBtnSendComment());

			Thread.sleep(5000);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'" + strPostComment + "')]", "xpath", 10);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@value,'" + strPostComment + "')]", "xpath", 10);
			}
		} catch (Exception e) {
			userProfileMsgList.add("clickOnSendCommentBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean enterComment(AppiumDriver<MobileElement> driver, String strPostComment) {
		boolean result = true;
		try {

			cfObj.commonClick(userProfilePageObj.getPostCommentBtn().get(0));

			Thread.sleep(4000);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTextFieldComment(), 30);
			if (!result) {
				userProfileMsgList.add("Comment text field is not visible.");
				return result;
			}

			userProfilePageObj.getTextFieldComment().click();
			Thread.sleep(1000);
			userProfilePageObj.getTextFieldComment().sendKeys(strPostComment);

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonVerifyValueTextBox(userProfilePageObj.getTextFieldComment(), strPostComment);
			} else {
				result = cfObj.commonVerifyValueTextBox(userProfilePageObj.getTextFieldComment(), strPostComment,
						"value");
			}
			if (!result) {
				userProfileMsgList.add("Entered comment is not matching.");
				return result;
			}

			result = clickOnSendCommentBtn(driver, strPostComment);
			if (!result) {
				userProfileMsgList.add("Unable to send comment");
				return result;
			}

		} catch (Exception e) {
			userProfileMsgList.add("enterComment_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean enterCommentInCommentTextField(AppiumDriver<MobileElement> driver, String strPostComment) {
		boolean result = true;
		String attribute = null;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				attribute = "content-desc";
			} else {
				attribute = "name";
			}
			int commentCount = Integer.parseInt(userProfilePageObj.getPostCommentBtn().get(0).getAttribute(attribute)
					.replaceAll("moveToComment", "").trim());

			cfObj.commonClick(userProfilePageObj.getPostCommentBtn().get(0));

			Thread.sleep(4000);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTextFieldComment(), 30);
			if (!result) {
				userProfileMsgList.add("Comment text field is not visible.");
				return result;
			}

			userProfilePageObj.getTextFieldComment().click();
			Thread.sleep(1000);
			userProfilePageObj.getTextFieldComment().sendKeys(strPostComment);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonVerifyValueTextBox(userProfilePageObj.getTextFieldComment(), strPostComment);
			} else {
				result = cfObj.commonVerifyValueTextBox(userProfilePageObj.getTextFieldComment(), strPostComment,
						"value");
			}
			if (!result) {
				userProfileMsgList.add("Entered comment is not matching.");
				return result;
			}

			result = clickOnSendCommentBtn(driver, strPostComment);
			if (!result) {
				userProfileMsgList.add("Unable to send comment");
				return result;
			}
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.pressAndroidKey(driver, key.BACK, 1);
				if (!result) {
					userProfileMsgList.add("Unable to click Back button.");
					return result;
				}
			} else {
				cfObj.tapOnElement(driver,
						cfObj.commonGetElement(driver, "//XCUIElementTypeButton[contains(@name,'postBack')]", "xpath"));

			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostCommentBtn().get(0), 10);
			if (!result) {
				userProfileMsgList.add("Post Comment button is not visible.");
				return result;
			}

			Thread.sleep(1000);

			result = Integer.parseInt(userProfilePageObj.getPostCommentBtn().get(0).getAttribute(attribute)
					.replaceAll("moveToComment", "").trim()) == (commentCount + 1);
			if (!result) {
				userProfileMsgList.add("Comment Count is not Update.");
			}

		} catch (Exception e) {
			userProfileMsgList.add("clickOnPostLikeBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean editComment(AppiumDriver<MobileElement> driver, String strPostComment, String editComment) {
		boolean result = true;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'" + strPostComment + "')]", "xpath", 30);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@value,'" + strPostComment + "')]", "xpath", 30);
			}
			if (!result) {
				userProfileMsgList.add("Post Comment is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostDotIcon(), 10);
			if(!result){
				userProfileMsgList.add("getPostDotIcon is not visible");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getPostDotIcon());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEditCommentBtn(), 30);
			if (!result) {
				userProfileMsgList.add("EditCommentBtn is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getEditCommentBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTextFieldComment(), 30);
			if (!result) {
				userProfileMsgList.add("Comment text field is not visible.");
				return result;
			}

			userProfilePageObj.getTextFieldComment().click();
			userProfilePageObj.getTextFieldComment().clear();
			Thread.sleep(1000);
			userProfilePageObj.getTextFieldComment().sendKeys(editComment);

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonVerifyValueTextBox(userProfilePageObj.getTextFieldComment(), editComment);
			} else {
				result = cfObj.commonVerifyValueTextBox(userProfilePageObj.getTextFieldComment(), editComment, "value");
			}
			if (!result) {
				userProfileMsgList.add("Entered comment is not matching.");
				return result;
			}

			result = clickOnSendCommentBtn(driver, editComment);
			if (!result) {
				userProfileMsgList.add("Unable to send comment");
				return result;
			}
		} catch (Exception e) {
			userProfileMsgList.add("editComment_Exception: " + e.getMessage());
			result = false;
		}
		return result;

	}

	public boolean deleteComment(AppiumDriver<MobileElement> driver, String editComment) {
		boolean result = true;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'" + editComment + "')]", "xpath", 30);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@value,'" + editComment + "')]", "xpath", 30);
			}
			if (!result) {
				userProfileMsgList.add("Post Comment is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getPostDotIcon());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getDeleteCommentBtn(), 30);
			if (!result) {
				userProfileMsgList.add("DeleteCommentBtn is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getDeleteCommentBtn());
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'" + editComment + "')]", "xpath", 30);
			} else {
				result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@value,'" + editComment + "')]", "xpath", 30);
			}
			if (!result) {
				userProfileMsgList.add("Not able to delete comment.");
				return result;
			}

		} catch (Exception e) {
			userProfileMsgList.add("deleteComment_Exception: " + e.getMessage());
			result = false;
		}
		return result;

	}

	public boolean enterSubComment(AppiumDriver<MobileElement> driver, String subComment) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getReplyCommentBtn().get(0), 10);
			if (!result) {
				userProfileMsgList.add("ReplyCommentBtn is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getReplyCommentBtn().get(0));
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getRePlyPageTitle(), 10);
			if (!result) {
				userProfileMsgList.add("RePlyPageTitle is not visible.");
				return result;
			}
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonSetTextTextBox(userProfilePageObj.getReplyCommentTextField(), subComment);
			} else {
				result = cfObj.commonSetTextTextBox(userProfilePageObj.getReplyCommentTextField(), subComment, "value");
			}
			if (!result) {
				userProfileMsgList.add("Not able to enter reply comment.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getReplyCommentPostBtn());

			result = !cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getRePlyPageTitle(), 10);
			if (!result) {
				userProfileMsgList.add("Reply Comment post is not clickable.");
				return result;
			}
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'" + subComment + "')]", "xpath", 30);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@value,'" + subComment + "')]", "xpath", 30);
			}
			if (!result) {
				userProfileMsgList.add("Not able to create subComment.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("enterSubComment_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifySharePost(AppiumDriver<MobileElement> driver, String queryText) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostShareBtn().get(0), 30);
			if (!result) {
				userProfileMsgList.add("PostShareBtn is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getPostShareBtn().get(0));
			Thread.sleep(3000);
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					result = cfObj.waitForPageLoading(driver, 8, 2000, cfObj.commonGetElement(driver,
							"//android.view.View[@content-desc='Adda247 Groups']", "xpath"));
					if (!result) {
						userProfileMsgList.add("Post share page Loading Error.");
						return result;
					}
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//android.view.View[@content-desc='Adda247 Groups']", "xpath", 30);
					if (!result) {
						userProfileMsgList.add("AddaGroupTitle is not visible.");
						return result;
					}
				} else {
					result = cfObj.waitForPageLoading(driver, 8, 2000,
							cfObj.commonGetElement(driver, "//*[@name='Adda247 Groups']", "xpath"));
					if (!result) {
						userProfileMsgList.add("Post share page Loading Error.");
						return result;
					}
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@name='Adda247 Groups']",
							"xpath", 30);
					if (!result) {
						userProfileMsgList.add("AddaGroupTitle is not visible.");
						return result;
					}
				}
			} else {
				result = cfObj.waitForPageLoading(driver, 8, 2000, cfObj.commonGetElement(driver,
						"//android.view.View[@content-desc='Sankalp Bharat Groups']", "xpath"));
				if (!result) {
					userProfileMsgList.add("Post share page Loading Error.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='Sankalp Bharat Groups']", "xpath", 30);
				if (!result) {
					userProfileMsgList.add("SankalpGroupTitle is not visible.");
					return result;
				}
			}

			Thread.sleep(2000);
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//android.view.View[@content-desc='Adda247 Groups']/following-sibling::android.view.View/descendant::android.widget.ImageView",
							"xpath", 10);
					if (!result) {
						userProfileMsgList.add("AddaGroupList is not visible.");
						return result;
					}
					cfObj.commonClick(cfObj.commonGetElements(driver,
							"//android.view.View[@content-desc='Adda247 Groups']/following-sibling::android.view.View/descendant::android.widget.ImageView",
							"xpath").get(2));
				} else {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//*[@name='Adda247 Groups']/following-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeImage",
							"xpath", 10);
					if (!result) {
						userProfileMsgList.add("AddaGroupList is not visible.");
						return result;
					}
					cfObj.commonClick(cfObj.commonGetElements(driver,
							"//*[@name='Adda247 Groups']/following-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeImage",
							"xpath").get(0));
				}
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='Sankalp Bharat Groups']/following-sibling::android.view.View/descendant::android.widget.ImageView",
						"xpath", 10);
				if (!result) {
					userProfileMsgList.add("SankalpGroupList is not visible.");
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Sankalp Bharat Groups']/following-sibling::android.view.View/descendant::android.widget.ImageView",
						"xpath").get(0));
			}
			postingPageUtilObj = new PostingPageUtil(driver);

			userProfilePageObj.getQueryTextField().click();
			Thread.sleep(1000);
			userProfilePageObj.getQueryTextField().sendKeys(queryText);

			Thread.sleep(2000);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = postingPageUtilObj.clickPostSubmitBtn(driver);
				if (!result) {
					userProfileMsgList.addAll(postingPageUtilObj.postingMsgList);
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						postingPageUtilObj.postingPageObj.getTitleSelectTopic(), 10);
				if (result) {

					cfObj.commonClick(postingPageUtilObj.postingPageObj.getOptionTopics().get(0));
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, postingPageUtilObj.postingPageObj.getPostBtn(),
						10);
				if (!result) {
					userProfileMsgList.add("Post button is not visible.");
					return result;
				}

			} else {
				result = postingPageUtilObj.selectTopicForSankalp(driver, "English");
				if (!result) {
					userProfileMsgList.addAll(postingPageUtilObj.postingMsgList);
					return result;
				}
			}

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				cfObj.commonClick(postingPageUtilObj.postingPageObj.getPostBtn());
			} else {
				cfObj.hideKeyBoard(driver);
				cfObj.commonClick(postingPageUtilObj.postingPageObj.getBtnPost());
			}

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android"))
				cfObj.waitTillElementIsVisible(driver, 3, 2000, userProfilePageObj.getRePostToast());

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("verifySharePost_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validatePostShareBtn(AppiumDriver<MobileElement> driver, String queryText) {
		boolean result = true;
		try {
			result = verifySharePost(driver, queryText);
			if (!result) {
				userProfileMsgList.add("Not able to verify Share Post.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getCommentTab());
			Thread.sleep(2000);
			cfObj.commonClick(userProfilePageObj.getAllPostTab());
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'" + queryText + "')]", "xpath", 30);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@value,'" + queryText + "')]", "xpath", 30);
			}
			if (!result) {
				userProfileMsgList.add("Not able to Re-Post.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validatePostShareBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnPostBookMarkBtn(AppiumDriver<MobileElement> driver, boolean isBookMark) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostBookMarkBtn(), 10);
			if (!result) {
				userProfileMsgList.add("PostBookMarkBtn is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getPostBookMarkBtn());
			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
				if (isBookMark) {
					result = cfObj.commonWaitForElementToBeVisible(driver,
							userProfilePageObj.getPostBookMarkAddedToast(), 10);
					if (!result) {
						userProfileMsgList.add("PostBookMarkAddedToast is not visible.");
						return result;
					}
				} else {
					result = cfObj.commonWaitForElementToBeVisible(driver,
							userProfilePageObj.getPostBookMarkRemovedToast(), 10);
					if (!result) {
						userProfileMsgList.add("PostBookMarkRemovedToast is not visible.");
						return result;
					}
				}
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnPostBookMarkBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateLikeShareBookMarkDeleteBtnInPostCommentPage(AppiumDriver<MobileElement> driver,
			String queryText, int mcqOptionCount) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMcqOptionList().get(0), 10);
			if (!result) {
				userProfileMsgList.add("McqOptionList is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getMcqOptionList().get(0));

			result = clickOnPostLikeBtn(driver);
			if (!result) {
				userProfileMsgList.add("Not able to click Like button.");
				return result;
			}
			Thread.sleep(2000);

			result = removedPostLikeBtn(driver);
			if (!result) {
				userProfileMsgList.add("Not able to remove like from Post.");
				return result;
			}
			Thread.sleep(1000);

			result = attemptMCQTest(driver, mcqOptionCount);
			if (!result) {
				userProfileMsgList.add("Not able to remove like from Post.");
				return result;
			}

			result = clickOnPostBookMarkBtn(driver, true);
			if (!result) {
				userProfileMsgList.add("Not able to add Post To BookMark.");
				return result;
			}

			result = verifySharePost(driver, queryText);
			if (!result) {
				userProfileMsgList.add("Not able to verify SharePost.");
				return result;
			}

			result = deletePostInCommentPage(driver);
			if (!result) {
				userProfileMsgList.add("Not able to Delete post.");
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				userProfileMsgList.add("Not able to press back button.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getCommentTab());
			cfObj.commonClick(userProfilePageObj.getAllPostTab());

			result = verifyDeletePostInRePost(driver);
			if (!result) {
				userProfileMsgList.add("Not able to verify DeletePost In RePost.");
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				userProfileMsgList.add("Not able to press back button.");
				return result;
			}

			result = deletePost(driver);
			if (!result) {
				userProfileMsgList.add("Not able to Delete post.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateLikeShareBookMarkBtnInPostCommentPage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyDeletePostInRePost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostDeletedText(), 30);
			if (!result) {
				userProfileMsgList.add("PostDeletedText  is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getPostDeletedText());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostNotAvailableImg(), 30);
			if (!result) {
				userProfileMsgList.add("PostNotAvailableImg  is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("verifyDeletePostInRePost_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateMCQPostInBookMarkPostsTab(AppiumDriver<MobileElement> driver, int mcqOptionCount,
			String postComment, String editComment, String subComment) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getPostsTab(), 10);
			if (!result) {
				userProfileMsgList.add("PostsTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getPostsTab());
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkPostTitle().get(0),
					30);
			if (!result) {
				userProfileMsgList.add("BookMarkPostTitle is not visible.");
				return result;
			}

			result = clickOnPostLikeBtn(driver);
			if (!result) {
				userProfileMsgList.add("Not able to click Like button.");
				return result;
			}

			Thread.sleep(2000);

			result = removedPostLikeBtn(driver);
			if (!result) {
				userProfileMsgList.add("Not able to remove like from Post.");
				return result;
			}

			result = enterComment(driver, postComment);
			if (!result) {
				userProfileMsgList.add("Not able to enter Comment in CommentTextField.");
				return result;
			}

			result = editComment(driver, postComment, editComment);
			if (!result) {
				userProfileMsgList.add("Not able to edit Comment.");
				return result;
			}
			result = enterSubComment(driver, subComment);
			if (!result) {
				userProfileMsgList.add("Not able to enter SubComment.");
				return result;
			}
			result = deleteComment(driver, editComment);
			if (!result) {
				userProfileMsgList.add("Not able to delete Comment.");
				return result;
			}
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.pressAndroidKey(driver, key.BACK, 1);
				if (!result) {
					userProfileMsgList.add("Unable to click Back button.");
					return result;
				}
			}

			result = attemptMCQTest(driver, mcqOptionCount);
			if (!result) {
				userProfileMsgList.add("Not able to attempt MCQTest");
				return result;
			}

			Thread.sleep(2000);

			result = verifySharePost(driver, "QueryComment");
			if (!result) {
				userProfileMsgList.add("Not able to verify SharePost.");
				return result;
			}

			result = clickOnPostBookMarkBtn(driver, false);
			if (!result) {
				userProfileMsgList.add("Not able to Remove post from BookMark.");
				return result;
			}
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = deletePost(driver);
				if (!result) {
					userProfileMsgList.add("Not able to delete Post.");
					return result;
				}
			}
			Thread.sleep(2000);
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")
					&& ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				cfObj.commonClick(userProfilePageObj.getQuestionsTab());
				Thread.sleep(2000);
				cfObj.commonClick(userProfilePageObj.getPostsTab());

				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEmptyStatePost(), 30);
				if (!result) {
					userProfileMsgList.add("EmptyStatePost is not visible.");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateMCQPostInBookMarkPostsTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateUserDeepLink(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = clickOnAllActivityBtn(driver);
			if (!result) {
				userProfileMsgList.add("Not able to click AllActivity button.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllPostTab(), 10);
			if (!result) {
				userProfileMsgList.add("AllPostTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getAllPostTab());
			result = userProfilePageObj.getAllPostTab().getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				userProfileMsgList.add("AllPostTab is not clickable.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkPostTitle().get(0),
					30);
			if (!result) {
				userProfileMsgList.add("BookMarkPostTitle is not visible.");
				return result;
			}

			result = enterUserDeepLinkInCommentTextField(driver);
			if (!result) {
				userProfileMsgList.add("Not able to enter User DeepLink In CommentTextField.");
				return result;
			}
			result = clickDeepLinkOnActivityPage(driver);
			if (!result) {
				userProfileMsgList.add("Not able to click DeepLink.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validateUserDeepLink_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean enterUserDeepLinkInCommentTextField(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(userProfilePageObj.getPostCommentBtn().get(0));

			Thread.sleep(4000);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getTextFieldComment(), 30);
			if (!result) {
				userProfileMsgList.add("Comment text field is not visible.");
				return result;
			}

			result = cfObj.performPastAction(driver, userProfilePageObj.getTextFieldComment());
			if (!result) {
				userProfileMsgList.add("Not able to perform past action.");
				return result;
			}
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				cfObj.commonClick(driver, "//android.widget.Button[@content-desc='Paste']", "xpath");
			} else {
				cfObj.commonClick(driver, "//android.widget.Button[@name='Paste']", "xpath");
			}
			Thread.sleep(1000);

			cfObj.commonClick(userProfilePageObj.getBtnSendComment());

		} catch (Exception e) {
			userProfileMsgList.add("enterUserDeepLinkInCommentTextField_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnDeepLink(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			Thread.sleep(3000);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[contains(@content-desc,\"adda247.com\")]", "xpath", 10);
			if (!result) {
				userProfileMsgList.add("UserProfileDeepLink is not visible.");
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.DOWN);
			Thread.sleep(1000);

			// Link not clickable
			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.widget.Button[contains(@content-desc,\"adda247.com\")]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[contains(@content-desc,\"adda247.com\")]", "xpath", 10);
			if (result) {

				System.out.println("Not able to click, trying again");

				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//android.widget.Button[contains(@content-desc,\"adda247.com\")]", "xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.Button[contains(@content-desc,\"adda247.com\")]", "xpath", 10);
				if (!result) {
					userProfileMsgList
							.add("UserProfileDeepLink is visible after clicking on it, user should go to profile");
					return result;
				}
			} else {
				result = true;
			}

		} catch (Exception e) {
			userProfileMsgList.add("clickOnDeepLink_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickDeepLinkOnActivityPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getUserProfileDeepLink(), 30);
			if (!result) {
				userProfileMsgList.add("UserProfileDeepLink is not visible.");
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.DOWN);
			Thread.sleep(1000);
			cfObj.commonClick(userProfilePageObj.getUserProfileDeepLink());

		} catch (Exception e) {
			userProfileMsgList.add("clickDeepLinkOnActivityPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnMyCertificatesTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMyCertificateTab(), 10);
			if (!result) {
				userProfileMsgList.add("MyCertificateTab is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getMyCertificateTab());
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCertificateViewBtn().get(0),
					10);
			if (!result) {
				userProfileMsgList.add("MyCertificateTab is not visible.");
				return result;
			}

			cfObj.commonClick(userProfilePageObj.getCertificateViewBtn().get(0));

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("clickOnMyCertificatesTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validatePostInActivityTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			for (int i = 0; i < 3; i++) {
				switch (i) {
				case 0:
					result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getAllPostTab(), 10);
					if (!result) {
						userProfileMsgList.add("AllPostTab is not visible.");
						return result;
					}
					cfObj.commonClick(userProfilePageObj.getAllPostTab());
					break;
				case 1:
					result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getCommentTab(), 10);
					if (!result) {
						userProfileMsgList.add("CommentTab is not visible.");
						return result;
					}
					cfObj.commonClick(userProfilePageObj.getCommentTab());
					break;
				case 2:
					result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getLikeTab(), 10);
					if (!result) {
						userProfileMsgList.add("LikeTab is not visible.");
						return result;
					}
					cfObj.commonClick(userProfilePageObj.getLikeTab());
					break;

				}
				result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getBookMarkPostTitle().get(0),
						10);
				if (!result) {
					userProfileMsgList.add("Post is not visible.");
					return result;
				}

				result = userProfilePageObj.getBookMarkPostTitle().size() == 1;
				if (!result) {
					userProfileMsgList.add("Post count is not matching.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getMcqTab(), 10);
			if (!result) {
				userProfileMsgList.add("McqTab is not visible.");
				return result;
			}
			cfObj.commonClick(userProfilePageObj.getMcqTab());

			result = cfObj.commonWaitForElementToBeVisible(driver, userProfilePageObj.getEmptyStateImg(), 10);
			if (!result) {
				userProfileMsgList.add("EmptyStateImg is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userProfileMsgList.add("validatePostInActivityTab_Exception" + e.getMessage());
		}
		return result;
	}

}
