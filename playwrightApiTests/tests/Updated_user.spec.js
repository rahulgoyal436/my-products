// ********RoostGPT********
/*
Test generated by RoostGPT for test roost_test using AI Type Azure Open AI and AI Model gpt-4o-standard

playwright test generated for endpoint: /user/{username}, with method: put
RoostTestHash=/user/{username}-put-bd81865868

*/

// ********RoostGPT********

import test, { expect, request } from "@playwright/test";
import { readCsvData } from "./utility.js";

test("Updated user", async () => {
  const baseURL =  "https://petstore.swagger.io/v2";
  const context = await request.newContext({
    baseURL,
    extraHTTPHeaders: {
      "Content-Type": "application/json",
    },
  });

  const csvFilePath = `/var/tmp/Roost/RoostGPT/roost_test/1732295204/source/my-products/playwrightApiTests/tests/Updated_user.csv`
  const testData = await readCsvData(csvFilePath);

  for (let i = 0; i < testData.length; i++) {
    const csvDataRow = testData[i];
    const { statusCode, ...sampleData } = csvDataRow;

    const response = await context.put(`/user/${sampleData.username}`, { data: JSON.stringify(sampleData) });
    expect(response.status()).toBe(statusCode);

    if (response.status() === statusCode) {
      console.log("Response for updateUser is:", await response.json());
    }
  }
})