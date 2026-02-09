package apiUtill;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.createPostPayload.Attachment;
import pojo.createPostPayload.CreatePostPayload;
import pojo.createPostResponse.CreatePostResponse;
import pojo.groupList.GroupList;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class SocialApiUtil {
	JSONObject requestParams;
	APIUtils apiUtilObj = new APIUtils();
	ConfigFileReader configReaderObject = new ConfigFileReader();

	public CreatePostResponse createAdminPost() {

		CreatePostResponse createPostResponseObj = null;
		APIResponse ap;
		GroupList groupList = null;
		ObjectMapper Obj;

		List<Header> headerlist = new ArrayList<Header>();
		CreatePostPayload createPostPayLoad = new CreatePostPayload();
		try {
			headerlist.add(new Header("jwt-token", configReaderObject.getAdminToken()));
			headerlist.add(new Header("client-name", "admin-panel"));
			headerlist.add(new Header("business-name", "social"));
			Headers headers = new Headers(headerlist);

			groupList = getGroupList(configReaderObject.getCategorySubCategory());
			if (groupList == null) {
				return null;
			}
			createPostPayLoad.setGroupId(groupList.getResponse().getGroupInfoList().get(0).getGroupId());
			createPostPayLoad.setContent("Admin Post");
			createPostPayLoad
					.setTopicTag(groupList.getResponse().getGroupInfoList().get(0).getTopicTags().get(0).getTopicId());
			createPostPayLoad.setPostType("Video/Image/Attachment");
			List<String> taggedUserList = new ArrayList<String>();
			taggedUserList.add("abhay.rai@adda247.com");
			createPostPayLoad.setTaggedUsers(taggedUserList);

			Obj = new ObjectMapper();
			String jsonmStr = Obj.writeValueAsString(createPostPayLoad);

			ap = apiUtilObj.postCall(configReaderObject.getSocialBaseUrlPost(), "/v1/post/create", jsonmStr, headers);

			if (ap.code != 200) {
				return null;
			}
			createPostResponseObj = ap.responseData.as(CreatePostResponse.class);

		} catch (Exception e) {

		}

		return createPostResponseObj;

	}

	public CreatePostResponse createUserPost(String strUserJwtToken) {

		CreatePostResponse createPostResponseObj = null;
		APIResponse ap;
		GroupList groupList = null;
		ObjectMapper Obj;

		List<Header> headerlist = new ArrayList<Header>();
		CreatePostPayload createPostPayLoad = new CreatePostPayload();
		try {
			headerlist.add(new Header("jwt-token", strUserJwtToken));
			headerlist.add(new Header("client-name", "androidapp"));
			headerlist.add(new Header("business-name", "social"));
			Headers headers = new Headers(headerlist);

			groupList = getGroupList(configReaderObject.getCategorySubCategory());
			if (groupList == null) {
				return null;
			}
			createPostPayLoad.setGroupId(groupList.getResponse().getGroupInfoList().get(0).getGroupId());
			createPostPayLoad.setContent("Hello automation" );
			createPostPayLoad
					.setTopicTag(groupList.getResponse().getGroupInfoList().get(0).getTopicTags().get(0).getTopicId());
			createPostPayLoad.setPostType("Video/Image/Attachment");
			List<String> taggedUserList = new ArrayList<String>();
			taggedUserList.add("abhay.rai@adda247.com");
			createPostPayLoad.setTaggedUsers(taggedUserList);
			Attachment attachment = new Attachment();
			attachment.setAttachmentType("png");
			attachment.setAttachmentLink("https://social-attachment.s3.ap-south-1.amazonaws.com/Adda247.png");
			List<Attachment> listAttachment = new ArrayList<Attachment>();
			listAttachment.add(attachment);

//			createPostPayLoad.setAttachment(listAttachment);

			Obj = new ObjectMapper();
			String jsonmStr = Obj.writeValueAsString(createPostPayLoad);

			ap = apiUtilObj.postCall(configReaderObject.getSocialBaseUrlPost(), "/v1/post/create", jsonmStr, headers);

			if (ap.code != 200) {
				return null;
			}
			createPostResponseObj = ap.responseData.as(CreatePostResponse.class);

		} catch (Exception e) {

		}

		return createPostResponseObj;

	}

	public GroupList getGroupList(String strCategorySubCategoryName) {

		List<Header> headerlist = new ArrayList<Header>();
		GroupList groupList = null;
		APIResponse ap;
		try {
			headerlist.add(new Header("jwt-token", configReaderObject.getAdminToken()));
			headerlist.add(new Header("client-name", "admin-panel"));
			headerlist.add(new Header("business-name", "social"));
			Headers headers = new Headers(headerlist);

			ap = apiUtilObj.getCall(configReaderObject.getSocialBaseUrl(),
					"/v1/group/list/categoryidentfier/" + strCategorySubCategoryName, headers);
			if (ap.code != 200) {
				return null;
			}

			groupList = ap.responseData.as(GroupList.class);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return groupList;

	}
}
