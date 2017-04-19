package GenericLib;

import TestRail_Inte.APIClient;
import TestRail_Inte.APIException;

import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import pageObject.JiraAccess;

/**
 * Created by t.mirasipally on 12-Apr-17.
 */
public class TestRail_Integration {
    static APIClient client;
    public static void TestRailLogin(){
        client = new APIClient("https://demoversion3.testrail.net");
        client.setUser("demoversion3@yopmail.com");
        client.setPassword("$amY2020");
    }
static int testNo;
    public static void GetTestCaseTitle(int TCno) throws IOException, APIException {
        TestRailLogin();
        testNo=TCno;
        JSONObject c = (JSONObject) client.sendGet("get_test/"+testNo+"");
        Object TestCaseTitle = c.get("title");
        System.out.println("Test Case Title is "+TestCaseTitle);
    }

    public static void UploadResults(boolean testResult) throws IOException, APIException {
        TestRailLogin();
        int status_id = 0;
        List steps = new ArrayList();
        Map data = new HashMap();
        Map step1 = new HashMap();
        if(testResult==true){
            status_id=1;
            data.put("status_id", new Integer(status_id));
            data.put("comment", "Successfully Verified");
        }
        else if(testResult==false){
            status_id=5;
            data.put("status_id", new Integer(status_id));
            data.put("comment", JiraAccess.TestRailDescriptionText());
        }else{ status_id = 3;};


        /*step1.put("status_id", new Integer(status_id));
        step1.put("content", "Step 1");
        step1.put("expected", "Expected");
        step1.put("actual", "Actual result is same as expected");
        steps.add(step1);

        data.put("status_id", new Integer(status_id));
        data.put("comment", "In Progress from selenium");
        data.put("custom_step_results", steps);*/

        JSONObject r = (JSONObject) client.sendPost("add_result/"+testNo+"", data);
        /*JSONObject c = (JSONObject) client.sendGet("get_test/157");
        System.out.println(c.get("get_results"));*/

    }


}
