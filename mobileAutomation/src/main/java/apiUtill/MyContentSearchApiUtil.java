package apiUtill;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.mcPopularCourseResponse.PopularCoursesResponse;
import pojo.mcSearchResultsResponse.SearchResultsResponse;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class MyContentSearchApiUtil {
	
	APIUtils apiUtilObj = new APIUtils();
	ConfigFileReader configReaderObject = new ConfigFileReader();

	public PopularCoursesResponse getPopuparCourseResponse(String strUserToken) {
		PopularCoursesResponse popularCourseResponseDataObj = new PopularCoursesResponse();
		APIResponse ap;

		List<Header> headerlist = new ArrayList<Header>();
		try {

			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headerlist.add(new Header("X-JWT-Token", strUserToken));
			Headers headers = new Headers(headerlist);

			ap = apiUtilObj.getCall(configReaderObject.getStoreBaseUrl(),
					"/packages/paid/v1/popularCourses?pageSize=10&src=and", headers);

			if (ap.code != 200) {
				return null;
			}

			popularCourseResponseDataObj = ap.responseData.as(PopularCoursesResponse.class);

		} catch (Exception e) {
			System.out.println("getPopuparCourseResponse_exception: " + e.getMessage());
			return null;
		}
		return popularCourseResponseDataObj;
	}
	
	public SearchResultsResponse getMyContentSearchResultResponse(String strUserToken, String seachTerm) {
		SearchResultsResponse searchResultsResonseObj = new SearchResultsResponse();
		APIResponse ap;

		List<Header> headerlist = new ArrayList<Header>();
		try {

			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headerlist.add(new Header("X-JWT-Token", strUserToken));
			Headers headers = new Headers(headerlist);

			ap = apiUtilObj.getCall(configReaderObject.getStoreBaseUrl(),
					"/packages/paid/v1/search?searchTerm=" + seachTerm + "&src=and", headers);

			if (ap.code != 200) {
				return null;
			}

			searchResultsResonseObj = ap.responseData.as(SearchResultsResponse.class);

		} catch (Exception e) {
			System.out.println("getMyContentSearchResult_exception: " + e.getMessage());
			return null;
		}
		return searchResultsResonseObj;
	}
	
	public List<String> getListOfPopularCourseTitle(String strUserToken){
		List<String> listCourseTitle = new ArrayList<>();
		PopularCoursesResponse popularCoursesResponseObj = new PopularCoursesResponse();
		int size=0;
		
		try {
			popularCoursesResponseObj = getPopuparCourseResponse(strUserToken);
			size = popularCoursesResponseObj.getData().getPackages().size();
			
			for(int i=0; i<size; i++) {
				listCourseTitle.add(popularCoursesResponseObj.getData().getPackages().get(i).getTitle().toString());
			}
			
		} catch (Exception e) {
			System.out.println("getListOfCourseTitle_exception: " + e.getMessage());
			return null;
		}
		return listCourseTitle;
	}
	
	public List<String> getListOfSearchResults(String strUserToken, String seachTerm){
		List<String> listCourseTitle = new ArrayList<>();
		SearchResultsResponse searchResultsResponseObj = new SearchResultsResponse();
		int size=0;
		
		try {
			searchResultsResponseObj = getMyContentSearchResultResponse(strUserToken, seachTerm);
			if(searchResultsResponseObj==null) {
				System.out.println("searchResultsResponseObj is null");
				return null;
			}
			size = searchResultsResponseObj.getData().getPackages().size();
			
			for(int i=0; i<size; i++) {
				listCourseTitle.add(searchResultsResponseObj.getData().getPackages().get(i).getTitle().toString());
			}
			
		} catch (Exception e) {
			System.out.println("getListOfCourseTitle_exception: " + e.getMessage());
			return null;
		}
		return listCourseTitle;
	}
	

}
