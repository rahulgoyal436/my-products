// ********RoostGPT********
/*
Test generated by RoostGPT for test roost_test using AI Type Azure Open AI and AI Model gpt-4o-standard

playwright test generated for endpoint: /user/{username}, with method: get
RoostTestHash=/user/{username}-get-f17de7b32a

*/

// ********RoostGPT********

import test, { expect, request } from "@playwright/test";
import { readCsvData } from "./utility.js";

test("Get user by user name", async () => {
  const baseURL =  "https://petstore.swagger.io/v2";
  const context = await request.newContext({
    baseURL,
    extraHTTPHeaders: {
      "Content-Type": "application/json",
    },
  });

  const csvFilePath = `/var/tmp/Roost/RoostGPT/roost_test/1732295204/source/my-products/playwrightApiTests/tests/Get_user_by_user_name.csv`
  const testData = await readCsvData(csvFilePath);

  for (let i = 0; i < testData.length; i++) {
    const csvDataRow = testData[i];
    const { statusCode, ...sampleData } = csvDataRow;

    const response = await context.get(`/user/${sampleData.username}`);
    expect(response.status()).toBe(statusCode);

    if (response.status() === statusCode) {
      console.log("Response for getUserByName is:", await response.json());
    }
  }
})