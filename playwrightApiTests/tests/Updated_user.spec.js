// ********RoostGPT********
/*
Test generated by RoostGPT for test roost_test using AI Type  and AI Model 

playwright test generated for endpoint: /user/{username}, with method: putRoostTestHash=

*/

// ********RoostGPT********

import test, { expect, request } from "@playwright/test";
import fs from "fs";
import csvParser from "csv-parser";

function convertToNumber(value) {
  const intValue = parseInt(value);
  if (!isNaN(intValue) && value.trim() !== "") {
    return intValue;
  }

  const floatValue = parseFloat(value);
  if (!isNaN(floatValue) && value.trim() !== "") {
    return floatValue;
  }

  return value;
}

function readCsvData(filePath) {
  return new Promise((resolve, reject) => {
    const results = [];
    fs.createReadStream(filePath)
      .pipe(csvParser())
      .on("data", (data) => {
        const convertedData = {};
        Object.keys(data).forEach((key) => {
          convertedData[key] = convertToNumber(data[key]);
        });
        results.push(convertedData);
      })
      .on("end", () => resolve(results))
      .on("error", (err) => reject(err));
  });
}

test("Updated user", async () => {
  const baseURL =  "https://petstore.swagger.io/v2";
  const context = await request.newContext({
    baseURL,
    extraHTTPHeaders: {
      "Content-Type": "application/json",
    },
  });

  const csvFilePath = `/var/tmp/Roost/RoostGPT/roost_test/1732093455/source/my-products/playwrightApiTests/tests/Updated_user.csv`
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