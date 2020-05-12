package com.planittesting.jupitertoys.support.jira;

import com.planittesting.jupitertoys.support.jira.responses.IssueDetails;
import com.planittesting.jupitertoys.support.jira.responses.TestCaseDetails;
import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class JiraApiServices {
    String user = "hong.wang.dev@gmail.com";
    String pw = "uuPyBE2sQP1TzpqipEup5019";
    String url = "https://hongliw.atlassian.net";

    private String buildAuthHeader(String authMethod) {
        String authHeader = null;
        if (authMethod.equalsIgnoreCase("basic")) {
            String auth = user + ":" + pw;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
            authHeader = "Basic " + new String(encodedAuth);
        }
        return authHeader;
    }

    private String buildEndpoint(String type, String key) {
        return url + "/rest/api/2/" + type + "/" + key;
    }

    private String buildEndpoint(String type) {
        return url + "/rest/api/2/" + type;
    }

    private JSONObject sendRequest(String requestMethod, String requestEndpoint, JSONObject requestBody) {
        JSONObject jsonResponse = null;
        try {
            URL url = new URL(requestEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Authorization", buildAuthHeader("basic"));
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            if (requestBody != null) {
                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                wr.write(requestBody.toString());
                wr.flush();
            }

            int responseCode = connection.getResponseCode();
            if ((responseCode != 200) && (responseCode != 201)) {
                throw new RuntimeException("Failed: HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();
            connection.disconnect();
            jsonResponse = new JSONObject(response.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }
    public IssueDetails getIssueDetails(String issueKey) {
        IssueDetails issueDetails = new IssueDetails();
        JSONObject response = sendRequest("GET", buildEndpoint("issue", issueKey), null);
        issueDetails.setIssueId(response.getString("id"));

        String testType = response.getJSONObject("fields").getJSONObject("issuetype").getString("name");
        System.out.println("result after Reading JSON Response");
        System.out.println("issueId: "+response.getString("id"));
        System.out.println("issueKey: "+response.getString("key"));
        System.out.println("issueType: "+response.getJSONObject("fields").getJSONObject("issuetype").getString("name"));

        return issueDetails;
    }

    public TestCaseDetails getTestCaseDetails(String issueKey) throws Exception {
        TestCaseDetails testCaseDetails = new TestCaseDetails();
        JSONObject response = sendRequest("GET", buildEndpoint("issue", issueKey), null);
        String issueType = response.getJSONObject("fields").getJSONObject("issuetype").getString("name");

        if (!issueType.equalsIgnoreCase("test")) {
            throw new Exception("Jira issue with key: " + issueKey + " is not a test case");
        }

        testCaseDetails.setIssueId(response.getString("id"));
        testCaseDetails.setIssueType(response.getJSONObject("fields").getJSONObject("issuetype").getString("name"));

        System.out.println("result after Reading JSON Response");
        System.out.println("issueId: "+response.getString("id"));
        System.out.println("issueKey: "+response.getString("key"));
        System.out.println("issueType: "+response.getJSONObject("fields").getJSONObject("issuetype").getString("name"));

        return testCaseDetails;
    }

    public void createTestCase(String projectKey, String testCaseName, String description) {
        JSONObject requestBody = new JSONObject();
        JSONObject issueType = new JSONObject();
        JSONObject project = new JSONObject();
        JSONObject fields = new JSONObject();
        issueType.put("name", "Test");
        project.put("key", projectKey);
        fields.put("project", project)
                .put("summary", testCaseName)
                .put("description", description)
                .put("issuetype", issueType);
        requestBody.put("fields", fields);
        sendRequest("POST", buildEndpoint("issue"), requestBody);
    }

    public boolean isTestCaseExist(String testCaseName) {
        //search in jira to see if test exists
        //rest/api/2/search?jql=project=\"HOTHLR\"&fields=summary,key
        return false;
    }

    //upload result to xray
}
